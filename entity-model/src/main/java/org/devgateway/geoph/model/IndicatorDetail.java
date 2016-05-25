package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author dbianco
 *         created on mar 25 2016.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@IdClass(IndicatorDetailId.class)
public class IndicatorDetail implements Serializable {

    @Id
    @Column(name = "indicator_id")
    private long indicatorId;

    @Id
    @Column(name = "location_id")
    private long locationId;

    private String value;

    public long getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(long indicatorId) {
        this.indicatorId = indicatorId;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
