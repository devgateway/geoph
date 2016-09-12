package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.Project;

/**
 * @author dbianco
 *         created on ago 26 2016.
 */
public class ProjectMiniDao {

    private Long id;

    private String title;

    private String fundingAgency;

    private Double trxAmount;

    private Long transactionStatusId;

    private Long transactionTypeId;

    public ProjectMiniDao(){}

    public ProjectMiniDao(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public ProjectMiniDao(Project project) {
        this.id = project.getId();
        this.title = project.getTitle();
    }

    public ProjectMiniDao(Long id, String title, String fundingAgency, Double trxAmount, Long transactionStatusId, Long transactionTypeId) {
        this.id = id;
        this.title = title;
        this.fundingAgency = fundingAgency;
        this.trxAmount = trxAmount;
        this.transactionStatusId = transactionStatusId;
        this.transactionTypeId = transactionTypeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFundingAgency() {
        return fundingAgency;
    }

    public void setFundingAgency(String fundingAgency) {
        this.fundingAgency = fundingAgency;
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

    public Double getTrxAmount() {
        return trxAmount;
    }

    public void setTrxAmount(Double trxAmount) {
        this.trxAmount = trxAmount;
    }
}
