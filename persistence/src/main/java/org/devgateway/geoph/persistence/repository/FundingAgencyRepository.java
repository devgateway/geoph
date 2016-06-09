package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.FundingAgency;
import org.devgateway.geoph.util.Parameters;
import org.devgateway.geoph.util.queries.AgencyResultsQueryHelper;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 04 2016.
 */
public interface FundingAgencyRepository {

    List<FundingAgency> findAll();

    List<AgencyResultsQueryHelper> findFundingByFundingAgency(Parameters params, int trxTypeId, int trxStatusId);

}
