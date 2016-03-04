package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author dbianco
 *         created on mar 03 2016.
 */
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("select a from Location a where a.uacs = ?1")
    Location findByUacs(String uacs);
}
