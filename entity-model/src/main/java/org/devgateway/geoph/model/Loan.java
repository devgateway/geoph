package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

/**
 * @author dbianco
 *         created on mar 01 2016.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@DiscriminatorValue(value="loan")
public class Loan extends Transaction implements Serializable {

    public Loan() {
    }

    public Loan(Project project, double amount, Date date, FlowType flowType, TransactionType transactionType) {
        super(project, amount, date, flowType, transactionType);
    }
}
