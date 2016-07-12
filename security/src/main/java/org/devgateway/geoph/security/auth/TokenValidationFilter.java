package org.devgateway.geoph.security.auth;

import org.devgateway.geoph.core.services.SecurityService;
import org.devgateway.geoph.core.services.TokenService;
import org.devgateway.geoph.model.security.PersistentToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author dbianco
 *         created on mar 31 2016.
 */
public class TokenValidationFilter extends GenericFilterBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenValidationFilter.class);

    private static String DELIMITER = ":";
    protected AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();


    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    private SecurityService securityService;


    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
       /*Get User By Token and set the set Authentication*/

        String token = request.getHeader("X-Security-Token");

        UserDetails userDetails = null;
        if (token != null) {

            try {
                userDetails = processAutoLogin(decodeToken(token), request, response);
            } catch (Exception e) {
                LOGGER.info("User not authenticated: {}", e.getMessage());
            }
            if (userDetails != null) {
                //LOGGER.info("Success Token authentication for " + userDetails.getUsername() + " ");

                SecurityContext securityContext = SecurityContextHolder.getContext();

                Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                securityContext.setAuthentication(auth);
                /*
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "1111");
                authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
                securityContext.setAuthentication(getAuthenticationManager().authenticate(authRequest));
                */
                HttpSession session = request.getSession(true);
                session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
            }
        }
        chain.doFilter(req, res);
    }


    protected String[] decodeToken(String values) throws InvalidCookieException {
        for (int j = 0; j < values.length() % 4; j++) {
            values = values + "=";
        }

        if (!Base64.isBase64(values.getBytes())) {
            throw new InvalidCookieException("Token was not Base64 encoded; value was '" + values + "'");
        }

        String plainText = new String(Base64.decode(values.getBytes()));

        String[] tokens = StringUtils.delimitedListToStringArray(plainText, DELIMITER);

        if ((tokens[0].equalsIgnoreCase("http") || tokens[0].equalsIgnoreCase("https")) && tokens[1].startsWith("//")) {
            // Assume we've accidentally split a URL (OpenID identifier)
            String[] newTokens = new String[tokens.length - 1];
            newTokens[0] = tokens[0] + ":" + tokens[1];
            System.arraycopy(tokens, 2, newTokens, 1, newTokens.length - 1);
            tokens = newTokens;
        }

        return tokens;
    }


    protected UserDetails processAutoLogin(String[] tokens, HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (tokens.length != 2) {
            throw new InvalidCookieException("Token did not contain " + 2 +
                    " tokens, but contained '" + Arrays.asList(tokens) + "'");
        }

        final String presentedSeries = tokens[0];
        final String presentedToken = tokens[1];


        PersistentToken token = this.tokenService.findBySeries(presentedSeries);

        if (token == null) {
            // No series match, so we can't authenticate using this cookie
            throw new Exception("No persistent token found for series id: " + presentedSeries);
        }

        // We have a match for this user/series combination
        if (!presentedToken.equals(token.getTokenValue())) {
            // Token doesn't match series value. Delete all logins for this user and throw an exception to warn them.
            //TODO: Unvalidate login


        }

        if (token.getDate().getTime() + (token.getValidDays() * 24 * 60 * 60 * 1000L) <= System.currentTimeMillis()) {
            throw new Exception("Token is expired");
        }

        //TODO: update token expiration time
        /// PersistentRememberMeToken newToken = new PersistentRememberMeToken(token.getUsername(), token.getSeries(), generateTokenData(), new Date());

        try {
            // tokenService.save(newToken.getSeries(), newToken.getTokenValue(), newToken.getDate());

        } catch (DataAccessException e) {
            logger.error("Failed to update token: ", e);
            throw new RememberMeAuthenticationException("Autologin failed due to data access problem");
        }

        return this.securityService.loadUserByUsername(token.getUsername());
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }


    public TokenService getTokenService() {
        return tokenService;
    }

    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public SecurityService getSecurityService() {
        return securityService;
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }


    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}


