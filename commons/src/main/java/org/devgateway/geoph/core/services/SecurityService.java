package org.devgateway.geoph.core.services;

import org.devgateway.geoph.model.security.GrantedAuthority;
import org.devgateway.geoph.model.security.SystemUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author dbianco
 *         created on mar 31 2016.
 */
public interface SecurityService extends UserDetailsService {

    GrantedAuthority saveGrantedAuthority(GrantedAuthority grantedAuthority);

    SystemUser savePerson(SystemUser geophUser);

    SystemUser getLoggedUser() throws Exception;

    SystemUser loadUserByUsername(String username);
}
