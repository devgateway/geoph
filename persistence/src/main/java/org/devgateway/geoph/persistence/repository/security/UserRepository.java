package org.devgateway.geoph.persistence.repository.security;

import org.devgateway.geoph.model.security.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author dbianco
 *         created on mar 31 2016.
 */
public interface UserRepository extends JpaRepository<SystemUser, Long> {

    @Query("select a from SystemUser a where a.email = ?1")
    SystemUser findByEmail(String username);

}
