package org.devgateway.geoph.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author dbianco
 *         created on abr 22 2016.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity(name = "gender_responsiveness")
public class GenderResponsiveness extends GenericPersistable implements Serializable, Comparable {

    private String name;

    private String code;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "project_gender_responsiveness", joinColumns = {
            @JoinColumn(name = "gender_responsiveness_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "project_id",
                    nullable = false, updatable = false) })
    private Set<Project> projects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public int compareTo(Object o) {
        return this.name.compareTo(((GenderResponsiveness)o).getName());
    }
}