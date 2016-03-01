package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author dbianco
 *         created on mar 01 2016.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@DiscriminatorValue(value="grant")
public class Grant extends Transaction implements Serializable {

    @ManyToOne(cascade=CascadeType.ALL)
    private GrantType grantType;

    @ManyToOne(cascade=CascadeType.ALL)
    private Classification classification;

    public GrantType getGrantType() {
        return grantType;
    }

    public void setGrantType(GrantType grantType) {
        this.grantType = grantType;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }
}
