package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.GeoPhotoSource;
import org.devgateway.geoph.util.GeoPhotoGeometryHelper;
import org.springframework.data.jpa.repository.JpaRepository;

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
