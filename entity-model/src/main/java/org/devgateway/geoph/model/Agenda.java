package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by dbianco on 02/05/2018.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Agenda extends GenericPersistable implements Serializable, Comparable<Agenda> {

    @Column(name = "code")
    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCodeName() {
        return this.code + " - " + this.name;
    }

    @Override
    public int compareTo(Agenda o) {
        return this.getId().compareTo(o.getId());
    }
}
