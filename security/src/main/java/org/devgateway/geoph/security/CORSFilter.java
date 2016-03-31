package org.devgateway.geoph.security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_OK;


/**
 * This filer add sCross-Origin DeploymentResourceResponse Sharing (CORS) support .
 * CORS is an standard mechanism for enabling cross-domain requests from web browsers to servers that wish to handle them.
 * <p/>
 * Created by sebas on 6/22/14.
 */

public class CORSFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        // CORS "pre-flight" request
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Content-Type,X-Internal-Token,workspace_id");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Expose-Headers", "X-Internal-Token");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min

        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
            response.setStatus(SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }


    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }


}
