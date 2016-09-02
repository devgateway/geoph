package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.Location;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sebas on 9/2/2016.
 */
public class LocationSummaryDao {

    Location location;
    Long projectCount;
    ArrayList<HashMap<String,HashMap>> transactions;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Long getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Long projectCount) {
        this.projectCount = projectCount;
    }

    public ArrayList<HashMap<String, HashMap>> getTransactions() {
        return transactions;
    }

    public LocationSummaryDao(Location location) {
        this.location = location;
    }

    public void setTransactions(ArrayList<HashMap<String, HashMap>> transactions) {
        this.transactions = transactions;
    }
}
