package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.Agency;
import org.devgateway.geoph.model.Project;

/**
 * @author dbianco
 *         created on jun 08 2016.
 */
public class AgencyResultsDao  {

    private Agency agency;

    private Project project;

    public AgencyResultsDao() {
    }

    public AgencyResultsDao(Agency agency, Project project) {
        this.agency = agency;
        this.project = project;
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
}
