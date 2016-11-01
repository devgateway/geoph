package org.devgateway.geoph.dao;

import org.devgateway.geoph.enums.FlowTypeEnum;
import org.devgateway.geoph.model.FlowType;

/**
 * @author dbianco
 *         created on nov 01 2016.
 */
public class FlowTypeDao {

    private int flowTypeId;

    private String flowType;

    private Double trxAmount;

    private Long transactionStatusId;

    private Long transactionTypeId;

    public FlowTypeDao() {
    }

    public FlowTypeDao(String flowType, Double trxAmount, Long transactionTypeId, Long transactionStatusId) {
        this.flowTypeId = FlowTypeEnum.valueOf(flowType.toUpperCase()).getId();
        this.flowType = flowType;
        this.trxAmount = trxAmount;
        this.transactionStatusId = transactionStatusId;
        this.transactionTypeId = transactionTypeId;
    }

    public int getFlowTypeId() {
        return flowTypeId;
    }

    public void setFlowTypeId(int flowTypeId) {
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
