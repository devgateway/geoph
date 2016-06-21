package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.Location;
import org.devgateway.geoph.model.Project;

/**
 * @author dbianco
 *         created on jun 15 2016.
 */
public class ProjectLocationDao {

    private Project project;

    private Location location;

    public ProjectLocationDao(Location location, Project project) {
        this.project = project;
        this.location = location;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
