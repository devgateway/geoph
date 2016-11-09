package org.devgateway.geoph.dao;

import org.devgateway.geoph.enums.FlowTypeEnum;

/**
 * @author dbianco
 *         created on nov 01 2016.
 */
public class FlowTypeDao {

    private Long flowTypeId;

    private String flowType;

    private Double trxAmount;

    private Long transactionStatusId;

    private Long transactionTypeId;

    public FlowTypeDao() {
    }

    public FlowTypeDao(String flowType, Double trxAmount, Long transactionTypeId, Long transactionStatusId) {
        this.flowTypeId = Long.valueOf(FlowTypeEnum.valueOf(flowType.toUpperCase()).getId());
        this.flowType = flowType;
        this.trxAmount = trxAmount;
        this.transactionStatusId = transactionStatusId;
        this.transactionTypeId = transactionTypeId;
    }

    public Long getFlowTypeId() {
        return flowTypeId;
    }

    public void setFlowTypeId(Long flowTypeId) {
        this.flowTypeId = flowTypeId;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
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
