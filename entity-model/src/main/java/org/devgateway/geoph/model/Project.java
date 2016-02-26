package org.devgateway.geoph.model;

import org.devgateway.geoph.persistence.dao.GenericPersistable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;

/**
 * Created by Sebastian Dimunzio on 2/26/2016.
 */

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Project extends GenericPersistable {

    String title;

}
