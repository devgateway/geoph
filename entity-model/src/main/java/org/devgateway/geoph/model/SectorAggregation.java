package org.devgateway.geoph.model;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author dbianco
 *         created on mar 24 2016.
 */
public class SectorAggregation {

    @JsonProperty(value = "id")
    private long id;

    @JsonProperty(value = "loan")
    private double loan;

    @JsonProperty(value = "grant")
    private double grant;

    @JsonProperty(value = "pmc")
    private double pmc;

    public SectorAggregation() {
    }

    public SectorAggregation(long id, double loan, double grant, double pmc) {
        this.id = id;
        this.loan = loan;
        this.grant = grant;
        this.pmc = pmc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLoan() {
        return loan;
    }

    public void setLoan(double loan) {
        this.loan = loan;
    }

    public double getGrant() {
        return grant;
    }

    public void setGrant(double grant) {
        this.grant = grant;
    }

    public double getPmc() {
        return pmc;
    }

    public void setPmc(double pmc) {
        this.pmc = pmc;
    }
}
