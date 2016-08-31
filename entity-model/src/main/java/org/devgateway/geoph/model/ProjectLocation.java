package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author dbianco
 *         created on ago 31 2016.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity(name = "project_location")
@AssociationOverrides({
        @AssociationOverride(name = "pk.project",
                joinColumns = @JoinColumn(name = "project_id")),
        @AssociationOverride(name = "pk.location",
                joinColumns = @JoinColumn(name = "location_id")) })
public class ProjectLocation implements Serializable {

    private ProjectLocationId pk = new ProjectLocationId();

    private double utilization;

    public ProjectLocation(){}

    public ProjectLocation(ProjectLocation projectLocation) {
        this.pk = projectLocation.pk;
        this.utilization = projectLocation.utilization;
    }

    public ProjectLocation(Project project, Location location, double utilization) {
        this.pk.setProject(project);
        this.pk.setLocation(location);
        this.utilization = utilization;
    }

    @EmbeddedId
    public ProjectLocationId getPk() {
        return pk;
    }

    public void setPk(ProjectLocationId pk) {
        this.pk = pk;
    }

    @Transient
    public Project getProject() {
        return pk.getProject();
    }

    public void setProject(Project project) {
        this.pk.setProject(project);
    }

    @Transient
    public Location getLocation() {
        return pk.getLocation();
    }

    public void setLocation(Location location) {
        this.pk.setLocation(location);
    }

    @Column(name = "utilization")
    public double getUtilization() {
        return utilization;
    }

    public void setUtilization(double utilization) {
        this.utilization = utilization;
    }
}
