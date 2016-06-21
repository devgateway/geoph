package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.model.IndicatorDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author dbianco
 *         created on abr 25 2016.
 */
public interface IndicatorDetailRepository extends JpaRepository<IndicatorDetail, Long> {

    @Query("select a from IndicatorDetail a where a.indicatorId = ?1")
    List<IndicatorDetail> findByIndicatorId(long indicatorId);
}
