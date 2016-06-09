package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.Sector;

/**
 * @author dbianco
 *         created on jun 08 2016.
 */
public class SectorResultsQueryHelper implements ResultQueryHelper {

        private Sector sector;

        private Long projectCount;

        private Long transactionCount;

        private Double transactionAmount;

        public SectorResultsQueryHelper() {
        }

        public SectorResultsQueryHelper(Sector sector, Long projectCount, Double transactionAmount, Long transactionCount) {
        this.sector = sector;
        this.projectCount = projectCount;
        this.transactionCount = transactionCount;
        this.transactionAmount = transactionAmount;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Long getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Long projectCount) {
        this.projectCount = projectCount;
    }

    public Long getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Long transactionCount) {
        this.transactionCount = transactionCount;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
