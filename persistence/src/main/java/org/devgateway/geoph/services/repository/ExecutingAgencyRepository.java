package org.devgateway.geoph.services.repository;

import org.devgateway.geoph.model.ExecutingAgency;
import org.devgateway.geoph.util.Parameters;

import java.util.List;

/**
 * @author dbianco
 *         created on may 05 2016.
 */
public interface ExecutingAgencyRepository {

    List<ExecutingAgency> findAll();

    List<Object> findFundingByExecutingAgency(Parameters params);

}
