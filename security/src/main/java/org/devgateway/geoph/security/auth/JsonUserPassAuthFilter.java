package org.devgateway.geoph.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

/**
 * @author dbianco
 *         created on mar 31 2016.
 */
public class JsonUserPassAuthFilter extends UsernamePasswordAuthenticationFilter {

    private String jsonUsername;

    private String jsonPassword;

    private final ObjectMapper mapper;

    public JsonUserPassAuthFilter() {
        mapper = new ObjectMapper();
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String password;

        if (request.getHeader("Content-Type").indexOf("application/json") > -1) {
            password = this.jsonPassword;
        } else {
            password = super.obtainPassword(request);
        }

        return password;
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String username = null;

        if (request.getHeader("Content-Type").indexOf("application/json") > -1) {
            username = this.jsonUsername;
        } else {
            username = super.obtainUsername(request);
        }

        return username;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (request.getHeader("Content-Type").indexOf("application/json") > -1) {
            try {
                /*
                 * HttpServletRequest can be read only once
                 */
                StringBuilder sb = new StringBuilder();
                String line = null;

                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                //json transformation
                LoginRequest loginRequest = mapper.readValue(sb.toString(), LoginRequest.class);
                this.jsonUsername = loginRequest.getUsername();
                this.jsonPassword = loginRequest.getPassword();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return super.attemptAuthentication(request, response);


    }


}
