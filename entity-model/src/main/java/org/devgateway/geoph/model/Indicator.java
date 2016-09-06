package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 * @author dbianco
 *         created on mar 25 2016.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Indicator extends GenericPersistable implements Serializable {

    private String name;

    private String description;

    private String colorScheme;

    private String admLevel;

    private String unit;

    @OneToMany(fetch = FetchType.LAZY)
    List<IndicatorDetail> details;


    public Indicator() {
    }

    public void setId(Long id){
        super.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColorScheme() {
        return colorScheme;
    }

    public void setColorScheme(String colorScheme) {
        this.colorScheme = colorScheme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdmLevel() {
        return admLevel;
    }

    public void setAdmLevel(String admLevel) {
        this.admLevel = admLevel;
    }

    public String getUnit() {
        return unit;
    }

    public List<IndicatorDetail> getDetails() {
        return details;
    }

    public void setDetails(List<IndicatorDetail> details) {
        this.details = details;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
