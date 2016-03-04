package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Set;

/**
 * @author dbianco
 *         created on mar 01 2016.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Location extends GenericPersistable implements Serializable {

    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    private int type;

    private String uacs;

    private Double latitude;

    private Double longitude;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "locations")
    private Set<Project> projects;

    public Location() {
    }

    public Location(String name, Long parentId, int type, String uacs, Double latitude, Double longitude) {
        this.name = name;
        this.parentId = parentId;
        this.type = type;
        this.uacs = uacs;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(String name, Long parentId, int type, String uacs, Double latitude,
                    Double longitude, Set<Project> projects) {
        this.name = name;
        this.parentId = parentId;
        this.type = type;
        this.uacs = uacs;
        this.latitude = latitude;
        this.longitude = longitude;
        this.projects = projects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getUacs() {
        return uacs;
    }

    public void setUacs(String uacs) {
        this.uacs = uacs;
    }
}
