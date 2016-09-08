package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.Sector;

/**
 * @author dbianco
 *         created on ago 31 2016.
 */
public class SectorResultsDao {

    private Sector sector;

    private Double trxAmount;

    private Long transactionStatusId;

    private Long transactionTypeId;

    public SectorResultsDao() {
    }

    public SectorResultsDao(Sector sector, Double trxAmount, Long transactionStatusId, Long transactionTypeId) {
        this.sector = sector;
        this.trxAmount = trxAmount;
        this.transactionStatusId = transactionStatusId;
        this.transactionTypeId = transactionTypeId;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
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
