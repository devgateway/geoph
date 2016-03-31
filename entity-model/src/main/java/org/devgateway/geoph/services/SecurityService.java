package org.devgateway.geoph.services;

import org.devgateway.geoph.model.security.GrantedAuthority;
import org.devgateway.geoph.model.security.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author dbianco
 *         created on mar 31 2016.
 */
public interface SecurityService extends UserDetailsService {

    GrantedAuthority saveGrantedAuthority(GrantedAuthority grantedAuthority);

    User savePerson(User geophUser);

    User getLoggedUser() throws Exception;

    User loadUserByUsername(String username);
}
