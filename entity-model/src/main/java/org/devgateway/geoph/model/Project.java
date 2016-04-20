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
@NamedQueries({
    @NamedQuery(
            name = "findAllProjects",
            query = "from Project p"
    ),
    @NamedQuery(
            name = "findProjectsById",
            query = "from Project p where p.id = :id"
    )
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@NamedEntityGraph(name = "graph.project.all",
        attributeNodes = {
                @NamedAttributeNode("transactions"),
                @NamedAttributeNode("implementingAgency"),
                @NamedAttributeNode("executingAgency"),
                @NamedAttributeNode("fundingAgency"),
                @NamedAttributeNode("originalCurrency"),
                @NamedAttributeNode("status"),
                @NamedAttributeNode("grantType"),
                @NamedAttributeNode("grantClassification"),
                @NamedAttributeNode("locations"),
                @NamedAttributeNode("sectors"),
                @NamedAttributeNode("physicalStatus")
        })
public class Project extends GenericPersistable implements Serializable {

    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    private Set<Transaction> transactions;

    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.MERGE)
    private Agency implementingAgency;

    @Column(name = "implementing_agency_office")
    private String implementingAgencyOffice;

    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.MERGE)
    private Agency executingAgency;

    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.MERGE)
    private Agency fundingAgency;

    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.MERGE)
    private Currency originalCurrency;

    @Column(name = "total_project_amount")
    private double totalProjectAmount;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "revised_closing_date")
    private Date revisedClosingDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.MERGE)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.MERGE)
    private PhysicalStatus physicalStatus;

    @Column(name = "period_performance_start")
    private Date periodPerformanceStart;

    @Column(name = "period_performance_end")
    private Date periodPerformanceEnd;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    private TransactionType grantType;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public PhysicalStatus getPhysicalStatus() {
        return physicalStatus;
    }

    public void setPhysicalStatus(PhysicalStatus physicalStatus) {
        this.physicalStatus = physicalStatus;
    }

    public Date getPeriodPerformanceStart() {
        return periodPerformanceStart;
    }

    public void setPeriodPerformanceStart(Date periodPerformanceStart) {
        this.periodPerformanceStart = periodPerformanceStart;
    }

    public Date getPeriodPerformanceEnd() {
        return periodPerformanceEnd;
    }

    public void setPeriodPerformanceEnd(Date periodPerformanceEnd) {
        this.periodPerformanceEnd = periodPerformanceEnd;
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
