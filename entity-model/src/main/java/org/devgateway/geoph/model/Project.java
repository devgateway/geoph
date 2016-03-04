package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by Sebastian Dimunzio on 2/26/2016.
 */

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Project extends GenericPersistable implements Serializable {

    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    private Set<Transaction> transactions;

    @ManyToOne(cascade= CascadeType.MERGE)
    private Agency implementingAgency;

    @Column(name = "implementing_agency_office")
    private String implementingAgencyOffice;

    @ManyToOne(cascade= CascadeType.MERGE)
    private Agency executingAgency;

    @ManyToOne(cascade= CascadeType.MERGE)
    private Agency fundingAgency;

    @ManyToOne(cascade= CascadeType.MERGE)
    private Currency originalCurrency;

    @Column(name = "total_project_amount")
    private double totalProjectAmount;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "closing_date")
    private Date closingDate;

    @Column(name = "revised_closing_date")
    private Date revisedClosingDate;

    @ManyToOne(cascade= CascadeType.MERGE)
    private Status status;

    @Column(name = "period_start")
    private Date periodStart;

    @Column(name = "period_end")
    private Date periodEnd;

    @ManyToOne(cascade=CascadeType.MERGE)
    private TransactionType grantType;

    @ManyToOne(cascade=CascadeType.MERGE)
    private Classification grantClassification;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "project_location", joinColumns = {
            @JoinColumn(name = "project_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "location_id",
                    nullable = false, updatable = false) })
    private Set<Location> locations;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "project_sector", joinColumns = {
            @JoinColumn(name = "project_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "sector_id",
                    nullable = false, updatable = false) })
    private Set<Sector> sectors;

    public Project() {
    }

    public Project(String title, Agency implementingAgency, String implementingAgencyOffice,
                   Agency executingAgency, Agency fundingAgency, Currency originalCurrency,
                   double totalProjectAmount, Date startDate, Date closingDate,
                   Date revisedClosingDate, Status status, Date periodStart, Date periodEnd,
                   TransactionType grantType, Classification grantClassification, Set<Location> locations,
                   Set<Sector> sectors) {
        this.title = title;
        this.implementingAgency = implementingAgency;
        this.implementingAgencyOffice = implementingAgencyOffice;
        this.executingAgency = executingAgency;
        this.fundingAgency = fundingAgency;
        this.originalCurrency = originalCurrency;
        this.totalProjectAmount = totalProjectAmount;
        this.startDate = startDate;
        this.closingDate = closingDate;
        this.revisedClosingDate = revisedClosingDate;
        this.status = status;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.grantType = grantType;
        this.grantClassification = grantClassification;
        this.locations = locations;
        this.sectors = sectors;
    }

    public Project(String title, Set<Transaction> transactions, Agency implementingAgency,
                   String implementingAgencyOffice, Agency executingAgency, Agency fundingAgency,
                   Currency originalCurrency, double totalProjectAmount, Date startDate,
                   Date closingDate, Date revisedClosingDate, Status status, Date periodStart,
                   Date periodEnd, TransactionType grantType, Classification grantClassification,
                   Set<Location> locations, Set<Sector> sectors) {
        this.title = title;
        this.transactions = transactions;
        this.implementingAgency = implementingAgency;
        this.implementingAgencyOffice = implementingAgencyOffice;
        this.executingAgency = executingAgency;
        this.fundingAgency = fundingAgency;
        this.originalCurrency = originalCurrency;
        this.totalProjectAmount = totalProjectAmount;
        this.startDate = startDate;
        this.closingDate = closingDate;
        this.revisedClosingDate = revisedClosingDate;
        this.status = status;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.grantType = grantType;
        this.grantClassification = grantClassification;
        this.locations = locations;
        this.sectors = sectors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Agency getImplementingAgency() {
        return implementingAgency;
    }

    public void setImplementingAgency(Agency implementingAgency) {
        this.implementingAgency = implementingAgency;
    }

    public String getImplementingAgencyOffice() {
        return implementingAgencyOffice;
    }

    public void setImplementingAgencyOffice(String implementingAgencyOffice) {
        this.implementingAgencyOffice = implementingAgencyOffice;
    }

    public Agency getExecutingAgency() {
        return executingAgency;
    }

    public void setExecutingAgency(Agency executingAgency) {
        this.executingAgency = executingAgency;
    }

    public Agency getFundingAgency() {
        return fundingAgency;
    }

    public void setFundingAgency(Agency fundingAgency) {
        this.fundingAgency = fundingAgency;
    }

    public Currency getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(Currency originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    public double getTotalProjectAmount() {
        return totalProjectAmount;
    }

    public void setTotalProjectAmount(double totalProjectAmount) {
        this.totalProjectAmount = totalProjectAmount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public Date getRevisedClosingDate() {
        return revisedClosingDate;
    }

    public void setRevisedClosingDate(Date revisedClosingDate) {
        this.revisedClosingDate = revisedClosingDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(Date periodStart) {
        this.periodStart = periodStart;
    }

    public Date getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Date periodEnd) {
        this.periodEnd = periodEnd;
    }

    public TransactionType getGrantType() {
        return grantType;
    }

    public void setGrantType(TransactionType grantType) {
        this.grantType = grantType;
    }

    public Classification getGrantClassification() {
        return grantClassification;
    }

    public void setGrantClassification(Classification grantClassification) {
        this.grantClassification = grantClassification;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public Set<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(Set<Sector> sectors) {
        this.sectors = sectors;
    }
}
