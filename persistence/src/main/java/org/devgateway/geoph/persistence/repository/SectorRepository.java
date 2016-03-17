package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 03 2016.
 */
public interface SectorRepository extends JpaRepository<Sector, Long> {

    @Query("select a from Sector a where a.code = ?1")
    Sector findByCode(String code);

    @Query("select a from Sector a where a.level = ?1")
    List<Sector> findByLevel(int level);
}
