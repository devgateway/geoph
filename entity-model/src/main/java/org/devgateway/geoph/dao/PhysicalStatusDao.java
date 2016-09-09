package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.PhysicalStatus;

/**
 * @author dbianco
 *         created on jun 08 2016.
 */
public class PhysicalStatusDao {

    private PhysicalStatus physicalStatus;

    private Double trxAmount;

    private Long transactionStatusId;

    private Long transactionTypeId;

    public PhysicalStatusDao() {
    }

    public PhysicalStatusDao(PhysicalStatus physicalStatus, Double trxAmount, Long transactionStatusId, Long transactionTypeId) {
        this.physicalStatus = physicalStatus;
        this.trxAmount = trxAmount;
        this.transactionStatusId = transactionStatusId;
        this.transactionTypeId = transactionTypeId;
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

    public Long getTransactionStatusId() {
        return transactionStatusId;
    }

    public void setTransactionStatusId(Long transactionStatusId) {
        this.transactionStatusId = transactionStatusId;
    }

    public Long getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(Long transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }
}
