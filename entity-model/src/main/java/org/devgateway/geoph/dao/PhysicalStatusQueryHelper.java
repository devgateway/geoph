package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.PhysicalStatus;

/**
 * @author dbianco
 *         created on jun 08 2016.
 */
public class PhysicalStatusQueryHelper implements ResultQueryHelper {

    private PhysicalStatus physicalStatus;

    private Long projectCount;

    private Long transactionCount;

    private Double transactionAmount;

    public PhysicalStatusQueryHelper() {
    }

    public PhysicalStatusQueryHelper(PhysicalStatus physicalStatus, Long projectCount, Double transactionAmount, Long transactionCount) {
        this.physicalStatus = physicalStatus;
        this.projectCount = projectCount;
        this.transactionCount = transactionCount;
        this.transactionAmount = transactionAmount;
    }

    public PhysicalStatus getPhysicalStatus() {
        return physicalStatus;
    }

    public void setPhysicalStatus(PhysicalStatus physicalStatus) {
        this.physicalStatus = physicalStatus;
    }

    public Long getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Long projectCount) {
        this.projectCount = projectCount;
    }

    public Long getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Long transactionCount) {
        this.transactionCount = transactionCount;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
