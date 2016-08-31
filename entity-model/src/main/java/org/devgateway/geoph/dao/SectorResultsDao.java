package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.Project;
import org.devgateway.geoph.model.ProjectSector;
import org.devgateway.geoph.model.Sector;

/**
 * @author dbianco
 *         created on ago 31 2016.
 */
public class SectorResultsDao {

    private Sector sector;

    private Project project;

    private Double utilization;

    private Double locUtilization;

    public SectorResultsDao() {
    }

    public SectorResultsDao(ProjectSector projectSector, Double utilization) {
        this.sector = projectSector.getSector();
        this.project = projectSector.getProject();
        this.utilization = utilization;
    }

    public SectorResultsDao(ProjectSector projectSector, Double utilization, Double locUtilization) {
        this.sector = projectSector.getSector();
        this.project = projectSector.getProject();
        this.utilization = utilization;
        this.locUtilization = locUtilization;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
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
