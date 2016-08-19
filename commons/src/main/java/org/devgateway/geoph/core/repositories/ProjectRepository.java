package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.response.StatsResponse;
import org.devgateway.geoph.dao.ProjectStatsResultsDao;
import org.devgateway.geoph.model.Project;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author dbianco
 *         created on mar 03 2016.
 */
public interface ProjectRepository {

    List<Project> findAll();

    Project findById(long id);

    Page<Project> findProjectsByParams(Parameters params);

    Map<String, ProjectStatsResultsDao> getStats(Parameters params);

    Project save(Project project);

    List<Double> getFinancialAmountBoundaries();

    StatsResponse countProjectsByParams(Parameters params);

    List<String> getImpPeriodBoundaries();

    List<String> getGrantPeriodBoundaries();

    List<Double> getTargetPhysicalProgressPeriod();

    List<Double> getActualPhysicalProgressPeriod();

    List<Double> getReachedPhysicalProgressPeriod();
}
