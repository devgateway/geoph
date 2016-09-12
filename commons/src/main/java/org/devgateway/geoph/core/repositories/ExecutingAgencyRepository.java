package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.AgencyResultsDao;
import org.devgateway.geoph.dao.ChartProjectCountDao;
import org.devgateway.geoph.model.ExecutingAgency;

import java.util.List;

/**
 * @author dbianco
 *         created on may 05 2016.
 */
public interface ExecutingAgencyRepository {

    List<ExecutingAgency> findAll();

    List<AgencyResultsDao> findFundingByExecutingAgencyWithTransactionStats(Parameters params);

    List<ChartProjectCountDao> findFundingByExecutingAgencyWithProjectStats(Parameters params);

    Integer countAll();

    ExecutingAgency findById(Long id);
}
