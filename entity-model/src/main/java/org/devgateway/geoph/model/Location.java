package org.devgateway.geoph.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

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
                name = "findLocationsById",
                query = "from Location l where l.id = :id"
        ),
        @NamedQuery(
                name = "findLocationsByCode",
                query = "from Location l where l.code = :code"
        ),
        @NamedQuery(
                name = "findLocationsByLevel",
                query = "from Location l where l.level = :level"
        ),
        @NamedQuery(
                name = "findLocationsByLevelUacsNotNull",
                query = "from Location l where l.level = :level and l.code is not null"
        )
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Location extends GenericPersistable implements Serializable {

    private static final int MUNICIPALITY_LEVEL = 3;
    private String name;

    private int level;

    private String code;

    private Double latitude;

    private Double longitude;


    @Type(type = "org.hibernate.spatial.GeometryType")
    private Point centroid;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    LocationGeometry locationGeometry;




    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "location_items", joinColumns = {
            @JoinColumn(name = "location_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "items_id",
                    nullable = false, updatable = false) })
    private List<Location> items = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "pk.location")
    private Set<ProjectLocation> projects;

    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "province_id")
    private Long provinceId;

    public Location() {
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

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
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

    public Set<ProjectLocation> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectLocation> projects) {
        if(this.projects!=null) {
            this.projects.clear();
            this.projects.addAll(projects);
        } else {
            this.projects = projects;
        }
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long retrieveMunicipalityId() {
        Long ret = null;
        if(level== MUNICIPALITY_LEVEL){
            ret = getId();
        }
        return ret;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Location)){
            return false;
        }
        if (obj == this){
            return true;
        }
        return this.getId().equals(((Location) obj).getId());
    }


    public LocationGeometry getLocationGeometry() {
        return locationGeometry;
    }

    public void setLocationGeometry(LocationGeometry locationGeometry) {
        this.locationGeometry = locationGeometry;
    }

    public int hashCode(){
        return getId().intValue();
    }
}
