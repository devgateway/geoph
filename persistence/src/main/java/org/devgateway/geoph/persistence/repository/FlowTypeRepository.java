package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.FlowType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 01 2016.
 */
@Transactional
public interface FlowTypeRepository extends JpaRepository<FlowType, Long> {

    @Query("select a from flow_type a where a.name = ?1")
    List<FlowType> findByName(String name);
}
