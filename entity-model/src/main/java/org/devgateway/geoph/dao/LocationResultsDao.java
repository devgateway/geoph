package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.Location;

public class LocationResultsDao {

    private Location location;

    private Long count;

    private Double amount;

    private Long transactionStatusId;

    private Long transactionTypeId;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public LocationResultsDao(Location location,  Long transactionStatusId, Long transactionTypeId, Double amount,Long count) {
        this.location = location;
        this.count = count;
        this.amount = amount;
        this.transactionStatusId = transactionStatusId;
        this.transactionTypeId = transactionTypeId;
    }


}
