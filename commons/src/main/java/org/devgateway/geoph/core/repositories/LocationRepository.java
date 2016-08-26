package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.LocationResultsDao;
import org.devgateway.geoph.dao.PostGisDao;
import org.devgateway.geoph.dao.ProjectLocationDao;
import org.devgateway.geoph.model.Location;

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

    List<LocationResultsDao> findLocationsByParamsTypeStatus(Parameters params, int trxTypeId, int trxStatusId);

    List<LocationResultsDao> countLocationProjectsByParams(Parameters params);

    List<PostGisDao> getRegionShapesWithDetail(double detail);

    List<PostGisDao> getProvinceShapesWithDetail(double detail);

    List<PostGisDao> getMunicipalityShapesWithDetail(double detail);

}
