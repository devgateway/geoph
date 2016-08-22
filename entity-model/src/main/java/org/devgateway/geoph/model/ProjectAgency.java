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
@Entity(name = "project_agency")
@AssociationOverrides({
        @AssociationOverride(name = "pk.project",
                joinColumns = @JoinColumn(name = "project_id")),
        @AssociationOverride(name = "pk.agency",
                joinColumns = @JoinColumn(name = "agency_id")) })
public class ProjectAgency implements Serializable {

    private ProjectAgencyId pk = new ProjectAgencyId();

    @Column(name = "utilization")
    private double utilization;

    public ProjectAgency(){}

    public ProjectAgency(Project project, Agency agency, double utilization) {
        this.pk.setProject(project);
        this.pk.setAgency(agency);
        this.utilization = utilization;
    }

    @EmbeddedId
    public ProjectAgencyId getPk() {
        return pk;
    }

    public void setPk(ProjectAgencyId pk) {
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
    public Agency getAgency() {
        return pk.getAgency();
    }

    public void setAgency(Agency agency) {
        this.pk.setAgency(agency);
    }

    public double getUtilization() {
        return utilization;
    }

    public void setUtilization(double utilization) {
        this.utilization = utilization;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ProjectAgency that = (ProjectAgency) o;

        if (getPk() != null ? !getPk().equals(that.getPk())
                : that.getPk() != null)
            return false;

        return true;
    }

    public int hashCode() {
        return (getPk() != null ? getPk().hashCode() : 0);
    }
}
