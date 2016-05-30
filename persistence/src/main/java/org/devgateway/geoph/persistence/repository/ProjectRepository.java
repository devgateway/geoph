package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.Project;
import org.devgateway.geoph.response.StatsResponse;
import org.devgateway.geoph.util.Parameters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
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

    Project save(Project project);

    List<Double> getFinancialAmountBoundaries();

    StatsResponse countProjectsByParams(Parameters params);

    List<String> getImpPeriodBoundaries();

    List<String> getGrantPeriodBoundaries();
}
