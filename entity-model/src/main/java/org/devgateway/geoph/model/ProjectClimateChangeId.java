package org.devgateway.geoph.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author dbianco
 *         created on ago 22 2016.
 */
@SuppressWarnings("RedundantIfStatement")
@Embeddable
public class ProjectClimateChangeId implements Serializable {

    @JsonIgnore
    private Project project;

    private ClimateChange climateChange;

    public ProjectClimateChangeId(){}

    public ProjectClimateChangeId(Project project, ClimateChange climateChange) {
        this.project = project;
        this.climateChange = climateChange;
    }

    @ManyToOne
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @ManyToOne
    public ClimateChange getClimateChange() {
        return climateChange;
    }

    public void setClimateChange(ClimateChange climateChange) {
        this.climateChange = climateChange;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectClimateChangeId that = (ProjectClimateChangeId) o;

        if (project != null ? !project.equals(that.project) : that.project != null) {
            return false;
        }
        if (climateChange != null ? !climateChange.equals(that.climateChange) : that.climateChange != null){
            return false;
        }

        return true;
    }

    public int hashCode() {
        int result;
        result = (project != null ? project.hashCode() : 0);
        result = 31 * result + (climateChange != null ? climateChange.hashCode() : 0);
        return result;
    }
}
