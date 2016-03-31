package org.devgateway.geoph.persistence.repository.security;

import org.devgateway.geoph.model.security.GrantedAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dbianco
 *         created on mar 31 2016.
 */
public interface GrantedAuthorityRepository extends JpaRepository<GrantedAuthority, Long> {
}
