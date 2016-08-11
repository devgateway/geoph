package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author dbianco
 *         created on mar 02 2016.
 */
@NamedQueries({
        @NamedQuery(
                name = "findAllImplementingAgency",
                query = "from ImplementingAgency s order by name asc"
        ),
        @NamedQuery(
                name = "findImplementingAgencyById",
                query = "from ImplementingAgency s where s.id = :id"
        )
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@DiscriminatorValue(value="implementing_agency")
public class ImplementingAgency extends Agency implements Serializable {

    @ManyToOne(cascade= CascadeType.MERGE)
    private ImplementingAgencyType type;

    public ImplementingAgency() {
    }

    public ImplementingAgency(String name, String code, ImplementingAgencyType type) {
        super(name, code);
        this.type = type;
    }

    public ImplementingAgencyType getType() {
        return type;
    }

    public void setType(ImplementingAgencyType type) {
        this.type = type;
    }

}
