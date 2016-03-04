package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.Agency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dbianco
 *         created on feb 29 2016.
 */
@Transactional
public interface AgencyRepository extends JpaRepository<Agency, Long> {

    @Query("select a from Agency a where a.name = ?1")
    Agency findByName(String name);

    @Query("select a from Agency a where a.agencyId = ?1")
    Agency findByAgencyId(String agencyId);

    @Query(nativeQuery = true, value = "select * from Agency a where a.discriminator = 'implementing_agency'")
    List<Agency> findAllImpAgencies();

}
