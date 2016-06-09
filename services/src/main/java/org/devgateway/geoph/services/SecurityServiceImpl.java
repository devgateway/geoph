package org.devgateway.geoph.services;

import org.devgateway.geoph.model.security.GrantedAuthority;
import org.devgateway.geoph.model.security.SystemUser;
import org.devgateway.geoph.persistence.SecurityService;
import org.devgateway.geoph.persistence.repository.security.GrantedAuthorityRepository;
import org.devgateway.geoph.persistence.repository.security.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

/**
 * @author dbianco
 *         created on mar 31 2016.
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    GrantedAuthorityRepository grantedAuthorityRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public GrantedAuthority saveGrantedAuthority(GrantedAuthority grantedAuthority) {
        return grantedAuthorityRepository.saveAndFlush(grantedAuthority);
    }

    @Override
    public SystemUser savePerson(SystemUser geophUser) {
        return userRepository.saveAndFlush(geophUser);
    }

    @Override
    public SystemUser getLoggedUser() throws Exception {
        return userRepository.findByEmail(getUsername());
    }

    @Override
    public SystemUser loadUserByUsername(String username) {
        SystemUser user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    private String getUsername() throws Exception {
        final Object principal;
        notNull(SecurityContextHolder.getContext(), "The Security Context could not be null");
        notNull(SecurityContextHolder.getContext().getAuthentication(), "The Security Context Authentication could not be null");
        principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        isTrue(principal instanceof SystemUser, "The user is not logged in");
        return ((SystemUser) principal).getUsername();
    }
}
