package org.devgateway.geoph.util;

import org.devgateway.geoph.model.Location;

/**
 * @author dbianco
 *         created on mar 29 2016.
 */
public class LocationProperty {

    private long id;

    private String name;

    private String code;

    private double latitude;

    private double longitude;

    private long projectCount = 0;

    private long transactionCount = 0;

    private double loan = 0;

    private double grant = 0;

    private double pmc = 0;

    public LocationProperty() {
    }

    public LocationProperty(Location location) {
        this.id = location.getId();
        this.name = location.getName();
        this.code = location.getCode();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(int projectCount) {
        this.projectCount = projectCount;
    }

    public long getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
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

    public void addProjectCount(Long projectCount){
        if(projectCount!=null){
            this.projectCount += projectCount;
        }
    }

    public void addTransactionCount(Long transactionCount){
        if(transactionCount!=null){
            this.transactionCount += transactionCount;
        }
    }

    public void addLoan(Double loan){
        if(loan!=null){
            this.loan += loan;
        }
    }

    public void addGrant(Double grant){
        if(grant!=null){
            this.grant += grant;
        }
    }

    public void addPmc(Double pmc){
        if(pmc!=null){
            this.pmc += pmc;
        }
    }
}
