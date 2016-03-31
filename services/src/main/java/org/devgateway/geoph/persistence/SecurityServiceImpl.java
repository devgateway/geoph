package org.devgateway.geoph.persistence;

import org.devgateway.geoph.model.security.GrantedAuthority;
import org.devgateway.geoph.model.security.User;
import org.devgateway.geoph.persistence.repository.security.GrantedAuthorityRepository;
import org.devgateway.geoph.persistence.repository.security.UserRepository;
import org.devgateway.geoph.services.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public User savePerson(User geophUser) {
        return userRepository.saveAndFlush(geophUser);
    }

    @Override
    public User getLoggedUser() throws Exception {
        return userRepository.findByEmail(getUsername());
    }

    @Override
    public User loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username);
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
        isTrue(principal instanceof User, "The user is not logged in");
        return ((User) principal).getUsername();
    }
}
