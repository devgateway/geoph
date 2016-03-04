package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Set;

/**
 * @author dbianco
 *         created on mar 01 2016.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Sector extends GenericPersistable implements Serializable {

    @Column(name = "code")
    private String code;

    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    private int type;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "sectors")
    private Set<Project> projects;

    public Sector() {
    }

    public Sector(String code, String name, Long parentId, int type) {
        this.code = code;
        this.name = name;
        this.parentId = parentId;
        this.type = type;
    }

    public Sector(String name, long parentId, int type, Set<Project> projects) {
        this.name = name;
        this.parentId = parentId;
        this.type = type;
        this.projects = projects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
