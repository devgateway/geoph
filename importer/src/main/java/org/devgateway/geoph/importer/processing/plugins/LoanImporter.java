package org.devgateway.geoph.importer.processing.plugins;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.devgateway.geoph.enums.LocationAdmLevelEnum;
import org.devgateway.geoph.enums.TransactionStatusEnum;
import org.devgateway.geoph.enums.TransactionTypeEnum;
import org.devgateway.geoph.importer.processing.GeophProjectsImporter;
import org.devgateway.geoph.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private static final Logger LOG_REPORT = LoggerFactory.getLogger("report");
    private static final String UNDEFINED = "undefined";
    private static final double UTILIZATION = 1D;

    @Value("${import.without.implementingAgencies}")
    private boolean importWithoutIas;

    @Autowired
    private LoanColumns loanColumns;

    @Override
    protected void addProject(Row row, final int rowNumber) {
        int currentRow = rowNumber - 1;
        Project p = new Project();
        try {
            String phId = getCorrectPhId(getStringValueFromCell(row.getCell(loanColumns.getProjectId()), "project id", rowNumber, onProblem.NOTHING, false));
            if(StringUtils.isBlank(phId)) {
                LOG_REPORT.info("A project won't be imported at line " + currentRow);
                importStats.addError(" * Project Id not found at row " + currentRow);
                importStats.addFailedProject(" * Id is empty");
                return;
            }
            p.setPhId(phId);
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
                } else {
                    if(isFirstPA && !importWithoutIas){
                        String iaLogMessage = "At row " + currentRow + " (Project Id " + p.getPhId() + ") the first Implementing Agency could not be matched with our records. ";
                        LOG_REPORT.info(iaLogMessage + "The project won't be imported");
                        importStats.addError(" * IAs not found at row " + currentRow);
                        importStats.addFailedProject(" * " + p.getPhId());
                        return;
                    } else {
                        importStats.addWarning(" * IA undefined at row " + currentRow);
                        iaSet.add(new ProjectAgency(p, importBaseData.getImplementingAgencies().get(UNDEFINED), 0D));
                    }
                }
            }
            if(iaSet.size()>0){
                p.setImplementingAgencies(iaSet);
            } else {
                String iaLogMessage = "At row " + currentRow + " (Project Id " + p.getPhId() + ") there were no Implementing Agencies that could be matched with our records. ";
                if(importWithoutIas){
                    ProjectAgency pa = new ProjectAgency(p, importBaseData.getImplementingAgencies().get(UNDEFINED), UTILIZATION);
                    p.setImplementingAgencies(new HashSet(Arrays.asList(pa)));
                    LOG_REPORT.info(iaLogMessage + "Project will be imported and IA will be Undefined");
                    importStats.addWarning(" * IA added as undefined at row " + currentRow);
                } else {
                    LOG_REPORT.info(iaLogMessage + "The project won't be imported");
                    importStats.addError(" * IAs not found at row " + currentRow);
                    importStats.addFailedProject(" * " + p.getPhId());
                    return;
                }
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

            Loan commitment = new Loan();
            commitment.setAmount(getDoubleValueFromCell(row.getCell(loanColumns.getLoanAmount()), "project amount", rowNumber, onProblem.NOTHING, 0D));
            commitment.setTransactionTypeId(TransactionTypeEnum.COMMITMENTS.getId());
            commitment.setTransactionStatusId(TransactionStatusEnum.ACTUAL.getId());
            commitment.setDate(getImportDate());
            commitment.setProject(p);

            Loan loan = new Loan();
            loan.setAmount(
                    getDoubleValueFromCell(row.getCell(loanColumns.getLoanUtilization()), "loan utilization", rowNumber, GeophProjectsImporter.onProblem.NOTHING, 0D)
            );
            loan.setTransactionTypeId(typeId);
            loan.setTransactionStatusId(statusId);
            loan.setDate(getImportDate());
            loan.setProject(p);
            p.setTransactions(new HashSet<>(Arrays.asList(commitment, loan)));

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
                            if(l.getRegion()!=null) {
                                locationRegion.add(l.getRegion());
                            }
                        } else if(l.getLevel()==LocationAdmLevelEnum.MUNICIPALITY.getLevel()){
                            locationMunicipality.add(l);
                            if(l.getProvince()!=null) {
                                locationProvince.add(l.getProvince());
                            }
                            if(l.getRegion()!=null) {
                                locationRegion.add(l.getRegion());
                            }
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
            Set<ProjectClimateChange> climatesSet = new HashSet<>();
            boolean isFirstCC = true;
            for (String cc : climates) {
                if (importBaseData.getClimateChanges().get(cc.toLowerCase().trim()) != null) {
                    ProjectClimateChange pa;
                    if(isFirstCC) {
                        pa = new ProjectClimateChange(p, importBaseData.getClimateChanges().get(cc.toLowerCase().trim()), UTILIZATION);
                        isFirstCC = false;
                    } else {
                        pa = new ProjectClimateChange(p, importBaseData.getClimateChanges().get(cc.toLowerCase().trim()), 0D);
                    }
                    climatesSet.add(pa);
                }
            }
            if(climatesSet.size()>0){
                p.setClimateChange(climatesSet);
            }

            String[] genders = getStringArrayValueFromCell(row.getCell(loanColumns.getGenderClassification()), "gender classification", rowNumber, onProblem.NOTHING);
            Set<ProjectGenderResponsiveness> genderSet = new HashSet<>();
            boolean isFirstGR = true;
            for (String gender : genders) {
                if (importBaseData.getGenderResponsiveness().get(gender.toLowerCase().trim()) != null) {
                    ProjectGenderResponsiveness pa;
                    if(isFirstGR) {
                        pa = new ProjectGenderResponsiveness(p, importBaseData.getGenderResponsiveness().get(gender.toLowerCase().trim()), UTILIZATION);
                        isFirstGR = false;
                    } else {
                        pa = new ProjectGenderResponsiveness(p, importBaseData.getGenderResponsiveness().get(gender.toLowerCase().trim()), 0D);
                    }
                    genderSet.add(pa);
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
