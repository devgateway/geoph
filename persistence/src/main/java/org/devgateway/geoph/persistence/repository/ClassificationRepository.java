package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.Classification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author dbianco
 *         created on mar 03 2016.
 */
public interface ClassificationRepository extends JpaRepository<Classification, Long> {

    @Query("select a from Classification a where a.name = ?1")
    Classification findByName(String name);
}
