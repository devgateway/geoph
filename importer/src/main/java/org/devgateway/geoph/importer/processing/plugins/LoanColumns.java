package org.devgateway.geoph.importer.processing.plugins;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author dbianco
 *         created on jul 05 2016.
 */
@Service
public class LoanColumns {

    @Value("${loan.projectId}")
    private int projectId;

    @Value("${loan.projectTitle}")
    private int projectTitle;

    @Value("${loan.fundingInstitution}")
    private int fundingInstitution;

    @Value("${loan.executingAgency}")
    private int executingAgency;

    @Value("${loan.implementingAgency}")
    private int implementingAgency;

    @Value("${loan.classification}")
    private int classification;

    @Value("${loan.originalCurrency}")
    private int originalCurrency;

    @Value("${loan.loanAmountInOriginalCurrency}")
    private int loanAmountInOriginalCurrency;

    @Value("${loan.loanAmount}")
    private int loanAmount;

    @Value("${loan.loanUtilization}")
    private int loanUtilization;

    @Value("${loan.startDate}")
    private int startDate;

    @Value("${loan.originalClosingDate}")
    private int originalClosingDate;

    @Value("${loan.revisedClosingDate}")
    private int revisedClosingDate;

    @Value("${loan.sectors}")
    private int sectors;

    @Value("${loan.region}")
    private int region;

    @Value("${loan.province}")
    private int province;

    @Value("${loan.municipality}")
    private int municipality;

    @Value("${loan.subType}")
    private int subType;

    @Value("${loan.status}")
    private int status;

    @Value("${loan.physicalStatus}")
    private int physicalStatus;

    @Value("${loan.periodPerformanceStart}")
    private int periodPerformanceStart;

    @Value("${loan.periodPerformanceEnd}")
    private int periodPerformanceEnd;

    @Value("${loan.actualOwpa}")
    private int actualOwpa;

    @Value("${loan.targetOwpa}")
    private int targetOwpa;

    @Value("${loan.issueType}")
    private int issueType;

    @Value("${loan.issueDetail}")
    private int issueDetail;

    @Value("${loan.actionTakenIa}")
    private int actionTakenIa;

    @Value("${loan.actionToBeTakenIa}")
    private int actionToBeTakenIa;

    @Value("${loan.actionTakenNeda}")
    private int actionTakenNeda;

    @Value("${loan.accomplishmentUpdate}")
    private int accomplishmentUpdate;

    @Value("${loan.natureRRE}")
    private int natureRRE;

    @Value("${loan.reasonRRE}")
    private int reasonRRE;

    @Value("${loan.levelRRE}")
    private int levelRRE;

    @Value("${loan.dateRRE}")
    private int dateRRE;

    @Value("${loan.iccNbAction}")
    private int iccNbAction;

    @Value("${loan.iccNbConditions}")
    private int iccNbConditions;

    @Value("${loan.complianceToConditions}")
    private int complianceToConditions;

    @Value("${loan.climateChangeClassification}")
    private int climateChangeClassification;

    @Value("${loan.genderClassification}")
    private int genderClassification;

    public int getProjectId() {
        return projectId;
    }

    public int getLoanUtilization() {
        return loanUtilization;
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

    public int getActionTakenIa() {
        return actionTakenIa;
    }

    public int getActionToBeTakenIa() {
        return actionToBeTakenIa;
    }

    public int getActionTakenNeda() {
        return actionTakenNeda;
    }

    public int getAccomplishmentUpdate() {
        return accomplishmentUpdate;
    }

    public int getNatureRRE() {
        return natureRRE;
    }

    public int getReasonRRE() {
        return reasonRRE;
    }

    public int getLevelRRE() {
        return levelRRE;
    }

    public int getDateRRE() {
        return dateRRE;
    }

    public int getIccNbAction() {
        return iccNbAction;
    }

    public int getIccNbConditions() {
        return iccNbConditions;
    }

    public int getComplianceToConditions() {
        return complianceToConditions;
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

    public int getLoanAmountInOriginalCurrency() {
        return loanAmountInOriginalCurrency;
    }

    public int getLoanAmount() {
        return loanAmount;
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
