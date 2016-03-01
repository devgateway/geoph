package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.FundingSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dbianco
 *         created on mar 01 2016.
 */
@Transactional
public interface FundingSourceRepository  extends JpaRepository<FundingSource, Long> {

    @Query("select a from funding_source a where a.name = ?1")
    Page<FundingSource> findByName(String name, Pageable pageable);
}
