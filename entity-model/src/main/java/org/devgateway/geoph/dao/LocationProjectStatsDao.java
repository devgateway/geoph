package org.devgateway.geoph.dao;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

/**
 * Created by sebas on 9/5/2016.
 */
public class LocationProjectStatsDao extends  LocationDao {

    Double physicalProgres;
    Long projectCount;
    Integer level;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public Double getPhysicalProgres() {
        return physicalProgres;
    }

    public void setPhysicalProgres(Double physicalProgres) {
        this.physicalProgres = physicalProgres;
    }

    public Long getProjectCount() {
        return projectCount;
    }

    public LocationProjectStatsDao() {
    }

    public void setProjectCount(Long projectCount) {
        this.projectCount = projectCount;
    }
//long, java.lang.String, com.vividsolutions.jts.geom.Geometry, long, double [
    public LocationProjectStatsDao(Long id, String name, Geometry geometry, Long projectCount, Double physicalProgres) {
        this.id = id;
        this.physicalProgres = physicalProgres;
        this.projectCount = projectCount;
        this.geometry=geometry;
        this.name=name;
    }
}
