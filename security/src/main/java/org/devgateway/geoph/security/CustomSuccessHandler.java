package org.devgateway.geoph.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.devgateway.geoph.model.security.PersistentToken;
import org.devgateway.geoph.model.security.SystemUser;
import org.devgateway.geoph.services.SecurityService;
import org.devgateway.geoph.services.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @author dbianco
 *         created on mar 31 2016.
 */
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomSuccessHandler.class);
    private final ObjectMapper mapper;
    private final SecurityService securityService;
    private final TokenService tokenService;

    public CustomSuccessHandler(SecurityService securityService, TokenService tokenService) {
        mapper = new ObjectMapper();
        this.securityService = securityService;
        this.tokenService = tokenService;
    }

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication)
            throws IOException, ServletException {
        if (req.getHeader("Content-Type").indexOf("application/json") > -1) {

            String username = authentication.getName();
            logger.debug("Creating new persistent token " + username);
            PersistentToken token = new PersistentToken(username,
                    tokenService.generateSeriesData(),
                    tokenService.generateTokenData(),
                    new Date(),
                    tokenService.getDefaultValidDays());
            try {
                tokenService.savePersistentToken(token);

            } catch (DataAccessException e) {
                logger.error("Failed to save persistent token ", e);
            }

            String encodedToken = tokenService.encodeToken(new String[]{token.getSeries(), token.getTokenValue()});


            res.setHeader("X-Internal-Token", encodedToken);

            try{
                final SystemUser publicView = securityService.getLoggedUser();
                mapper.writeValue(res.getWriter(), publicView);
                res.setStatus(HttpServletResponse.SC_OK);
                res.getWriter().flush();
            } catch (Exception e){
                LOGGER.info("User not logged: {}", e.getMessage());
                throw new NotAllowException("User not log in");
            }

        } else {
            super.onAuthenticationSuccess(req, res, authentication);
        }
    }

}


