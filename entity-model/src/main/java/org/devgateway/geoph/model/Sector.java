package org.devgateway.geoph.model;

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
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Sector extends GenericPersistable implements Serializable {

    @Column(name = "code")
    private String code;

    private String name;

    private int type;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Sector> items = new ArrayList<>();

    public Sector() {
    }

    public Sector(String code, String name, int type) {
        this.code = code;
        this.name = name;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Sector> getItems() {
        return items;
    }

    public void setItems(List<Sector> items) {
        this.items = items;
    }
}
