package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.Sector;

/**
 * @author dbianco
 *         created on ago 31 2016.
 */
public class SectorResultsDao {

    private Sector sector;

    private Double trxAmount;

    private long projectCount;

    public SectorResultsDao() {
    }

    public SectorResultsDao(Sector sector, Double trxAmount, long projectCount) {
        this.sector = sector;
        this.trxAmount = trxAmount;
        this.projectCount = projectCount;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
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
