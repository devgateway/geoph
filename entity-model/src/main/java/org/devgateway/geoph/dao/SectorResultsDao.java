package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.Project;
import org.devgateway.geoph.model.Sector;

/**
 * @author dbianco
 *         created on jun 08 2016.
 */
public class SectorResultsDao {

    private Sector sector;

    private Project project;

    public SectorResultsDao() {
    }

    public SectorResultsDao(Sector sector, Project project) {
        this.sector = sector;
        this.project = project;
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
}
