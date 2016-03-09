package org.devgateway.geoph.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Discriminator", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue(value="transaction")
public class Transaction extends GenericPersistable implements Serializable {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    private double amount;

    private Date date;

    @ManyToOne(cascade= CascadeType.MERGE)
    private FlowType flowType;

    @ManyToOne(cascade= CascadeType.MERGE)
    private TransactionType transactionType;

    public Transaction() {
    }

    public Transaction(Project project, double amount, Date date, FlowType flowType, TransactionType transactionType) {
        this.project = project;
        this.amount = amount;
        this.date = date;
        this.flowType = flowType;
        this.transactionType = transactionType;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
