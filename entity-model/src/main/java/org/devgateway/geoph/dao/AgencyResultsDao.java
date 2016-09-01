package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.Agency;
import org.devgateway.geoph.model.Project;
import org.devgateway.geoph.model.ProjectAgency;

/**
 * @author dbianco
 *         created on jun 08 2016.
 */
public class AgencyResultsDao  {

    private Agency agency;

    private Project project;

    private Double utilization;

    private Double locUtilization;

    public AgencyResultsDao() {
    }

    public AgencyResultsDao(Agency agency, Project project) {
        this.agency = agency;
        this.project = project;
    }

    public AgencyResultsDao(Agency agency, Project project, Double locUtilization) {
        this.agency = agency;
        this.project = project;
        this.locUtilization = locUtilization;
    }

    public AgencyResultsDao(ProjectAgency projectAgency, Double utilization) {
        this.agency = projectAgency.getAgency();
        this.project = projectAgency.getProject();
        this.utilization = utilization;
    }

    public AgencyResultsDao(ProjectAgency projectAgency, Double utilization, Double locUtilization) {
        this.agency = projectAgency.getAgency();
        this.project = projectAgency.getProject();
        this.utilization = utilization;
        this.locUtilization = locUtilization;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Double getUtilization() {
        return utilization;
    }

    public void setUtilization(Double utilization) {
        this.utilization = utilization;
    }

    public Double getLocUtilization() {
        return locUtilization;
    }

    public void setLocUtilization(Double locUtilization) {
        this.locUtilization = locUtilization;
    }
}
