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
public class ProjectGenderResponsivenessId implements Serializable {

    @JsonIgnore
    private Project project;

    private GenderResponsiveness gender_responsiveness;

    public ProjectGenderResponsivenessId(){}

    public ProjectGenderResponsivenessId(Project project, GenderResponsiveness genderResponsiveness) {
        this.project = project;
        this.gender_responsiveness = genderResponsiveness;
    }

    @ManyToOne
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @ManyToOne
    public GenderResponsiveness getGender_responsiveness() {
        return gender_responsiveness;
    }

    public void setGender_responsiveness(GenderResponsiveness gender_responsiveness) {
        this.gender_responsiveness = gender_responsiveness;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectGenderResponsivenessId that = (ProjectGenderResponsivenessId) o;

        if (project != null ? !project.equals(that.project) : that.project != null) {
            return false;
        }
        return !(gender_responsiveness != null ? !gender_responsiveness.equals(that.gender_responsiveness) : that.gender_responsiveness != null);

    }

    public int hashCode() {
        int result;
        result = (project != null ? project.hashCode() : 0);
        result = 31 * result + (gender_responsiveness != null ? gender_responsiveness.hashCode() : 0);
        return result;
    }
}
