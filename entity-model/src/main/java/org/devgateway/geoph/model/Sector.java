package org.devgateway.geoph.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author dbianco
 *         created on mar 01 2016.
 */
@NamedQueries({
        @NamedQuery(
                name = "findAllSectors",
                query = "from Sector s"
        ),
        @NamedQuery(
                name = "findSectorsByCode",
                query = "from Sector s where s.code = :code"
        ),
        @NamedQuery(
                name = "findSectorsById",
                query = "from Sector s where s.id = :id"
        ),
        @NamedQuery(
                name = "findSectorsByLevel",
                query = "from Sector s where s.level = :level"
        )
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Sector extends GenericPersistable implements Serializable, Comparable {

    @Column(name = "code")
    private String code;

    private String name;

    private int level;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Sector> items = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "project_sector", joinColumns = {
            @JoinColumn(name = "sector_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "project_id",
                    nullable = false, updatable = false) })
    private Set<Project> projects;

    public Sector() {
    }

    public Sector(String code, String name, int level) {
        this.code = code;
        this.name = name;
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Sector> getItems() {
        return items;
    }

    public void setItems(List<Sector> items) {
        this.items = items;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public int compareTo(Object o) {
        return this.name.compareTo(((Sector)o).getName());
    }
}
