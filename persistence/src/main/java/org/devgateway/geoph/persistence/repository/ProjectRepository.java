package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @author dbianco
 *         created on mar 03 2016.
 */
public interface ProjectRepository {

    List<Project> findAll();

    Page<Project> findProjectsByParams(Map<String, String[]> params, Pageable pageable);
}
