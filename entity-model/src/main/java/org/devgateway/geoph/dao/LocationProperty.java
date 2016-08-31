package org.devgateway.geoph.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.devgateway.geoph.model.Location;
import org.devgateway.geoph.model.Project;

import java.util.HashMap;
import java.util.Map;

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

    private Map<Long, Project> projectMap = new HashMap<>();

    private Long projectCount = 0L;

    private Long transactionCount = 0L;

    @JsonIgnore
    private Map<Long, Double> physicalProgress = new HashMap<>();

    private Map<String, Double> commitments = new HashMap<>();

    private Map<String, Double> disbursements = new HashMap<>();

    private Map<String, Double> expenditures = new HashMap<>();


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

    public Long getProjectCount() {
        return projectCount;
    }

    public Long getTransactionCount() {
        return transactionCount;
    }

    public void setProjectCount(Long projectCount) {
        this.projectCount = projectCount;
    }

    public void setTransactionCount(Long transactionCount) {
        this.transactionCount = transactionCount;
    }

    public Map<String, Double> getCommitments() {
        return commitments;
    }

    public void setCommitments(Map<String, Double> commitments) {
        this.commitments = commitments;
    }

    public Map<String, Double> getDisbursements() {
        return disbursements;
    }

    public void setDisbursements(Map<String, Double> disbursements) {
        this.disbursements = disbursements;
    }

    public Map<String, Double> getExpenditures() {
        return expenditures;
    }

    public void setExpenditures(Map<String, Double> expenditures) {
        this.expenditures = expenditures;
    }

    public void addProjectCount(Long projectCount){
        this.projectCount += projectCount;
    }

    public void addTransactionCount(Long transactionCount){
        this.transactionCount += transactionCount;
    }

    public void addCommitment(String type, Double addValue){
        if(addValue!=null){
            commitments.put(type, commitments.get(type)!=null?commitments.get(type) + addValue:addValue);
        }
    }


    public void addDisbursement(String type, Double addValue){
        if(addValue!=null){
            disbursements.put(type, disbursements.get(type)!=null?disbursements.get(type) + addValue:addValue);
        }
    }

    public void addExpenditure(String type, Double addValue){
        if(addValue!=null){
            expenditures.put(type, expenditures.get(type)!=null?expenditures.get(type) + addValue:addValue);
        }
    }

    public void addPhysicalProgress(Long projectId, Double value){
        if(value!=null) {
            physicalProgress.put(projectId, value);
        }
    }

    public Double getPhysicalProgressAverage(){
        Double temp = 0D;
        int count = 0;
        for(Double value:physicalProgress.values()){
            temp += value;
            count ++;
        }
        return count>0?temp/count:temp;
    }

    public void addProject(Project project){
        projectMap.put(project.getId(), project);
    }

}
