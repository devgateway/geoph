package org.devgateway.geoph.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author dbianco
 *         created on mar 01 2016.
 */
@NamedQueries({
        @NamedQuery(
                name = "findAllLocations",
                query = "from Location l"
        ),
        @NamedQuery(
                name = "findLocationsByCode",
                query = "from Location l where l.code = :code"
        ),
        @NamedQuery(
                name = "findLocationsByLevel",
                query = "from Location l where l.level = :level"
        )
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Location extends GenericPersistable implements Serializable {

    private String name;

    private int level;

    private String code;

    private Double latitude;

    private Double longitude;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "location_items", joinColumns = {
            @JoinColumn(name = "location_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "items_id",
                    nullable = false, updatable = false) })
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

    public Location(String name, int level, String code, Double latitude, Double longitude) {
        this.name = name;
        this.level = level;
        this.code = code;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(String name, int level, String code, Double latitude,
                    Double longitude, List<Location> items) {
        this.name = name;
        this.level = level;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
