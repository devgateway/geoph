package org.devgateway.geoph.core.services;

import org.devgateway.geoph.core.response.StatsResponse;
import org.devgateway.geoph.model.Project;
import org.devgateway.geoph.core.request.Parameters;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 08 2016.
 */
public interface ProjectService {

    List<Project> findAllProjects();

    Project findById(long id);

    Page<Project> findProjectsByParams(Parameters params);

    Project save(Project project);

    StatsResponse countProjectsByParams(Parameters params);
}
