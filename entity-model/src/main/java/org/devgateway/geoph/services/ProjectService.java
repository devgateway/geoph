package org.devgateway.geoph.services;

import org.devgateway.geoph.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * @author dbianco
 *         created on mar 08 2016.
 */
public interface ProjectService {

    Page<Project> findAllProjects(Pageable pageable);

    Page<Project> findProjectsByParams(String params, Pageable pageable);
}
