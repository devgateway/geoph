package org.devgateway.geoph.services.repository;

import org.devgateway.geoph.model.Location;
import org.devgateway.geoph.util.PostGisHelper;
import org.devgateway.geoph.util.Parameters;

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

    List<Object> findLocationsByParams(Parameters params, int trxTypeId, int trxStatusId);

    List<Object> countLocationProjectsByParams(Parameters params);

    List<PostGisHelper> getRegionShapesWithDetail(double detail);

    List<PostGisHelper> getProvinceShapesWithDetail(double detail);

    List<PostGisHelper> getMunicipalityShapesWithDetail(double detail);

    Location findParentLocation(long locationId);

    Location findGrandParentLocation(long locationId);

}
