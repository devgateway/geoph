package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author dbianco
 *         created on mar 01 2016.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@DiscriminatorValue(value="grant")
public class Grant extends Transaction implements Serializable {

    public Grant() {
    }

    public Grant(Project project, double amount, Date date, FlowType flowType, TransactionType transactionType) {
        super(project, amount, date, flowType, transactionType);
    }

}
