package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author dbianco
 *         created on mar 01 2016.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity(name = "project_monitoring_committee")
@DiscriminatorValue(value="pmc")
public class PMC extends Transaction implements Serializable {

    @Column(name="unpaid_obligation")
    private double unpaid;

    private int target;

    private int actual;

    public double getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(double unpaid) {
        this.unpaid = unpaid;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getActual() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }
}
