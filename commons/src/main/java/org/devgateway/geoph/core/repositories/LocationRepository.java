package org.devgateway.geoph.core.repositories;

import com.vividsolutions.jts.geom.Geometry;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.GeometryDao;
import org.devgateway.geoph.dao.LocationResultsDao;
import org.devgateway.geoph.dao.PostGisDao;
import org.devgateway.geoph.dao.ProjectLocationDao;
import org.devgateway.geoph.model.Location;

import java.util.HashMap;
import java.util.List;

/**
 * @author dbianco
 *         created on mar 03 2016.
 */
public interface LocationRepository {

    List<Location> findAll();

    Location findById(long id);

    Location findByCode(String code);

    List<Location> findLocationsByLevel(int level);

    List<Location> findLocationsByLevelUacsNotNull(int level);

    List<Location> findLocationsByParentId(long parentId);

    List<ProjectLocationDao> findProjectLocationsByParams(Parameters params);

    List<LocationResultsDao> findLocationsByParams(Parameters params);

    List<LocationResultsDao> countLocationProjectsByParams(Parameters params);

    List<GeometryDao> getShapesByLevelAndDetail(int level,double detail);
}
