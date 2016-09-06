package org.devgateway.geoph.dao;

import com.vividsolutions.jts.geom.Geometry;

/**
 * Created by sebas on 9/6/2016.
 */
public class LocationDao {

    Long id;
    String name;
    Geometry geometry;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
