package org.devgateway.geoph.persistence;

import org.devgateway.geoph.model.Project;
import org.devgateway.geoph.persistence.repository.ProjectRepository;
import org.devgateway.geoph.services.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Page<Project> findAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }
}
