package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.Agency;

/**
 * @author dbianco
 *         created on jun 08 2016.
 */
public class AgencyResultsDao  {

    private Agency agency;

    private Double trxAmount;

    private Long transactionStatusId;

    private Long transactionTypeId;

    public AgencyResultsDao() {
    }

    public AgencyResultsDao(Agency agency, Double trxAmount, Long transactionTypeId, Long transactionStatusId) {
        this.agency = agency;
        this.trxAmount = trxAmount;
        this.transactionStatusId = transactionStatusId;
        this.transactionTypeId = transactionTypeId;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
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
