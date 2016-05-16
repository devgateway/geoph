package org.devgateway.geoph.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.devgateway.geoph.util.TransactionStatusEnum;
import org.devgateway.geoph.util.TransactionTypeEnum;
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

    @JsonIgnore
    @Column(name = "transaction_type_id")
    private long transactionTypeId;

    @JsonIgnore
    @Column(name = "transaction_status_id")
    private long transactionStatusId;

    @JsonIgnore
    @Column(name = "grant_sub_type_id")
    private long grantSubTypeId ;

    public Transaction() {
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

    public long getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(long transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public long getTransactionStatusId() {
        return transactionStatusId;
    }

    public void setTransactionStatusId(long transactionStatusId) {
        this.transactionStatusId = transactionStatusId;
    }

    @JsonProperty(value = "transactionType")
    public TransactionTypeEnum getTransactionType() {
        return TransactionTypeEnum.getEnumById(transactionTypeId);
    }

    @JsonProperty(value = "transactionStatus")
    public TransactionStatusEnum getTransactionStatus() {
        return TransactionStatusEnum.getEnumById(transactionStatusId);
    }

    public long getGrantSubTypeId() {
        return grantSubTypeId;
    }

    public void setGrantSubTypeId(long grantSubTypeId) {
        this.grantSubTypeId = grantSubTypeId;
    }
}
