package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.LocationResultsQueryHelper;
import org.devgateway.geoph.dao.PostGisHelper;
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

    List<Location> findLocationsByParentId(long parentId);

    List<Location> findLocationsByParams(Parameters params);

    List<LocationResultsQueryHelper> findLocationsByParamsTypeStatus(Parameters params, int trxTypeId, int trxStatusId);

    List<Object> countLocationProjectsByParams(Parameters params);

    List<PostGisHelper> getRegionShapesWithDetail(double detail);

    List<PostGisHelper> getProvinceShapesWithDetail(double detail);

    List<PostGisHelper> getMunicipalityShapesWithDetail(double detail);

}
