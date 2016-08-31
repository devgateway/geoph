package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.PhysicalStatus;
import org.devgateway.geoph.model.Project;

/**
 * @author dbianco
 *         created on jun 08 2016.
 */
public class PhysicalStatusDao {

    private PhysicalStatus physicalStatus;

    private Project project;

    private Double locUtilization;

    public PhysicalStatusDao() {
    }

    public PhysicalStatusDao(PhysicalStatus physicalStatus, Project project) {
        this.physicalStatus = physicalStatus;
        this.project = project;
    }

    public PhysicalStatusDao(PhysicalStatus physicalStatus, Project project, Double locUtilization) {
        this.physicalStatus = physicalStatus;
        this.project = project;
        this.locUtilization = locUtilization;
    }

    public PhysicalStatus getPhysicalStatus() {
        return physicalStatus;
    }

    public void setPhysicalStatus(PhysicalStatus physicalStatus) {
        this.physicalStatus = physicalStatus;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Double getLocUtilization() {
        return locUtilization;
    }

    public void setLocUtilization(Double locUtilization) {
        this.locUtilization = locUtilization;
    }
}
