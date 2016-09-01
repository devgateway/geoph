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
public class ProjectLocationId implements Serializable {

    @JsonIgnore
    private Project project;

    private Location location;

    public ProjectLocationId(){}

    public ProjectLocationId(Project project, Location location) {
        this.project = project;
        this.location = location;
    }

    @ManyToOne
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @ManyToOne
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        ProjectLocationId that = (ProjectLocationId) o;
        if (project != null ? !project.equals(that.project) : that.project != null){
            return false;
        }
        if (location != null ? !location.equals(that.location) : that.location != null) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        int result;
        result = (project != null ? project.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
