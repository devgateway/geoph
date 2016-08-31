package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
    ),
        @NamedQuery(
                name = "findProjectsByPhId",
                query = "from Project p where p.phId = :phId"
        )
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@NamedEntityGraph(name = "graph.project.all",
        attributeNodes = {
                @NamedAttributeNode("transactions"),
                @NamedAttributeNode("implementingAgencies"),
                @NamedAttributeNode("executingAgency"),
                @NamedAttributeNode("fundingAgency"),
                @NamedAttributeNode("originalCurrency"),
                @NamedAttributeNode("status"),
                @NamedAttributeNode("grantClassification"),
                @NamedAttributeNode("locations"),
                @NamedAttributeNode("sectors"),
                @NamedAttributeNode("climateChange"),
                @NamedAttributeNode("genderResponsiveness"),
                @NamedAttributeNode("physicalStatus")
        })
public class Project extends GenericPersistable implements Serializable {

    private String phId;

    @Size(max=255)
    private String title;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "project")
    private Set<Transaction> transactions;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "pk.project")
    private Set<ProjectAgency> implementingAgencies;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "pk.project")
    private Set<ProjectLocation> locations;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "pk.project")
    private Set<ProjectSector> sectors;

    @Column(name = "implementing_agency_office")
    private String implementingAgencyOffice;

    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.MERGE)
    private Agency executingAgency;

    @ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.MERGE)
    private Agency fundingAgency;

    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.MERGE)
    private Currency originalCurrency;

    @Column(name = "total_project_amount")
    private Double totalProjectAmount;

    @Column(name = "total_project_amount_original")
    private Double totalProjectAmountOriginal;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "revised_closing_date")
    private Date revisedClosingDate;

    @Column(name = "revised_period_performance_end")
    private Date revisedPeriodPerformanceEndDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.MERGE)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.MERGE)
    private PhysicalStatus physicalStatus;

    @Column(name = "period_performance_start")
    private Date periodPerformanceStart;

    @Column(name = "period_performance_end")
    private Date periodPerformanceEnd;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    private Classification grantClassification;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "project_climate_change", joinColumns = {
            @JoinColumn(name = "project_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "climate_change_id",
                    nullable = false, updatable = false) })
    private Set<ClimateChange> climateChange;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "project_gender_responsiveness", joinColumns = {
            @JoinColumn(name = "project_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "gender_responsiveness_id",
                    nullable = false, updatable = false) })
    private Set<GenderResponsiveness> genderResponsiveness;

    @Column(name = "actual_owpa")
    private Double actualOwpa;

    @Column(name = "target_owpa")
    private Double targetOwpa;

    @Column(name = "physical_progress")
    private Double physicalProgress;

    @Column(name = "issue_type", columnDefinition = "TEXT")
    private String issueType;

    @Column(name = "issue_detail", columnDefinition = "TEXT")
    private String issueDetail;

    @Column(name = "action_taken_ia", columnDefinition = "TEXT")
    private String actionTakenIa;

    @Column(name = "action_to_be_taken_ia", columnDefinition = "TEXT")
    private String actionToBeTakenIa;

    @Column(name = "action_taken_neda", columnDefinition = "TEXT")
    private String actionTakenNeda;

    @Column(name = "accomplishment_update", columnDefinition = "TEXT")
    private String accomplishmentUpdate;

    @Column(name = "nature_rre")
    private String natureRre;

    @Column(name = "reason_rre")
    private String reasonRre;

    @Column(name = "level_rre")
    private String levelRre;

    @Column(name = "date_rre")
    private String dateRre;

    @Column(name = "icc_nb_action")
    private String iccNbAction;

    @Column(name = "icc_nb_conditions", columnDefinition = "TEXT")
    private String iccNbConditions;

    @Column(name = "compliance_to_conditions", columnDefinition = "TEXT")
    private String complianceToConditions;

    @Column(name = "cumulative_allotment")
    private Double cumulativeAllotment;

    @Column(name = "cumulative_obligations")
    private Double cumulativeObligations;


    public Project() {
    }

    public String getPhId() {
        return phId;
    }

    public void setPhId(String phId) {
        this.phId = phId;
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
        if(this.transactions!=null) {
            this.transactions.clear();
            if (transactions != null) {
                this.transactions.addAll(transactions);
            }
        } else {
            this.transactions = transactions;
        }
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

    public Double getTotalProjectAmount() {
        return totalProjectAmount;
    }

    public void setTotalProjectAmount(Double totalProjectAmount) {
        this.totalProjectAmount = totalProjectAmount;
    }

    public Double getTotalProjectAmountOriginal() {
        return totalProjectAmountOriginal;
    }

    public void setTotalProjectAmountOriginal(Double totalProjectAmountOriginal) {
        this.totalProjectAmountOriginal = totalProjectAmountOriginal;
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

    public Date getRevisedPeriodPerformanceEndDate() {
        return revisedPeriodPerformanceEndDate;
    }

    public void setRevisedPeriodPerformanceEndDate(Date revisedPeriodPerformanceEndDate) {
        this.revisedPeriodPerformanceEndDate = revisedPeriodPerformanceEndDate;
    }

    public Date getPeriodPerformanceEnd() {
        return periodPerformanceEnd;
    }

    public void setPeriodPerformanceEnd(Date periodPerformanceEnd) {
        this.periodPerformanceEnd = periodPerformanceEnd;
    }

    public Classification getGrantClassification() {
        return grantClassification;
    }

    public void setGrantClassification(Classification grantClassification) {
        this.grantClassification = grantClassification;
    }

    public Set<ProjectLocation> getLocations() {
        return locations;
    }

    public void setLocations(Set<ProjectLocation> locations) {
        if(this.locations!=null) {
            this.locations.clear();
            this.locations.addAll(locations);
        } else {
            this.locations = locations;
        }
    }

    public Set<ProjectAgency> getImplementingAgencies() {
        return implementingAgencies;
    }

    public void setImplementingAgencies(Set<ProjectAgency> implementingAgencies) {
        if(this.implementingAgencies != null){
            this.implementingAgencies.clear();
            this.implementingAgencies.addAll(implementingAgencies);
        } else {
            this.implementingAgencies = implementingAgencies;
        }
    }

    public Set<ProjectSector> getSectors() {
        return sectors;
    }

    public void setSectors(Set<ProjectSector> sectors) {
        if(this.sectors!=null) {
            this.sectors.clear();
            this.sectors.addAll(sectors);
        } else {
            this.sectors = sectors;
        }
    }

    public Set<ClimateChange> getClimateChange() {
        return climateChange;
    }

    public void setClimateChange(Set<ClimateChange> climateChange) {
        this.climateChange = climateChange;
    }

    public Set<GenderResponsiveness> getGenderResponsiveness() {
        return genderResponsiveness;
    }

    public void setGenderResponsiveness(Set<GenderResponsiveness> genderResponsiveness) {
        this.genderResponsiveness = genderResponsiveness;
    }

    public Double getActualOwpa() {
        return actualOwpa;
    }

    public void setActualOwpa(Double actualOwpa) {
        this.actualOwpa = actualOwpa;
    }

    public Double getTargetOwpa() {
        return targetOwpa;
    }

    public void setTargetOwpa(Double targetOwpa) {
        this.targetOwpa = targetOwpa;
    }

    public Double getPhysicalProgress() {
        return physicalProgress;
    }

    public void setPhysicalProgress(Double physicalProgress) {
        this.physicalProgress = physicalProgress;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getIssueDetail() {
        return issueDetail;
    }

    public void setIssueDetail(String issueDetail) {
        this.issueDetail = issueDetail;
    }

    public String getActionTakenIa() {
        return actionTakenIa;
    }

    public void setActionTakenIa(String actionTakenIa) {
        this.actionTakenIa = actionTakenIa;
    }

    public String getActionToBeTakenIa() {
        return actionToBeTakenIa;
    }

    public void setActionToBeTakenIa(String actionToBeTakenIa) {
        this.actionToBeTakenIa = actionToBeTakenIa;
    }

    public String getActionTakenNeda() {
        return actionTakenNeda;
    }

    public void setActionTakenNeda(String actionTakenNeda) {
        this.actionTakenNeda = actionTakenNeda;
    }

    public String getAccomplishmentUpdate() {
        return accomplishmentUpdate;
    }

    public void setAccomplishmentUpdate(String accomplishmentUpdate) {
        this.accomplishmentUpdate = accomplishmentUpdate;
    }

    public String getNatureRre() {
        return natureRre;
    }

    public void setNatureRre(String natureRre) {
        this.natureRre = natureRre;
    }

    public String getReasonRre() {
        return reasonRre;
    }

    public void setReasonRre(String reasonRre) {
        this.reasonRre = reasonRre;
    }

    public String getLevelRre() {
        return levelRre;
    }

    public void setLevelRre(String levelRre) {
        this.levelRre = levelRre;
    }

    public String getDateRre() {
        return dateRre;
    }

    public void setDateRre(String dateRre) {
        this.dateRre = dateRre;
    }

    public String getIccNbAction() {
        return iccNbAction;
    }

    public void setIccNbAction(String iccNbAction) {
        this.iccNbAction = iccNbAction;
    }

    public String getIccNbConditions() {
        return iccNbConditions;
    }

    public void setIccNbConditions(String iccNbConditions) {
        this.iccNbConditions = iccNbConditions;
    }

    public String getComplianceToConditions() {
        return complianceToConditions;
    }

    public void setComplianceToConditions(String complianceToConditions) {
        this.complianceToConditions = complianceToConditions;
    }

    public Double getCumulativeAllotment() {
        return cumulativeAllotment;
    }

    public void setCumulativeAllotment(Double cumulativeAllotment) {
        this.cumulativeAllotment = cumulativeAllotment;
    }

    public Double getCumulativeObligations() {
        return cumulativeObligations;
    }

    public void setCumulativeObligations(Double cumulativeObligations) {
        this.cumulativeObligations = cumulativeObligations;
    }

    public void updateFields(Project project){
        this.title = project.getTitle();

        if(this.transactions!=null) {
            this.transactions.clear();
        }
        if (project.getTransactions()!=null){
            if(this.transactions==null) {
                this.transactions = new HashSet<>();
            }
            for(Transaction transaction:project.getTransactions()) {
                transaction.setProject(this);
                this.transactions.add(transaction);
            }
        }

        if(this.implementingAgencies!=null) {
            this.implementingAgencies.clear();
        }
        if (project.getImplementingAgencies()!=null){
            if(this.implementingAgencies==null) {
                this.implementingAgencies = new HashSet<>();
            }
            for(ProjectAgency agency:project.getImplementingAgencies()) {
                agency.setProject(this);
                this.implementingAgencies.add(agency);
            }
        }

        this.implementingAgencyOffice = project.getImplementingAgencyOffice();
        this.executingAgency = project.getExecutingAgency();
        this.fundingAgency = project.getFundingAgency();
        this.originalCurrency = project.getOriginalCurrency();
        this.totalProjectAmount = project.getTotalProjectAmount();
        this.totalProjectAmountOriginal = project.getTotalProjectAmountOriginal();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
        this.revisedClosingDate = project.getRevisedClosingDate();
        this.revisedPeriodPerformanceEndDate = project.getRevisedPeriodPerformanceEndDate();
        this.status = project.getStatus();
        this.physicalStatus = project.getPhysicalStatus();
        this.periodPerformanceStart = project.getPeriodPerformanceStart();
        this.periodPerformanceEnd = project.getPeriodPerformanceEnd();
        this.grantClassification = project.getGrantClassification();

        if(this.locations!=null) {
            this.locations.clear();
        }
        if (project.getLocations()!=null){
            if(this.locations==null) {
                this.locations = new HashSet<>();
            }
            for(ProjectLocation location:project.getLocations()) {
                location.setProject(this);
                this.locations.add(location);
            }
        }

        if(this.sectors!=null) {
            this.sectors.clear();
        }
        if (project.getSectors()!=null){
            if(this.sectors==null) {
                this.sectors = new HashSet<>();
            }
            for(ProjectSector sector:project.getSectors()) {
                sector.setProject(this);
                this.sectors.add(sector);
            }
        }


        this.climateChange = project.getClimateChange();
        this.genderResponsiveness = project.getGenderResponsiveness();
        this.actualOwpa = project.getActualOwpa();
        this.targetOwpa = project.getTargetOwpa();
        this.physicalProgress = project.getPhysicalProgress();
        this.issueType = project.getIssueType();
        this.issueDetail = project.getIssueDetail();
        this.actionTakenIa = project.getActionTakenIa();
        this.actionToBeTakenIa = project.getActionToBeTakenIa();
        this.actionTakenNeda = project.getActionTakenNeda();
        this.accomplishmentUpdate = project.getAccomplishmentUpdate();
        this.natureRre = project.getNatureRre();
        this.reasonRre = project.getReasonRre();
        this.levelRre = project.getLevelRre();
        this.dateRre = project.getDateRre();
        this.iccNbAction = project.getIccNbAction();
        this.iccNbConditions = project.getIccNbConditions();
        this.complianceToConditions = project.getComplianceToConditions();
        this.cumulativeAllotment = project.getCumulativeAllotment();
        this.cumulativeObligations = project.getCumulativeObligations();
    }


}
