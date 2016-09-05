package org.devgateway.geoph.importer.processing.plugins;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author dbianco
 *         created on jul 04 2016.
 */
@Service
public class GrantColumns {

    @Value("${grant.projectId}")
    private int projectId;

    @Value("${grant.projectTitle}")
    private int projectTitle;

    @Value("${grant.fundingInstitution}")
    private int fundingInstitution;

    @Value("${grant.executingAgency}")
    private int executingAgency;

    @Value("${grant.implementingAgency}")
    private int implementingAgency;

    @Value("${grant.classification}")
    private int classification;

    @Value("${grant.originalCurrency}")
    private int originalCurrency;

    @Value("${grant.grantAmountInOriginalCurrency}")
    private int grantAmountInOriginalCurrency;

    @Value("${grant.grantAmount}")
    private int grantAmount;

    @Value("${grant.grantUtilization}")
    private int grantUtilization;

    @Value("${grant.startDate}")
    private int startDate;

    @Value("${grant.originalClosingDate}")
    private int originalClosingDate;

    @Value("${grant.revisedClosingDate}")
    private int revisedClosingDate;

    @Value("${grant.sectors}")
    private int sectors;

    @Value("${grant.region}")
    private int region;

    @Value("${grant.province}")
    private int province;

    @Value("${grant.municipality}")
    private int municipality;

    @Value("${grant.subType}")
    private int subType;

    @Value("${grant.status}")
    private int status;

    @Value("${grant.physicalStatus}")
    private int physicalStatus;

    @Value("${grant.periodPerformanceStart}")
    private int periodPerformanceStart;

    @Value("${grant.periodPerformanceEnd}")
    private int periodPerformanceEnd;

    @Value("${grant.climateChangeClassification}")
    private int climateChangeClassification;

    @Value("${grant.genderClassification}")
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

    public int getGrantAmountInOriginalCurrency() {
        return grantAmountInOriginalCurrency;
    }

    public int getGrantAmount() {
        return grantAmount;
    }

    public int getGrantUtilization() {
        return grantUtilization;
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

    public int getSubType() {
        return subType;
    }

    public int getStatus() {
        return status;
    }

    public int getPhysicalStatus() {
        return physicalStatus;
    }

    public int getPeriodPerformanceStart() {
        return periodPerformanceStart;
    }

    public int getPeriodPerformanceEnd() {
        return periodPerformanceEnd;
    }

    public int getClimateChangeClassification() {
        return climateChangeClassification;
    }

    public int getGenderClassification() {
        return genderClassification;
    }
}
