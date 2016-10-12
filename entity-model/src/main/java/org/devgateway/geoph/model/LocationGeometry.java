package org.devgateway.geoph.model;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * Created by sebas on 9/5/2016.
 */
@Entity
public class LocationGeometry extends GenericPersistable implements Serializable {

    @Type(type = "org.hibernate.spatial.GeometryType")
    com.vividsolutions.jts.geom.Geometry geometry;

    @OneToOne
    Location location;

    public com.vividsolutions.jts.geom.Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(com.vividsolutions.jts.geom.Geometry geometry) {
        this.geometry = geometry;
    }

}
