package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.Location;

/**
 * @author dbianco
 *         created on jun 08 2016.
 */
public class LocationResultsDao {

    private Location location;

    private Long projectCount;

    private Double trxAmount;

    private Long trxCount;

    private Double physicalProgressAmount;

    private Long physicalProgressCount;

    public LocationResultsDao(Location location, Long projectCount) {
        this.location = location;
        this.projectCount = projectCount;
    }

    public LocationResultsDao(Location location, Double physicalProgressAmount, Long physicalProgressCount) {
        this.location = location;
        this.physicalProgressAmount = physicalProgressAmount;
        this.physicalProgressCount = physicalProgressCount;
    }

    public LocationResultsDao(Location location, Double trxAmount, Long trxCount, Double physicalProgressAmount, Long physicalProgressCount) {
        this.location = location;
        this.trxAmount = trxAmount;
        this.trxCount = trxCount;
        this.physicalProgressAmount = physicalProgressAmount;
        this.physicalProgressCount = physicalProgressCount;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Long getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Long projectCount) {
        this.projectCount = projectCount;
    }

    public Double getTrxAmount() {
        return trxAmount;
    }

    public void setTrxAmount(Double trxAmount) {
        this.trxAmount = trxAmount;
    }

    public Long getTrxCount() {
        return trxCount;
    }

    public void setTrxCount(Long trxCount) {
        this.trxCount = trxCount;
    }

    public Double getPhysicalProgressAmount() {
        return physicalProgressAmount;
    }

    public void setPhysicalProgressAmount(Double physicalProgressAmount) {
        this.physicalProgressAmount = physicalProgressAmount;
    }

    public Long getPhysicalProgressCount() {
        return physicalProgressCount;
    }

    public void setPhysicalProgressCount(Long physicalProgressCount) {
        this.physicalProgressCount = physicalProgressCount;
    }

    public Double getPhysicalProgressAverage() {
        Double ret = 0D;
        if(physicalProgressAmount!=null && physicalProgressCount!=null && physicalProgressCount!=0D){
            ret=physicalProgressAmount/physicalProgressCount;
        }
        return ret;
    }
}
