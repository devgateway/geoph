package org.devgateway.geoph.dao;

/**
 * @author dbianco
 *         created on ago 19 2016.
 */
public class ProjectStatsResultsDao {

    private Double trxAmount;

    private Long projectCount;

    private Long statusId;

    private Long typeId;

    public ProjectStatsResultsDao(Double trxAmount, Long projectCount, Long statusId, Long typeId) {
        this.trxAmount = trxAmount!=null?trxAmount:0D;
        this.projectCount = projectCount!=null?projectCount:0;
        this.statusId = statusId;
        this.typeId = typeId;
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

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
