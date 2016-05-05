package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

/**
 * @author dbianco
 *         created on abr 28 2016.
 */
@NamedQueries({
        @NamedQuery(
                name = "findAllGeoPhotoSources",
                query = "from geophoto_source g"
        ),
        @NamedQuery(
                name = "findGeoPhotoSourcesByName",
                query = "from geophoto_source g where g.name = :name"
        )
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity(name = "geophoto_source")
public class GeoPhotoSource extends GenericPersistable implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
