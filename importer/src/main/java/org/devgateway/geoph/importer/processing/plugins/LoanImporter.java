package org.devgateway.geoph.importer.processing.plugins;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.devgateway.geoph.importer.processing.GeophProjectsImporter;
import org.devgateway.geoph.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author dbianco
 *         created on jul 05 2016.
 */
@Service("loanImporter")
public class LoanImporter extends GeophProjectsImporter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanImporter.class);
    private static final int MAX_LENGTH = 255;
    private static final String UNDEFINED = "undefined";

    @Autowired
    private LoanColumns loanColumns;

    @Override
    protected void addProject(Row row, final int rowNumber) {
        Project p = new Project();
        try {
            p.setPhId(getStringValueFromCell(row.getCell(loanColumns.getProjectId()), "project id", rowNumber, GeophProjectsImporter.onProblem.NOTHING, false));

            String title = getStringValueFromCell(row.getCell(loanColumns.getProjectTitle()), "project title", rowNumber, GeophProjectsImporter.onProblem.NOTHING, false);
            if(title.length()> MAX_LENGTH){
                p.setTitle(title.substring(0, MAX_LENGTH));
            } else {
                p.setTitle(title);
            }

            String fa = getStringValueFromCell(row.getCell(loanColumns.getFundingInstitution()), "funding institution", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true);
            if(StringUtils.isBlank(fa)){
                fa = UNDEFINED;
            }
            p.setFundingAgency(importBaseData.getFundingAgencies().get(fa));

            String[] ias = getStringArrayValueFromCell(row.getCell(loanColumns.getImplementingAgency()), "implementing agency", rowNumber, GeophProjectsImporter.onProblem.NOTHING);
            Set<Agency> iaSet = new HashSet<>();
            for (String ia : ias) {
                if (importBaseData.getImplementingAgencies().get(ia.toLowerCase().trim()) != null) {
                    iaSet.add(importBaseData.getImplementingAgencies().get(ia.toLowerCase().trim()));
                }
            }
            if(iaSet.size()>0){
                p.setImplementingAgencies(iaSet);
            } else {
                p.setImplementingAgencies(new HashSet<>(Arrays.asList(importBaseData.getImplementingAgencies().get(UNDEFINED))));
            }

            String ea = getStringValueFromCell(row.getCell(loanColumns.getExecutingAgency()), "executing agency", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true);
            if(StringUtils.isBlank(ea)){
                ea = UNDEFINED;
            }
            p.setExecutingAgency(importBaseData.getExecutingAgencies().get(ea));

            p.setOriginalCurrency(importBaseData.getCurrencies().get(
                    getStringValueFromCell(row.getCell(loanColumns.getOriginalCurrency()), "original currency", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            ));

            p.setTotalProjectAmountOriginal(
                    getDoubleValueFromCell(row.getCell(loanColumns.getLoanAmountInOriginalCurrency()), "loan amount in original currency", rowNumber, GeophProjectsImporter.onProblem.NOTHING)
            );

            p.setTotalProjectAmount(
                    getDoubleValueFromCell(row.getCell(loanColumns.getLoanAmount()), "loan amount", rowNumber, GeophProjectsImporter.onProblem.NOTHING)
            );

            Loan loan = new Loan();
            loan.setAmount(
                    getDoubleValueFromCell(row.getCell(loanColumns.getLoanUtilization()), "loan utilization", rowNumber, GeophProjectsImporter.onProblem.NOTHING)
            );
            loan.setTransactionTypeId(typeId);
            loan.setTransactionStatusId(statusId);
            loan.setDate(getImportDate());
            loan.setProject(p);
            p.setTransactions(new HashSet<>(Arrays.asList(loan)));

            p.setStartDate(
                    getDateValueFromCell(row.getCell(loanColumns.getStartDate()), "start date", rowNumber, GeophProjectsImporter.onProblem.NOTHING)
            );

            p.setEndDate(
                    getDateValueFromCell(row.getCell(loanColumns.getOriginalClosingDate()), "original closing date", rowNumber, GeophProjectsImporter.onProblem.NOTHING)
            );

            p.setRevisedClosingDate(
                    getDateValueFromCell(row.getCell(loanColumns.getRevisedClosingDate()), "revised closing date", rowNumber, GeophProjectsImporter.onProblem.NOTHING)
            );

            p.setRevisedClosingDate(
                    getDateValueFromCell(row.getCell(loanColumns.getRevisedClosingDate()), "revised closing date", rowNumber, GeophProjectsImporter.onProblem.NOTHING)
            );

            String[] sectors = getStringArrayValueFromCell(row.getCell(loanColumns.getSectors()), "sectors", rowNumber, GeophProjectsImporter.onProblem.NOTHING);
            Set<Sector> sectorSet = new HashSet<>();
            for (String sector : sectors) {
                if (importBaseData.getSectors().get(sector.toLowerCase().trim()) != null) {
                    sectorSet.add(importBaseData.getSectors().get(sector.toLowerCase().trim()));
                }
            }
            if(sectorSet.size()>0){
                p.setSectors(sectorSet);
            }

            String[] locations = getStringArrayValueFromCell(row.getCell(loanColumns.getMunicipality()), "municipality", rowNumber, GeophProjectsImporter.onProblem.NOTHING);
            if(locations.length==0){
                locations = getStringArrayValueFromCell(row.getCell(loanColumns.getProvince()), "province", rowNumber, GeophProjectsImporter.onProblem.NOTHING);
                if(locations.length==0){
                    locations = getStringArrayValueFromCell(row.getCell(loanColumns.getRegion()), "region", rowNumber, GeophProjectsImporter.onProblem.NOTHING);
                }
            }
            Set<Location> locationSet = new HashSet<>();
            for (String loc : locations) {
                if (StringUtils.isNotBlank(loc) && importBaseData.getLocations().get(loc.trim())!=null) {
                    locationSet.add(importBaseData.getLocations().get(loc.trim()));
                }
            }
            p.setLocations(locationSet);

            p.setStatus(importBaseData.getStatuses().get(
                    getStringValueFromCell(row.getCell(loanColumns.getStatus()), "status", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            ));

            p.setPhysicalStatus(importBaseData.getPhysicalStatuses().get(
                    getStringValueFromCell(row.getCell(loanColumns.getStatus()), "physical status", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            ));

            p.setPeriodPerformanceStart(
                    getDateValueFromCell(row.getCell(loanColumns.getPeriodPerformanceStart()), "period performance start", rowNumber, GeophProjectsImporter.onProblem.NOTHING)
            );

            p.setPeriodPerformanceEnd(
                    getDateValueFromCell(row.getCell(loanColumns.getPeriodPerformanceEnd()), "period performance end", rowNumber, GeophProjectsImporter.onProblem.NOTHING)
            );

            p.setActualOwpa(
                    getDoubleValueFromCell(row.getCell(loanColumns.getActualOwpa()), "actual owpa", rowNumber, GeophProjectsImporter.onProblem.NOTHING)
            );

            p.setTargetOwpa(
                    getDoubleValueFromCell(row.getCell(loanColumns.getTargetOwpa()), "target owpa", rowNumber, GeophProjectsImporter.onProblem.NOTHING)
            );

            p.setIssueType(
                    getStringValueFromCell(row.getCell(loanColumns.getIssueType()), "issue type", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            );

            p.setIssueDetail(
                    getStringValueFromCell(row.getCell(loanColumns.getIssueDetail()), "issue detail", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            );

            p.setActionTakenIa(
                    getStringValueFromCell(row.getCell(loanColumns.getActionTakenIa()), "action taken ia", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            );

            p.setActionToBeTakenIa(
                    getStringValueFromCell(row.getCell(loanColumns.getActionToBeTakenIa()), "action to be taken ia", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            );

            p.setActionTakenNeda(
                    getStringValueFromCell(row.getCell(loanColumns.getActionTakenIa()), "action taken neda", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            );

            p.setAccomplishmentUpdate(
                    getStringValueFromCell(row.getCell(loanColumns.getAccomplishmentUpdate()), "accomplishment update", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            );

            p.setNatureRre(
                    getStringValueFromCell(row.getCell(loanColumns.getNatureRRE()), "nature rre", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            );

            p.setReasonRre(
                    getStringValueFromCell(row.getCell(loanColumns.getReasonRRE()), "reason rre", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            );

            p.setLevelRre(
                    getStringValueFromCell(row.getCell(loanColumns.getLevelRRE()), "level rre", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            );

            p.setDateRre(
                    getStringValueFromCell(row.getCell(loanColumns.getDateRRE()), "date rre", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            );

            p.setIccNbConditions(
                    getStringValueFromCell(row.getCell(loanColumns.getIccNbConditions()), "icc nb conditions", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            );

            p.setIccNbAction(
                    getStringValueFromCell(row.getCell(loanColumns.getIccNbAction()), "icc nb action", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            );

            p.setComplianceToConditions(
                    getStringValueFromCell(row.getCell(loanColumns.getComplianceToConditions()), "compliance to conditions", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            );


            importBaseData.getProjectService().save(p);
            importStats.addSuccessProjectAndTransactions(p.getTransactions().size());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
