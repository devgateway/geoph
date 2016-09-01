package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.PhysicalStatus;

/**
 * @author dbianco
 *         created on jun 08 2016.
 */
public class PhysicalStatusDao {

    private PhysicalStatus physicalStatus;

    private Double trxAmount;

    private long projectCount;

    public PhysicalStatusDao() {
    }

    public PhysicalStatusDao(PhysicalStatus physicalStatus, Double trxAmount, long projectCount) {
        this.physicalStatus = physicalStatus;
        this.trxAmount = trxAmount;
        this.projectCount = projectCount;
    }

    public PhysicalStatus getPhysicalStatus() {
        return physicalStatus;
    }

    public void setPhysicalStatus(PhysicalStatus physicalStatus) {
        this.physicalStatus = physicalStatus;
    }

    public Double getTrxAmount() {
        return trxAmount;
    }

    public void setTrxAmount(Double trxAmount) {
        this.trxAmount = trxAmount;
    }

    public long getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(long projectCount) {
        this.projectCount = projectCount;
    }
}
