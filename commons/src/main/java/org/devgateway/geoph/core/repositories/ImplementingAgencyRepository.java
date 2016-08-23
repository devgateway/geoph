package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.model.ImplementingAgency;
import org.devgateway.geoph.model.ProjectAgency;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 04 2016.
 */
public interface ImplementingAgencyRepository {

    List<ImplementingAgency> findAll();

    ImplementingAgency findById(Long id);

    Integer countAll();

    List<ProjectAgency> findFundingByImplementingAgency(Parameters params);
}
