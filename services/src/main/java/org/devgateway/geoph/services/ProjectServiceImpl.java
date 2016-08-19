package org.devgateway.geoph.services;

import org.devgateway.geoph.core.repositories.ProjectRepository;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.response.StatsResponse;
import org.devgateway.geoph.core.services.ProjectService;
import org.devgateway.geoph.dao.ProjectStatsResultsDao;
import org.devgateway.geoph.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author dbianco
 *         created on mar 08 2016.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Page<Project> findProjectsByParams(Parameters params) {
        return projectRepository.findProjectsByParams(params);
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public StatsResponse countProjectsByParams(Parameters params) {
        return projectRepository.countProjectsByParams(params);
    }

    @Override
    public Map<String, List<ProjectStatsResultsDao>> getStats(Parameters params) {
        return projectRepository.getStats(params);
    }
}
