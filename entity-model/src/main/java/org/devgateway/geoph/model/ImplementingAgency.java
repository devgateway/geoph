package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author dbianco
 *         created on mar 02 2016.
 */
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
