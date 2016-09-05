package org.devgateway.geoph.dao;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import org.devgateway.geoph.model.Location;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sebas on 9/2/2016.
 */
public class LocationSummaryDao {

    Long id;
    String name;
    Point centroid;
    Geometry geometry;
    Long projectCount=0L;
    HashMap<String,Double> commitments;
    HashMap<String,Double> expenditures;
    HashMap<String,Double> disbursements;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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



    public HashMap<String, Double> getExpenditures() {
        return expenditures;
    }

    public void setExpenditures(HashMap<String, Double> expenditures) {
        this.expenditures = expenditures;
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

    public HashMap<String, Double> getExpenditure() {
        if (expenditures==null){
            expenditures=new HashMap<>();
        }
        return expenditures;
    }



    public HashMap<String, Double> getDisbursements() {
        if (disbursements==null){
            disbursements=new HashMap<>();
        }
        return disbursements;
    }



    public void setCommitments(HashMap<String, Double> commitments) {
        this.commitments = commitments;
    }

    public void setExpenditure(HashMap<String, Double> expenditures) {
        this.expenditures = expenditures;
    }

    public void setDisbursements(HashMap<String, Double> disbursements) {
        this.disbursements = disbursements;
    }
}
