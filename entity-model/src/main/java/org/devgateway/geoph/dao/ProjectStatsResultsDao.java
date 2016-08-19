package org.devgateway.geoph.dao;

/**
 * @author dbianco
 *         created on ago 19 2016.
 */
public class ProjectStatsResultsDao {

    private Double trxAmount;

    private Long projectCount;

    public ProjectStatsResultsDao(Double trxAmount, Long projectCount) {
        this.trxAmount = trxAmount;
        this.projectCount = projectCount;
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
}
