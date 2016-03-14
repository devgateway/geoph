package org.devgateway.geoph.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author dbianco
 *         created on mar 01 2016.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Location extends GenericPersistable implements Serializable {

    private String name;

    private int type;

    private String code;

    private Double latitude;

    private Double longitude;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private List<Location> items = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "project_location", joinColumns = {
            @JoinColumn(name = "location_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "project_id",
                    nullable = false, updatable = false) })
    private Set<Project> projects;

    public Location() {
    }

    public Location(String name, int type, String code, Double latitude, Double longitude) {
        this.name = name;
        this.type = type;
        this.code = code;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(String name, int type, String code, Double latitude,
                    Double longitude, List<Location> items) {
        this.name = name;
        this.type = type;
        this.code = code;
        this.latitude = latitude;
        this.longitude = longitude;
        this.items = items;
    }

    @JsonIgnore
    public boolean isNew(){
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Location> getItems() {
        return items;
    }

    public void setItems(List<Location> items) {
        this.items = items;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
