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
@DiscriminatorColumn(name="flow_type", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue(value="transaction")
public class Transaction extends GenericPersistable implements Serializable {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    private double amount;

    private Date date;

    @Column(name = "flow_type", insertable = false, updatable = false)
    private String flowType;

    @ManyToOne(cascade= CascadeType.MERGE)
    private TransactionType transactionType;

    @ManyToOne(cascade= CascadeType.MERGE)
    private TransactionStatus transactionStatus;

    public Transaction() {
    }

    public Transaction(Project project, double amount, Date date, String flowType, TransactionType transactionType) {
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

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
