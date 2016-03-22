package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.Location;
import org.devgateway.geoph.util.GeometryDetailLevel;
import org.devgateway.geoph.util.PostGisHelper;
import org.devgateway.geoph.util.Parameters;

import java.util.List;

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

    List<PostGisHelper> getRegionShapesWithDetail(GeometryDetailLevel detail);
}
