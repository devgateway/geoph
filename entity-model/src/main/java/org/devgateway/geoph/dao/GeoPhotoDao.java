package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.GeoPhoto;

/**
 * @author dbianco
 *         created on sep 02 2016.
 */
public class GeoPhotoDao {

    private GeoPhoto geoPhoto;

    public GeoPhotoDao(GeoPhoto geoPhoto) {
        this.geoPhoto = geoPhoto;
    }

    public GeoPhoto getGeoPhoto() {
        return geoPhoto;
    }

    public void setGeoPhoto(GeoPhoto geoPhoto) {
        this.geoPhoto = geoPhoto;
    }
}
