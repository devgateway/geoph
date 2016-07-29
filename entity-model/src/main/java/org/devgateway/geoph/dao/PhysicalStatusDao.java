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

    public PhysicalStatusDao() {
    }

    public PhysicalStatusDao(PhysicalStatus physicalStatus, Project project) {
        this.physicalStatus = physicalStatus;
        this.project = project;
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
}
