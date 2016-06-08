package org.devgateway.geoph.services.repository;

import org.devgateway.geoph.model.Agency;
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
    List<Agency> findByName(String name);

    @Query("select a from Agency a where a.code = ?1")
    Agency findByCode(String code);

}
