package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.Location;

public class LocationResultsDao {

    private Long locationId;

    private String name;

    private Double latitude;

    private  Double longitude;

    private Long count;

    private Double amount;

    private Long transactionStatusId;

    private Long transactionTypeId;

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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

    public LocationResultsDao(Long  locationId,String name ,Double latitude,Double longitude ,Long transactionStatusId, Long transactionTypeId, Double amount,Long count) {
        this.locationId = locationId;
        this.name=name;
        this.count = count;
        this.amount = amount;
        this.transactionStatusId = transactionStatusId;
        this.transactionTypeId = transactionTypeId;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
