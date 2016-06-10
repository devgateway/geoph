package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.AgencyResultsQueryHelper;
import org.devgateway.geoph.model.FundingAgency;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 04 2016.
 */
public interface FundingAgencyRepository {

    List<FundingAgency> findAll();

    List<AgencyResultsQueryHelper> findFundingByFundingAgency(Parameters params, int trxTypeId, int trxStatusId);

    Integer countAll();
}
