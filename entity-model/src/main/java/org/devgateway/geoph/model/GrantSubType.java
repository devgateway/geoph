package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author dbianco
 *         created on may 16 2016.
 */

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity(name = "grant_sub_type")
public class GrantSubType extends GenericPersistable implements Serializable {

    private String name;

    public GrantSubType() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
