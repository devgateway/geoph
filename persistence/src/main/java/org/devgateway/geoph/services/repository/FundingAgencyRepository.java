package org.devgateway.geoph.services.repository;

import org.devgateway.geoph.model.FundingAgency;
import org.devgateway.geoph.util.Parameters;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 04 2016.
 */
public interface FundingAgencyRepository {

    List<FundingAgency> findAll();

    List<Object> findFundingByFundingAgency(Parameters params);

}
