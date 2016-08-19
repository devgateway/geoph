package org.devgateway.geoph.dao;

import org.devgateway.geoph.enums.TransactionStatusEnum;
import org.devgateway.geoph.enums.TransactionTypeEnum;

/**
 * @author dbianco
 *         created on ago 19 2016.
 */
public class ProjectStatsResultsDao {

    private Double trxAmount;

    private Long projectCount;

    private String status;

    private String type;

    public ProjectStatsResultsDao(Double trxAmount, Long projectCount, Long statusId, Long typeId) {
        this.trxAmount = trxAmount!=null?trxAmount:0D;
        this.projectCount = projectCount!=null?projectCount:0;
        this.status = statusId!=null?TransactionStatusEnum.getEnumById(statusId).getName():"undefined";
        this.type = typeId!=null? TransactionTypeEnum.getEnumById(typeId).getName():"undefined";
    }

    public Double getTrxAmount() {
        return trxAmount;
    }

    public void setTrxAmount(Double trxAmount) {
        this.trxAmount = trxAmount;
    }

    public Long getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Long projectCount) {
        this.projectCount = projectCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
