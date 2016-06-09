package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.Sector;
import org.devgateway.geoph.util.Parameters;
import org.devgateway.geoph.util.queries.SectorResultsQueryHelper;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 03 2016.
 */
public interface SectorRepository {

    List<Sector> findAll();

    Sector findByCode(String code);

    List<Sector> findByLevel(int level);

    List<SectorResultsQueryHelper> findFundingBySector(Parameters params, int trxTypeId, int trxStatusId);
}
