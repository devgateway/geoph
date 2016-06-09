package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.ClimateChange;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dbianco
 *         created on abr 26 2016.
 */
public interface ClimateChangeRepository extends JpaRepository<ClimateChange, Long> {
}
