package org.devgateway.geoph.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
    @OneToMany(cascade=CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "pk.gender_responsiveness")
    private Set<ProjectGenderResponsiveness> projects;

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

    public Set<ProjectGenderResponsiveness> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectGenderResponsiveness> projects) {
        this.projects = projects;
    }

    @Override
    public int compareTo(Object o) {
        return this.name.compareTo(((GenderResponsiveness)o).getName());
    }
}