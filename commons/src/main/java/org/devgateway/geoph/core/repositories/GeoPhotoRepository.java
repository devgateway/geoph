package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.model.GeoPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sebas on 8/30/2016.
 */
public interface GeoPhotoRepository extends JpaRepository<GeoPhoto,Long>  {

}
