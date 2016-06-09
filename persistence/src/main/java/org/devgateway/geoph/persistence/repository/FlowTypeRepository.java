package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.FlowType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author dbianco
 *         created on abr 22 2016.
 */
public interface FlowTypeRepository extends JpaRepository<FlowType, Long> {

    @Query("select a from FlowType a where LOWER(a.name) LIKE LOWER(CONCAT('%',?1, '%'))")
    FlowType findByName(String name);

}
