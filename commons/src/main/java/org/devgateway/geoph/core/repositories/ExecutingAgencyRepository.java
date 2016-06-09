package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.model.ExecutingAgency;

import java.util.List;

/**
 * @author dbianco
 *         created on may 05 2016.
 */
public interface ExecutingAgencyRepository {

    List<ExecutingAgency> findAll();

    List<Object> findFundingByExecutingAgency(Parameters params);

}
