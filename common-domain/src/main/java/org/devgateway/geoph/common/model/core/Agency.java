package org.devgateway.geoph.common.model.core;

import org.hibernate.annotations.Cache;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;

import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

/**
 * @author dbianco
 *         created on feb 26 2016.
 */
@Entity
@Cache(usage = READ_WRITE)
public class Agency implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
