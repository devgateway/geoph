package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.AgencyResultsDao;
import org.devgateway.geoph.model.FundingAgency;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 04 2016.
 */
public interface FundingAgencyRepository {

    List<FundingAgency> findAll();

    List<AgencyResultsDao> findFundingByFundingAgency(Parameters params, int trxTypeId, int trxStatusId);

    Integer countAll();
}
