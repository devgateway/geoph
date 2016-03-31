package org.devgateway.geoph.security;

import org.devgateway.geoph.services.SecurityService;
import org.devgateway.geoph.services.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dbianco
 *         created on mar 31 2016.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan(basePackages = { "org.devgateway.geoph.security.*" })
@EnableJpaRepositories(basePackages = {
        "org.devgateway.geoph.persistence.repository.security",
})
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    SecurityService securityService;

    @Autowired
    TokenService tokenService;

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin()
                .disable()
                .addFilterBefore(new CORSFilter(), LogoutFilter.class)
                .addFilterBefore(tokenValidationFilter(), LogoutFilter.class)
                .addFilter(jsonUsernamePasswordAuthenticationFilter(customAuthenticationSuccessHandler(securityService)))
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        // TODO remove the token
                        // remove the queue listener
                        System.out.println("logout");
                    }
                });
    }

    @Bean
    public AuthenticationEntryPoint customAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler(final SecurityService securityService) throws Exception {
        return new CustomSuccessHandler(securityService, tokenService);
    }

    public TokenValidationFilter tokenValidationFilter() throws Exception {
        return new TokenValidationFilter(authenticationManagerBean(), tokenService, securityService);
    }

    @Bean
    public UsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter(final AuthenticationSuccessHandler authenticationSuccessHandler) throws Exception {
        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new JsonUsernamePasswordAuthenticationFilter();
        usernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager());
        usernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        return usernamePasswordAuthenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder registry) throws Exception {
        LOGGER.info("Configuring UserDetails Service");
        registry.userDetailsService(securityService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new StandardPasswordEncoder("1");
    }

}
