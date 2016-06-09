package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.ImplementingAgency;
import org.devgateway.geoph.util.Parameters;
import org.devgateway.geoph.util.queries.AgencyResultsQueryHelper;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 04 2016.
 */
public interface ImplementingAgencyRepository  {

    List<ImplementingAgency> findAll();

    Integer count();

    List<AgencyResultsQueryHelper> findFundingByImplementingAgency(Parameters params, int trxTypeId, int trxStatusId);
}
