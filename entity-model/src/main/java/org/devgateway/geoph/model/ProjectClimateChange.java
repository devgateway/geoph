package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author dbianco
 *         created on ago 19 2016.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity(name = "project_climate_change")
@AssociationOverrides({
        @AssociationOverride(name = "pk.project",
                joinColumns = @JoinColumn(name = "project_id")),
        @AssociationOverride(name = "pk.climateChange",
                joinColumns = @JoinColumn(name = "climate_change_id")) })
public class ProjectClimateChange implements Serializable {

    private ProjectClimateChangeId pk = new ProjectClimateChangeId();

    private double utilization;

    public ProjectClimateChange(){}

    public ProjectClimateChange(ProjectClimateChange projectClimateChange) {
        this.pk = projectClimateChange.pk;
        this.utilization = projectClimateChange.utilization;
    }

    public ProjectClimateChange(Project project, ClimateChange climateChange, double utilization) {
        this.pk.setProject(project);
        this.pk.setClimateChange(climateChange);
        this.utilization = utilization;
    }

    @EmbeddedId
    public ProjectClimateChangeId getPk() {
        return pk;
    }

    public void setPk(ProjectClimateChangeId pk) {
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
    public ClimateChange getClimate_change() {
        return pk.getClimateChange();
    }

    public void setClimate_change(ClimateChange climateChange) {
        this.pk.setClimateChange(climateChange);
    }

    @Column(name = "utilization")
    public double getUtilization() {
        return utilization;
    }

    public void setUtilization(double utilization) {
        this.utilization = utilization;
    }
}
