package org.devgateway.geoph.dao;

/**
 * @author dbianco
 *         created on ago 19 2016.
 */
public class ProjectStatsResultsDao {

    private double trxAmount;

    private int projectCount;

    private int statusId;

    private int typeId;

    public ProjectStatsResultsDao(Double trxAmount, Long projectCount, Long statusId, Long typeId) {
        this.trxAmount = trxAmount!=null?trxAmount:0D;
        this.projectCount = projectCount!=null?projectCount.intValue():0;
        this.statusId = statusId.intValue();
        this.typeId = typeId.intValue();
    }

    public double getTrxAmount() {
        return trxAmount;
    }

    public void setTrxAmount(double trxAmount) {
        this.trxAmount = trxAmount;
    }

    public int getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(int projectCount) {
        this.projectCount = projectCount;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
