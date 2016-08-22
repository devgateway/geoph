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
public class ProjectAgencyId implements Serializable {

    @JsonIgnore
    private Project project;

    private Agency agency;

    public ProjectAgencyId(){

    }

    public ProjectAgencyId(Project project, Agency agency) {
        this.project = project;
        this.agency = agency;
    }

    @ManyToOne
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @ManyToOne
    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectAgencyId that = (ProjectAgencyId) o;

        if (project != null ? !project.equals(that.project) : that.project != null) return false;
        if (agency != null ? !agency.equals(that.agency) : that.agency != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (project != null ? project.hashCode() : 0);
        result = 31 * result + (agency != null ? agency.hashCode() : 0);
        return result;
    }
}
