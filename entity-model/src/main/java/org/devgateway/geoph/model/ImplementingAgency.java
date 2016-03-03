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

    @ManyToOne(cascade= CascadeType.ALL)
    private ImplementingAgencyType implementingAgencyType;

    public ImplementingAgencyType getImplementingAgencyType() {
        return implementingAgencyType;
    }

    public void setImplementingAgencyType(ImplementingAgencyType implementingAgencyType) {
        this.implementingAgencyType = implementingAgencyType;
    }
}
