package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.Location;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sebas on 9/2/2016.
 */
public class LocationSummaryDao {

    Location location;
    Long projectCount=0L;
    HashMap<String,Double> commitments;
    HashMap<String,Double> expenditures;
    HashMap<String,Double> disbursements;

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


    public void addProjectCount(Long projectCount) {
        this.projectCount=this.projectCount + projectCount;
    }

    public HashMap<String, Double> getCommitments() {
        if (commitments==null){
            commitments=new HashMap<>();
        }
        return commitments;
    }

    public void setCommitments(HashMap<String, Double> commitments) {
          this.commitments = commitments;
    }

    public HashMap<String, Double> getExpenditure() {
        return expenditures;
    }

    public void setExpenditure(HashMap<String, Double> expenditures) {
        if (expenditures==null){
            expenditures=new HashMap<>();
        }
        this.expenditures = expenditures;
    }

    public HashMap<String, Double> getDisbursements() {
        if (disbursements==null){
            disbursements=new HashMap<>();
        }
        return disbursements;
    }

    public void setDisbursements(HashMap<String, Double> disbursements) {
        this.disbursements = disbursements;
    }
}
