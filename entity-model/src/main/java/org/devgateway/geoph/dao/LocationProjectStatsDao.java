package org.devgateway.geoph.dao;

import com.vividsolutions.jts.geom.Geometry;

/**
 * Created by sebas on 9/5/2016.
 */
public class LocationProjectStatsDao extends LocationDao {

    private Double physicalProgress;

    private Long projectCount;

    private Integer level;


    public LocationProjectStatsDao() {
    }

    public LocationProjectStatsDao(Long id, String name, Geometry geometry, Long projectCount, Double physicalProgress) {
        this.id = id;
        this.physicalProgress = physicalProgress;
        this.projectCount = projectCount;
        this.geometry=geometry;
        this.name=name;
    }

    public Double getPhysicalProgress() {
        return physicalProgress;
    }

    public void setPhysicalProgress(Double physicalProgress) {
        this.physicalProgress = physicalProgress;
    }

    public Long getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Long projectCount) {
        this.projectCount = projectCount;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
