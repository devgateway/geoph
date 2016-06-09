package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.dao.GeoPhotoGeometryHelper;
import org.devgateway.geoph.model.GeoPhotoSource;

import java.util.List;

/**
 * @author dbianco
 *         created on abr 29 2016.
 */
public interface GeoPhotoRepository {
    List<GeoPhotoSource> findAllGeoPhotoSources();

    GeoPhotoSource findByCode(String name);

    List<GeoPhotoGeometryHelper> getGeoPhotoGeometryByKmlId(long kmlId);
}
