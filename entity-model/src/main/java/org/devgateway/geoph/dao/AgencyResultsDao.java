package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.Agency;

/**
 * @author dbianco
 *         created on jun 08 2016.
 */
public class AgencyResultsDao  {

    private Agency agency;

    private Double trxAmount;

    private long projectCount;

    public AgencyResultsDao() {
    }

    public AgencyResultsDao(Agency agency, Double trxAmount, long projectCount) {
        this.agency = agency;
        this.trxAmount = trxAmount;
        this.projectCount = projectCount;
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

    public long getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(long projectCount) {
        this.projectCount = projectCount;
    }
}
