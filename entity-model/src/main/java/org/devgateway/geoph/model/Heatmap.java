package org.devgateway.geoph.model;

import com.vividsolutions.jts.geom.Geometry;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by dbianco on 02/02/2018.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Heatmap extends GenericPersistable implements Serializable {

    private int dn;

    @Type(type = "org.hibernate.spatial.GeometryType")
    private Geometry geom;

    public int getDn() {
        return dn;
    }

    public void setDn(int dn) {
        this.dn = dn;
    }

    public Geometry getGeom() {
        return geom;
    }

    public void setGeom(Geometry geom) {
        this.geom = geom;
    }
}
