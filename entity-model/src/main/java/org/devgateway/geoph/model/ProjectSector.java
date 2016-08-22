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
@Entity(name = "project_sector")
@AssociationOverrides({
        @AssociationOverride(name = "pk.project",
                joinColumns = @JoinColumn(name = "project_id")),
        @AssociationOverride(name = "pk.sector",
                joinColumns = @JoinColumn(name = "sector_id")) })
public class ProjectSector implements Serializable {

    private ProjectSectorId pk = new ProjectSectorId();

    private double utilization;

    public ProjectSector(){}

    public ProjectSector(Project project, Sector sector, double utilization) {
        this.pk.setProject(project);
        this.pk.setSector(sector);
        this.utilization = utilization;
    }

    @EmbeddedId
    public ProjectSectorId getPk() {
        return pk;
    }

    public void setPk(ProjectSectorId pk) {
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
    public Sector getSector() {
        return pk.getSector();
    }

    public void setSector(Sector sector) {
        this.pk.setSector(sector);
    }

    @Column(name = "utilization")
    public double getUtilization() {
        return utilization;
    }

    public void setUtilization(double utilization) {
        this.utilization = utilization;
    }
}
