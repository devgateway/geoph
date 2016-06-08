package org.devgateway.geoph.services.repository;

import org.devgateway.geoph.model.Project;
import org.devgateway.geoph.response.StatsResponse;
import org.devgateway.geoph.util.Parameters;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 03 2016.
 */
public interface ProjectRepository {

    List<Project> findAll();

    Project findById(long id);

    Page<Project> findProjectsByParams(Parameters params);

    Project save(Project project);

    List<Double> getFinancialAmountBoundaries();

    StatsResponse countProjectsByParams(Parameters params);

    List<String> getImpPeriodBoundaries();

    List<String> getGrantPeriodBoundaries();

    List<Float> getTargetReachedPeriodBoundaries();
}
