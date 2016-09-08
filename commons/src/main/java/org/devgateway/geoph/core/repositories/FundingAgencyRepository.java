package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.AgencyResultsDao;
import org.devgateway.geoph.dao.ChartProjectCountDao;
import org.devgateway.geoph.model.FundingAgency;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 04 2016.
 */
public interface FundingAgencyRepository {

    List<FundingAgency> findAll();

    List<AgencyResultsDao> findFundingByFundingAgencyWithTransactionStats(Parameters params);

    List<ChartProjectCountDao> findFundingByFundingAgencyWithProjectStats(Parameters params);

    Integer countAll();

    FundingAgency findById(Long id);
}
