package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.AgencyResultsDao;
import org.devgateway.geoph.dao.ChartProjectCountDao;
import org.devgateway.geoph.model.ImplementingAgency;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 04 2016.
 */
public interface ImplementingAgencyRepository {

    List<ImplementingAgency> findAll();

    ImplementingAgency findById(Long id);

    Integer countAll();

    List<AgencyResultsDao> findFundingByImplementingAgencyWithTransactionStats(Parameters params);

    List<ChartProjectCountDao> findFundingByImplementingAgencyWithProjectStats(Parameters params);
}
