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
 *         created on jul 04 2016.
 */
@Service("grantImporter")
public class GrantImporter extends GeophProjectsImporter {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrantImporter.class);
    private static final Logger LOG_REPORT = LoggerFactory.getLogger("report");

    private static final int MAX_LENGTH = 255;
    private static final String UNDEFINED = "undefined";
    private static final double UTILIZATION = 1D;

    @Value("${import.without.implementingAgencies}")
    private boolean importWithoutIas;

    @Autowired
    private GrantColumns grantColumns;

    @Override
    protected void addProject(Row row, final int rowNumber) {
        int currentRow = rowNumber - 1;
        Project p = new Project();
        try {
            String phId = getCorrectPhId(getStringValueFromCell(row.getCell(grantColumns.getProjectId()), "project id", rowNumber, onProblem.NOTHING, false));
            if(StringUtils.isBlank(phId)) {
                LOG_REPORT.info("A project won't be imported at line " + currentRow);
                importStats.addError(" * Project Id not found at row " + currentRow);
                importStats.addFailedProject(" * Id is empty");
                return;
            }
            p.setPhId(phId);
            String title = getStringValueFromCell(row.getCell(grantColumns.getProjectTitle()), "project title", rowNumber, onProblem.NOTHING, false);
            if(title.length()> MAX_LENGTH){
                p.setTitle(title.substring(0, MAX_LENGTH));
            } else {
                p.setTitle(title);
            }

            String fa = getStringValueFromCell(row.getCell(grantColumns.getFundingInstitution()), "funding institution", rowNumber, onProblem.NOTHING, true);
            if(StringUtils.isBlank(fa) || importBaseData.getFundingAgencies().get(fa.trim()) == null){
                fa = UNDEFINED;
            }
            p.setFundingAgency(importBaseData.getFundingAgencies().get(fa.trim()));

            String[] ias = getStringArrayValueFromCell(row.getCell(grantColumns.getImplementingAgency()), "implementing agency", rowNumber, onProblem.NOTHING);
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

            p.setGrantClassification(importBaseData.getClassifications().get(
                    getStringValueFromCell(row.getCell(grantColumns.getClassification()), "classification", rowNumber, onProblem.NOTHING, true)
            ));

            String ea = getStringValueFromCell(row.getCell(grantColumns.getExecutingAgency()), "executing agency", rowNumber, onProblem.NOTHING, true);
            if(StringUtils.isBlank(ea) || importBaseData.getExecutingAgencies().get(ea.trim()) == null){
                ea = UNDEFINED;
            }
            p.setExecutingAgency(importBaseData.getExecutingAgencies().get(ea.trim()));

            p.setOriginalCurrency(importBaseData.getCurrencies().get(
                    getStringValueFromCell(row.getCell(grantColumns.getOriginalCurrency()), "original currency", rowNumber, onProblem.NOTHING, true)
            ));

            p.setTotalProjectAmountOriginal(
                    getDoubleValueFromCell(row.getCell(grantColumns.getGrantAmountInOriginalCurrency()), "project amount in original currency", rowNumber, onProblem.NOTHING)
            );

            p.setTotalProjectAmount(
                    getDoubleValueFromCell(row.getCell(grantColumns.getGrantAmount()), "project amount", rowNumber, onProblem.NOTHING, 0D)
            );

            Grant commitment = new Grant();
            commitment.setAmount(getDoubleValueFromCell(row.getCell(grantColumns.getGrantAmount()), "project amount", rowNumber, onProblem.NOTHING, 0D));
            commitment.setTransactionTypeId(TransactionTypeEnum.COMMITMENTS.getId());
            commitment.setTransactionStatusId(TransactionStatusEnum.ACTUAL.getId());
            commitment.setDate(getImportDate());
            GrantSubType grantSubType = importBaseData.getGrantSubTypes().get(
                    getStringValueFromCell(row.getCell(grantColumns.getSubType()), "grant subType", rowNumber, onProblem.NOTHING, true)
            );
            if(grantSubType!=null){
                commitment.setGrantSubTypeId(grantSubType.getId());
            }
            commitment.setProject(p);

            Grant grant = new Grant();
            grant.setAmount(
                    getDoubleValueFromCell(row.getCell(grantColumns.getGrantUtilization()), "grant utilization", rowNumber, onProblem.NOTHING, 0D)
            );
            grant.setTransactionTypeId(typeId);
            grant.setTransactionStatusId(statusId);
            grant.setDate(getImportDate());

            if(grantSubType!=null){
                grant.setGrantSubTypeId(grantSubType.getId());
            }
            grant.setProject(p);
            p.setTransactions(new HashSet<>(Arrays.asList(commitment, grant)));

            p.setStartDate(
                    getDateValueFromCell(row.getCell(grantColumns.getStartDate()), "start date", rowNumber, onProblem.NOTHING)
            );

            p.setEndDate(
                    getDateValueFromCell(row.getCell(grantColumns.getOriginalClosingDate()), "original closing date", rowNumber, onProblem.NOTHING)
            );

            p.setRevisedClosingDate(
                    getDateValueFromCell(row.getCell(grantColumns.getRevisedClosingDate()), "revised closing date", rowNumber, onProblem.NOTHING)
            );

            p.setRevisedClosingDate(
                    getDateValueFromCell(row.getCell(grantColumns.getRevisedClosingDate()), "revised closing date", rowNumber, onProblem.NOTHING)
            );

            String sector = getStringValueFromCell(row.getCell(grantColumns.getSectors()), "sectors", rowNumber, GeophProjectsImporter.onProblem.NOTHING, false);
            Set<ProjectSector> sectorSet = new HashSet<>();
            if (sector!=null && importBaseData.getSectors().get(sector.toLowerCase().trim()) != null) {
                ProjectSector ps = new ProjectSector(p, importBaseData.getSectors().get(sector.toLowerCase().trim()), UTILIZATION);
                sectorSet.add(ps);
            }
            if(sectorSet.size()>0){
                p.setSectors(sectorSet);
            }

            String[] locations = getStringArrayValueFromCell(row.getCell(grantColumns.getMunicipality()), "municipality", rowNumber, onProblem.NOTHING);
            if(locations.length==0){
                locations = getStringArrayValueFromCell(row.getCell(grantColumns.getProvince()), "province", rowNumber, onProblem.NOTHING);
                if(locations.length==0){
                    locations = getStringArrayValueFromCell(row.getCell(grantColumns.getRegion()), "region", rowNumber, onProblem.NOTHING);
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
                    getStringValueFromCell(row.getCell(grantColumns.getStatus()), "status", rowNumber, onProblem.NOTHING, true)
            ));

            p.setPhysicalStatus(importBaseData.getPhysicalStatuses().get(
                    getStringValueFromCell(row.getCell(grantColumns.getPhysicalStatus()), "physical status", rowNumber, onProblem.NOTHING, true)
            ));

            p.setPeriodPerformanceStart(
                    getDateValueFromCell(row.getCell(grantColumns.getPeriodPerformanceStart()), "period performance start", rowNumber, onProblem.NOTHING)
            );

            p.setPeriodPerformanceEnd(
                    getDateValueFromCell(row.getCell(grantColumns.getPeriodPerformanceEnd()), "period performance end", rowNumber, onProblem.NOTHING)
            );



            String[] climates = getStringArrayValueFromCell(row.getCell(grantColumns.getClimateChangeClassification()), "climate change", rowNumber, onProblem.NOTHING);
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

            String[] genders = getStringArrayValueFromCell(row.getCell(grantColumns.getGenderClassification()), "gender classification", rowNumber, onProblem.NOTHING);
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
