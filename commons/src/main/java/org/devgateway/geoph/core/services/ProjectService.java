package org.devgateway.geoph.core.services;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.response.StatsResponse;
import org.devgateway.geoph.dao.ProjectMiniDao;
import org.devgateway.geoph.dao.ProjectMiniSummaryDao;
import org.devgateway.geoph.dao.ProjectPageDao;
import org.devgateway.geoph.dao.ProjectStatsResultsDao;
import org.devgateway.geoph.model.Project;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author dbianco
 *         created on mar 08 2016.
 */
public interface ProjectService {

    List<Project> findAllProjects();

    ProjectPageDao findById(long id);

    Page<ProjectMiniSummaryDao> findProjectsByParams(Parameters params);

    Page<ProjectMiniDao> findProjectMiniByParams(Parameters params);

    Project save(Project project);

    StatsResponse countProjectsByParams(Parameters params);

    Map<String, List<ProjectStatsResultsDao>> getStats(Parameters params);
}
