package org.devgateway.geoph.importer.processing.plugins;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author dbianco
 *         created on jul 04 2016.
 */
@Service
public class PmcColumns {

    @Value("${pmc.projectId}")
    private int projectId;

    @Value("${pmc.projectTitle}")
    private int projectTitle;

    @Value("${pmc.fundingInstitution}")
    private int fundingInstitution;

    @Value("${pmc.executingAgency}")
    private int executingAgency;

    @Value("${pmc.implementingAgency}")
    private int implementingAgency;

    @Value("${grant.classification}")
    private int classification;

    @Value("${pmc.originalCurrency}")
    private int originalCurrency;

    @Value("${pmc.pmcAmountInOriginalCurrency}")
    private int pmcAmountInOriginalCurrency;

    @Value("${pmc.pmcAmount}")
    private int pmcAmount;

    @Value("${pmc.pmcUtilization}")
    private int pmcUtilization;

    @Value("${pmc.startDate}")
    private int startDate;

    @Value("${pmc.originalClosingDate}")
    private int originalClosingDate;

    @Value("${pmc.revisedClosingDate}")
    private int revisedClosingDate;

    @Value("${pmc.sectors}")
    private int sectors;

    @Value("${pmc.region}")
    private int region;

    @Value("${pmc.province}")
    private int province;

    @Value("${pmc.municipality}")
    private int municipality;

    @Value("${pmc.status}")
    private int status;

    @Value("${pmc.physicalStatus}")
    private int physicalStatus;

    @Value("${pmc.physicalProgress}")
    private int physicalProgress;

    @Value("${pmc.actualOwpa}")
    private int actualOwpa;

    @Value("${pmc.targetOwpa}")
    private int targetOwpa;

    @Value("${pmc.issueType}")
    private int issueType;

    @Value("${pmc.issueDetail}")
    private int issueDetail;

    @Value("${pmc.cumulativeAllotment}")
    private int cumulativeAllotment;

    @Value("${pmc.cumulativeObligations}")
    private int cumulativeObligations;

    @Value("${pmc.climateChangeClassification}")
    private int climateChangeClassification;

    @Value("${pmc.genderClassification}")
    private int genderClassification;

    public int getProjectId() {
        return projectId;
    }

    public int getProjectTitle() {
        return projectTitle;
    }

    public int getFundingInstitution() {
        return fundingInstitution;
    }

    public int getExecutingAgency() {
        return executingAgency;
    }

    public int getImplementingAgency() {
        return implementingAgency;
    }

    public int getClassification() {
        return classification;
    }

    public int getOriginalCurrency() {
        return originalCurrency;
    }

    public int getPmcAmountInOriginalCurrency() {
        return pmcAmountInOriginalCurrency;
    }

    public int getPmcAmount() {
        return pmcAmount;
    }

    public int getPmcUtilization() {
        return pmcUtilization;
    }

    public int getStartDate() {
        return startDate;
    }

    public int getOriginalClosingDate() {
        return originalClosingDate;
    }

    public int getRevisedClosingDate() {
        return revisedClosingDate;
    }

    public int getSectors() {
        return sectors;
    }

    public int getRegion() {
        return region;
    }

    public int getProvince() {
        return province;
    }

    public int getMunicipality() {
        return municipality;
    }

    public int getStatus() {
        return status;
    }

    public int getPhysicalStatus() {
        return physicalStatus;
    }

    public int getPhysicalProgress(){
        return physicalProgress;
    }

    public int getActualOwpa() {
        return actualOwpa;
    }

    public int getTargetOwpa() {
        return targetOwpa;
    }

    public int getIssueType() {
        return issueType;
    }

    public int getIssueDetail() {
        return issueDetail;
    }

    public int getCumulativeAllotment() {
        return cumulativeAllotment;
    }

    public int getCumulativeObligations() {
        return cumulativeObligations;
    }

    public int getClimateChangeClassification() {
        return climateChangeClassification;
    }

    public int getGenderClassification() {
        return genderClassification;
    }
}
