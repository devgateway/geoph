package org.devgateway.geoph.importer.processing.plugins;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.devgateway.geoph.enums.LocationAdmLevelEnum;
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
    private static final String UNDEFINED = "undefined";
    private static final double UTILIZATION = 1D;

    @Autowired
    private LoanColumns loanColumns;

    @Override
    protected void addProject(Row row, final int rowNumber) {
        Project p = new Project();
        try {
            p.setPhId(getCorrectPhId(getStringValueFromCell(row.getCell(loanColumns.getProjectId()), "project id", rowNumber, GeophProjectsImporter.onProblem.NOTHING, false)));

            String title = getStringValueFromCell(row.getCell(loanColumns.getProjectTitle()), "project title", rowNumber, GeophProjectsImporter.onProblem.NOTHING, false);
            p.setTitle(getMaxString(title));

            String fa = getStringValueFromCell(row.getCell(loanColumns.getFundingInstitution()), "funding institution", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true);
            if(StringUtils.isBlank(fa) || importBaseData.getFundingAgencies().get(fa.trim()) == null){
                fa = UNDEFINED;
            }
            p.setFundingAgency(importBaseData.getFundingAgencies().get(fa.trim()));

            String[] ias = getStringArrayValueFromCell(row.getCell(loanColumns.getImplementingAgency()), "implementing agency", rowNumber, GeophProjectsImporter.onProblem.NOTHING);
            Set<ProjectAgency> iaSet = new HashSet<>();
            boolean isFirstPA = true;
            for (String ia : ias) {
                if (importBaseData.getImplementingAgencies().get(ia.toLowerCase().trim()) != null) {
                    ProjectAgency pa;
                    if(isFirstPA) {
                        pa = new ProjectAgency(p, importBaseData.getImplementingAgencies().get(ia.toLowerCase().trim()), UTILIZATION);
                        isFirstPA = false;
                    } else {
                        pa = new ProjectAgency(p, importBaseData.getImplementingAgencies().get(ia.toLowerCase().trim()), 0D);
                    }
                    iaSet.add(pa);
                }
            }
            if(iaSet.size()>0){
                p.setImplementingAgencies(iaSet);
            } else {
                ProjectAgency pa = new ProjectAgency(p, importBaseData.getImplementingAgencies().get(UNDEFINED), UTILIZATION);
                p.setImplementingAgencies(new HashSet<>(Arrays.asList(pa)));
            }

            String ea = getStringValueFromCell(row.getCell(loanColumns.getExecutingAgency()), "executing agency", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true);
            if(StringUtils.isBlank(ea) || importBaseData.getExecutingAgencies().get(ea.trim()) == null){
                ea = UNDEFINED;
            }
            p.setExecutingAgency(importBaseData.getExecutingAgencies().get(ea.trim()));

            p.setOriginalCurrency(importBaseData.getCurrencies().get(
                    getStringValueFromCell(row.getCell(loanColumns.getOriginalCurrency()), "original currency", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            ));

            p.setTotalProjectAmountOriginal(
                    getDoubleValueFromCell(row.getCell(loanColumns.getLoanAmountInOriginalCurrency()), "loan amount in original currency", rowNumber, GeophProjectsImporter.onProblem.NOTHING)
            );

            p.setTotalProjectAmount(
                    getDoubleValueFromCell(row.getCell(loanColumns.getLoanAmount()), "loan amount", rowNumber, GeophProjectsImporter.onProblem.NOTHING, 0D)
            );

            Loan loan = new Loan();
            loan.setAmount(
                    getDoubleValueFromCell(row.getCell(loanColumns.getLoanUtilization()), "loan utilization", rowNumber, GeophProjectsImporter.onProblem.NOTHING, 0D)
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

            String sector = getStringValueFromCell(row.getCell(loanColumns.getSectors()), "sectors", rowNumber, GeophProjectsImporter.onProblem.NOTHING, false);
            Set<ProjectSector> sectorSet = new HashSet<>();

            if (sector!=null && importBaseData.getSectors().get(sector.toLowerCase().trim()) != null) {
                ProjectSector ps = new ProjectSector(p, importBaseData.getSectors().get(sector.toLowerCase().trim()), UTILIZATION);
                sectorSet.add(ps);
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
            if(locations.length>0) {
                Set<ProjectLocation> locationSet = new HashSet<>();
                Set<Location> locationRegion = new HashSet<>();
                Set<Location> locationProvince = new HashSet<>();
                Set<Location> locationMunicipality = new HashSet<>();
                for (String loc : locations) {
                    String locOk = loc.trim().endsWith(".0")?loc.trim().substring(0,loc.length()-2):loc.trim();
                    Location l = importBaseData.getLocations().get(locOk);
                    if(l!=null) {
                        if(l.getLevel()== LocationAdmLevelEnum.REGION.getLevel()){
                            locationRegion.add(l);
                        } else if(l.getLevel()==LocationAdmLevelEnum.PROVINCE.getLevel()){
                            locationProvince.add(l);
                            locationRegion.add(importBaseData.getLocationsById().get(l.getRegionId()));
                        } else if(l.getLevel()==LocationAdmLevelEnum.MUNICIPALITY.getLevel()){
                            locationMunicipality.add(l);
                            locationProvince.add(importBaseData.getLocationsById().get(l.getProvinceId()));
                            locationRegion.add(importBaseData.getLocationsById().get(l.getRegionId()));
                        }
                    }
                }
                locationMunicipality.stream().forEach(l->locationSet.add(new ProjectLocation(p, l, 0D)));
                locationProvince.stream().forEach(l->locationSet.add(new ProjectLocation(p, l, 0D)));
                locationRegion.stream().forEach(l->locationSet.add(new ProjectLocation(p, l, UTILIZATION /locationRegion.size())));
                p.setLocations(locationSet);
            }

            p.setStatus(importBaseData.getStatuses().get(
                    getStringValueFromCell(row.getCell(loanColumns.getStatus()), "status", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            ));

            p.setPhysicalStatus(importBaseData.getPhysicalStatuses().get(
                    getStringValueFromCell(row.getCell(loanColumns.getPhysicalStatus()), "physical status", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
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

            if(p.getActualOwpa()!=null && p.getTargetOwpa()!=null){
                p.setPhysicalProgress(p.getActualOwpa()/p.getTargetOwpa()*100);
            }

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
                    getMaxString(getStringValueFromCell(row.getCell(loanColumns.getNatureRRE()), "nature rre", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true))
            );

            p.setReasonRre(
                    getMaxString(getStringValueFromCell(row.getCell(loanColumns.getReasonRRE()), "reason rre", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true))
            );

            p.setLevelRre(
                    getMaxString(getStringValueFromCell(row.getCell(loanColumns.getLevelRRE()), "level rre", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true))
            );

            p.setDateRre(
                    getMaxString(getStringValueFromCell(row.getCell(loanColumns.getDateRRE()), "date rre", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true))
            );

            p.setIccNbConditions(
                    getMaxString(getStringValueFromCell(row.getCell(loanColumns.getIccNbConditions()), "icc nb conditions", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true))
            );

            p.setIccNbAction(
                    getMaxString(getStringValueFromCell(row.getCell(loanColumns.getIccNbAction()), "icc nb action", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true))
            );

            p.setComplianceToConditions(
                    getStringValueFromCell(row.getCell(loanColumns.getComplianceToConditions()), "compliance to conditions", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            );

            String[] climates = getStringArrayValueFromCell(row.getCell(loanColumns.getClimateChangeClassification()), "climate change", rowNumber, onProblem.NOTHING);
            Set<ClimateChange> climatesSet = new HashSet<>();
            for (String cc : climates) {
                if (importBaseData.getClimateChanges().get(cc.toLowerCase().trim()) != null) {
                    climatesSet.add(importBaseData.getClimateChanges().get(cc.toLowerCase().trim()));
                }
            }
            if(climatesSet.size()>0){
                p.setClimateChange(climatesSet);
            }

            String[] genders = getStringArrayValueFromCell(row.getCell(loanColumns.getGenderClassification()), "gender classification", rowNumber, onProblem.NOTHING);
            Set<GenderResponsiveness> genderSet = new HashSet<>();
            for (String gender : genders) {
                if (importBaseData.getGenderResponsiveness().get(gender.toLowerCase().trim()) != null) {
                    genderSet.add(importBaseData.getGenderResponsiveness().get(gender.toLowerCase().trim()));
                }
            }
            if(genderSet.size()>0){
                p.setGenderResponsiveness(genderSet);
            }


            importBaseData.getProjectService().save(p);
            importStats.addSuccessProjectAndTransactions(p.getTransactions().size());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
