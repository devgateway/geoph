package org.devgateway.geoph.services;

import org.devgateway.geoph.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author dbianco
 *         created on mar 08 2016.
 */
public interface ProjectService {

    Page<Project> findAllProjects(Pageable pageable);
}
