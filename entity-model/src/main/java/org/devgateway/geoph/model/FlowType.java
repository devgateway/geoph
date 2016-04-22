package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author dbianco
 *         created on abr 22 2016.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class FlowType extends GenericPersistable implements Serializable {

    private String name;

    public FlowType() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
