package org.devgateway.geoph.services.repository;

import org.devgateway.geoph.model.ImplementingAgency;
import org.devgateway.geoph.util.Parameters;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 04 2016.
 */
public interface ImplementingAgencyRepository  {

    List<ImplementingAgency> findAll();

    List<Object> findFundingByImplementingAgency(Parameters params);
}
