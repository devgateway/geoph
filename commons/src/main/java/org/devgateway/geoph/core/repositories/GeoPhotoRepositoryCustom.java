package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.GeoPhotoDao;

import java.util.List;

/**
 * @author dbianco
 *         created on sep 02 2016.
 */
public interface GeoPhotoRepositoryCustom {

    List<GeoPhotoDao> findGeoPhotosByParams(Parameters parameters);

}
