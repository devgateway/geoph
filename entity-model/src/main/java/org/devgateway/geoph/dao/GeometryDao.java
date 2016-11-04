package org.devgateway.geoph.dao;

import com.vividsolutions.jts.geom.Geometry;

/**
 * Created by sebas on 9/5/2016.
 */
public class GeometryDao {

    Long locationId;

    String name;

    Geometry geometry;

    public GeometryDao(Long locationId, String name, Geometry geometry) {
        this.locationId = locationId;
        this.geometry = geometry;
        this.name = name;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
