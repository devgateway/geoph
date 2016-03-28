package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author dbianco
 *         created on mar 25 2016.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Indicator extends GenericPersistable implements Serializable {

    private String name;

    private String description;

    private String unit;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "indicator")
    private Set<IndicatorDetail> details = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Set<IndicatorDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<IndicatorDetail> details) {
        this.details = details;
    }
}
