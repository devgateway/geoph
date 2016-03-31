package org.devgateway.geoph.persistence.repository.security;

import org.devgateway.geoph.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author dbianco
 *         created on mar 31 2016.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select a from User a where a.email = ?1")
    User findByEmail(String username);

}
