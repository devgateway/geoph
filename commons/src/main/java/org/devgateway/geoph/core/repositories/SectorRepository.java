package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.SectorResultsDao;
import org.devgateway.geoph.model.Sector;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 03 2016.
 */
public interface SectorRepository {

    List<Sector> findAll();

    Sector findByCode(String code);

    List<Sector> findByLevel(int level);

    List<SectorResultsDao> findFundingBySector(Parameters params, int trxTypeId, int trxStatusId);
}
