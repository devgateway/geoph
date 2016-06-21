package org.devgateway.geoph.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.devgateway.geoph.core.services.SecurityService;
import org.devgateway.geoph.core.services.TokenService;
import org.devgateway.geoph.model.security.PersistentToken;
import org.devgateway.geoph.model.security.SystemUser;
import org.devgateway.geoph.security.NotAllowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;

/**
 * Created by sebas on 6/18/14.
 */
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private ObjectMapper mapper;
    private SecurityService securityService;
    private TokenService tokenService;

    @Autowired
    public CustomSuccessHandler(TokenService tokenService, SecurityService securityService) {
        this.tokenService = tokenService;
        this.mapper = new ObjectMapper();
        this.securityService = securityService;
    }

    @Override
    /**
     * Override the original redirect by an JSON response
     */
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication)
            throws IOException, ServletException {
        if (req.getHeader("Content-Type").indexOf("application/json") > -1) {

            String username = authentication.getName();
            logger.debug("Creating new persistent token " + username);
            PersistentToken token = new PersistentToken(username, tokenService.generateSeriesData(), tokenService.generateTokenData(), new Date(), tokenService.getDefaultValidDays());
            try {
                this.tokenService.savePersistentToken(token);

            } catch (DataAccessException e) {
                logger.error("Failed to save persistent token ", e);
            }

            String encodedToken = tokenService.encodeToken(new String[]{token.getSeries(), token.getTokenValue()});


            res.setHeader("X-Internal-Token", encodedToken);
            try {
                final SystemUser publicView = securityService.getLoggedUser();
                mapper.writeValue(res.getWriter(), publicView);
                res.setStatus(HttpServletResponse.SC_OK);
                res.getWriter().flush();
            } catch (Exception e) {
                throw new NotAllowException("User not logged in:" + e);
            }

        } else {
            super.onAuthenticationSuccess(req, res, authentication);
        }
    }

    public TokenService getTokenService() {
        return tokenService;
    }

    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

}


