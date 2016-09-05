package org.devgateway.geoph.dao;

import com.vividsolutions.jts.geom.Geometry;

/**
 * Created by sebas on 9/5/2016.
 */
public class GeometryDao {
    Long locationId;
    Geometry geometry;

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public GeometryDao(Long locationId, Geometry geometry) {
        this.locationId = locationId;
        this.geometry = geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }


}
