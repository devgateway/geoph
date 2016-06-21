package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.model.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author dbianco
 *         created on abr 25 2016.
 */
public interface IndicatorRepository extends JpaRepository<Indicator, Long> {

    @Query("select a from Indicator a where a.name = ?1")
    Indicator findByName(String name);
}
