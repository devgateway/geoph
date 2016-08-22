package org.devgateway.geoph.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author dbianco
 *         created on ago 22 2016.
 */
@Embeddable
public class ProjectSectorId implements Serializable {

    @JsonIgnore
    private Project project;

    private Sector sector;

    public ProjectSectorId(){}

    public ProjectSectorId(Project project, Sector sector) {
        this.project = project;
        this.sector = sector;
    }

    @ManyToOne
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @ManyToOne
    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    @ManyToOne


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectSectorId that = (ProjectSectorId) o;

        if (project != null ? !project.equals(that.project) : that.project != null) return false;
        if (sector != null ? !sector.equals(that.sector) : that.sector != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (project != null ? project.hashCode() : 0);
        result = 31 * result + (sector != null ? sector.hashCode() : 0);
        return result;
    }
}
