package org.devgateway.geoph.services.repository.security;

import org.devgateway.geoph.model.security.PersistentToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 31 2016.
 */
public interface PersistentTokenRepository extends JpaRepository<PersistentToken, Long> {

    @Query("select a from PersistentToken a where a.series = ?1")
    public PersistentToken findBySeries(String series);

    @Query("select a from PersistentToken a where a.username = ?1")
    public List<PersistentToken> findByUsername(String username);
}
