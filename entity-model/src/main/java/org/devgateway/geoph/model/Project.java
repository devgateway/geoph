package org.devgateway.geoph.model;

import org.devgateway.geoph.dao.GenericPersistable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Sebastian Dimunzio on 2/26/2016.
 */

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Project extends GenericPersistable implements Serializable {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
