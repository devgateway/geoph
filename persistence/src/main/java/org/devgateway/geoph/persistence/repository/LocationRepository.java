package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.Location;
import org.devgateway.geoph.util.Parameters;
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
public interface LocationRepository {

    List<Location> findAll();

    Location findByCode(String code);

    List<Location> findLocationsByLevel(int level);

    List<Location> findLocationsByParentId(long parentId);

    List<Location> findLocationsByParams(Parameters params);
}
