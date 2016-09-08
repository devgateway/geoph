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
@Entity(name = "project_gender_responsiveness")
@AssociationOverrides({
        @AssociationOverride(name = "pk.project",
                joinColumns = @JoinColumn(name = "project_id")),
        @AssociationOverride(name = "pk.gender_responsiveness",
                joinColumns = @JoinColumn(name = "gender_responsiveness_id")) })
public class ProjectGenderResponsiveness implements Serializable {

    private ProjectGenderResponsivenessId pk = new ProjectGenderResponsivenessId();

    private double utilization;

    public ProjectGenderResponsiveness(){}

    public ProjectGenderResponsiveness(ProjectGenderResponsiveness projectGenderResponsiveness) {
        this.pk = projectGenderResponsiveness.pk;
        this.utilization = projectGenderResponsiveness.utilization;
    }

    public ProjectGenderResponsiveness(Project project, GenderResponsiveness genderResponsiveness, double utilization) {
        this.pk.setProject(project);
        this.pk.setGender_responsiveness(genderResponsiveness);
        this.utilization = utilization;
    }

    @EmbeddedId
    public ProjectGenderResponsivenessId getPk() {
        return pk;
    }

    public void setPk(ProjectGenderResponsivenessId pk) {
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
    public GenderResponsiveness getGender_responsiveness() {
        return pk.getGender_responsiveness();
    }

    public void setGender_responsiveness(GenderResponsiveness genderResponsiveness) {
        this.pk.setGender_responsiveness(genderResponsiveness);
    }

    @Column(name = "utilization")
    public double getUtilization() {
        return utilization;
    }

    public void setUtilization(double utilization) {
        this.utilization = utilization;
    }
}
