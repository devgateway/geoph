package org.devgateway.geoph.model;

import org.devgateway.geoph.dao.GenericPersistable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sebastian Dimunzio on 2/26/2016.
 */

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Agency extends GenericPersistable implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
