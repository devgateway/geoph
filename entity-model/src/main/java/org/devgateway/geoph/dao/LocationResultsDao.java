package org.devgateway.geoph.dao;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

public class LocationResultsDao {

    private Long locationId;

    private String name;

    private Point centroid;

    private Geometry geometry;

    private Long count;

    private Double amount;

    private Long transactionStatusId;

    private Long transactionTypeId;

    public LocationResultsDao(Long  locationId, String name, Geometry centroid, Long transactionStatusId, Long transactionTypeId, Double amount, Long count) {
        this.locationId = locationId;
        this.name=name;
        this.count = count;
        this.amount = amount;
        this.transactionStatusId = transactionStatusId;
        this.transactionTypeId = transactionTypeId;
        this.centroid= (Point) centroid;
    }

    public LocationResultsDao(Long  locationId, String name,Geometry centroid, Geometry geometry, Long transactionStatusId, Long transactionTypeId, Double amount, Long count) {
        this.locationId = locationId;
        this.name=name;
        this.count = count;
        this.amount = amount;
        this.transactionStatusId = transactionStatusId;
        this.transactionTypeId = transactionTypeId;
        this.centroid= (Point) centroid;
        this.geometry=geometry;
    }

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

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
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

}
