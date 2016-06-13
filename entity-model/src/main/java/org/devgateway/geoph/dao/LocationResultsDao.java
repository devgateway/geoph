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

    private Double actualPhysicalProgressAmount;

    private Long actualPhysicalProgressCount;

    private Double targetPhysicalProgressAmount;

    private Long targetPhysicalProgressCount;

    public LocationResultsDao(Location location, Long projectCount) {
        this.location = location;
        this.projectCount = projectCount;
    }

    public LocationResultsDao(Location location, Double actualPhysicalProgressAmount, Long actualPhysicalProgressCount, Double targetPhysicalProgressAmount, Long targetPhysicalProgressCount) {
        this.location = location;
        this.actualPhysicalProgressAmount = actualPhysicalProgressAmount;
        this.actualPhysicalProgressCount = actualPhysicalProgressCount;
        this.targetPhysicalProgressAmount = targetPhysicalProgressAmount;
        this.targetPhysicalProgressCount = targetPhysicalProgressCount;
    }

    public LocationResultsDao(Location location, Double trxAmount, Long trxCount, Double actualPhysicalProgressAmount, Long actualPhysicalProgressCount, Double targetPhysicalProgressAmount, Long targetPhysicalProgressCount) {
        this.location = location;
        this.trxAmount = trxAmount;
        this.trxCount = trxCount;
        this.actualPhysicalProgressAmount = actualPhysicalProgressAmount;
        this.actualPhysicalProgressCount = actualPhysicalProgressCount;
        this.targetPhysicalProgressAmount = targetPhysicalProgressAmount;
        this.targetPhysicalProgressCount = targetPhysicalProgressCount;
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

    public Double getActualPhysicalProgressAmount() {
        return actualPhysicalProgressAmount;
    }

    public void setActualPhysicalProgressAmount(Double actualPhysicalProgressAmount) {
        this.actualPhysicalProgressAmount = actualPhysicalProgressAmount;
    }

    public Long getActualPhysicalProgressCount() {
        return actualPhysicalProgressCount;
    }

    public void setActualPhysicalProgressCount(Long actualPhysicalProgressCount) {
        this.actualPhysicalProgressCount = actualPhysicalProgressCount;
    }

    public Double getTargetPhysicalProgressAmount() {
        return targetPhysicalProgressAmount;
    }

    public void setTargetPhysicalProgressAmount(Double targetPhysicalProgressAmount) {
        this.targetPhysicalProgressAmount = targetPhysicalProgressAmount;
    }

    public Long getTargetPhysicalProgressCount() {
        return targetPhysicalProgressCount;
    }

    public void setTargetPhysicalProgressCount(Long targetPhysicalProgressCount) {
        this.targetPhysicalProgressCount = targetPhysicalProgressCount;
    }

    public Double getActualPhysicalProgressAverage() {
        Double ret = 0D;
        if(actualPhysicalProgressAmount!=null && actualPhysicalProgressCount!=null && actualPhysicalProgressCount!=0D){
            ret=actualPhysicalProgressAmount/actualPhysicalProgressCount;
        }
        return ret;
    }

    public Double getTargetPhysicalProgressAverage() {
        Double ret = 0D;
        if(targetPhysicalProgressAmount!=null && targetPhysicalProgressCount!=null && targetPhysicalProgressCount!=0D){
            ret=targetPhysicalProgressAmount/targetPhysicalProgressCount;
        }
        return ret;
    }
}
