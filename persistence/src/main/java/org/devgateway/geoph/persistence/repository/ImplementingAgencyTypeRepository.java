package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.ImplementingAgencyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dbianco
 *         created on mar 03 2016.
 */
@Transactional
public interface ImplementingAgencyTypeRepository extends JpaRepository<ImplementingAgencyType, Long> {

    @Query("select a from implementing_agency_type a where a.typeId = ?1")
    ImplementingAgencyType findByTypeId(String typeId);
}
