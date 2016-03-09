package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

/**
 * @author dbianco
 *         created on mar 01 2016.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity(name = "public_investment")
@DiscriminatorValue(value="pmc")
public class PublicInvestment extends Transaction implements Serializable {

    @Column(name="funding_support")
    private double fundingSupport;

    public PublicInvestment() {
    }

    public PublicInvestment(Project project, double amount, Date date, FlowType flowType, TransactionType transactionType, double fundingSupport) {
        super(project, amount, date, flowType, transactionType);
        this.fundingSupport = fundingSupport;
    }

    public double getFundingSupport() {
        return fundingSupport;
    }

    public void setFundingSupport(double fundingSupport) {
        this.fundingSupport = fundingSupport;
    }
}
