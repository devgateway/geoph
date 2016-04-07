package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.Sector;
import org.devgateway.geoph.util.Parameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 03 2016.
 */
public interface SectorRepository {

    List<Sector> findAll();

    Sector findByCode(String code);

    List<Sector> findByLevel(int level);

    List<Object> findFundingBySector(Parameters params);
}
