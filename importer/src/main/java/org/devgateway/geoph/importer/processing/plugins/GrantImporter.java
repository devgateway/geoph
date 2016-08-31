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
 *         created on jul 04 2016.
 */
@Service("grantImporter")
public class GrantImporter extends GeophProjectsImporter {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrantImporter.class);
    private static final int MAX_LENGTH = 255;
    private static final String UNDEFINED = "undefined";

    @Autowired
    private GrantColumns grantColumns;

    @Override
    protected void addProject(Row row, final int rowNumber) {
        Project p = new Project();
        try {
            p.setPhId(getStringValueFromCell(row.getCell(grantColumns.getProjectId()), "project id", rowNumber, onProblem.NOTHING, false));

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
                        pa = new ProjectAgency(p, importBaseData.getImplementingAgencies().get(ia.toLowerCase().trim()), 100D);
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
                ProjectAgency pa = new ProjectAgency(p, importBaseData.getImplementingAgencies().get(UNDEFINED), 100D);
                p.setImplementingAgencies(new HashSet<>(Arrays.asList(pa)));
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

            Grant grant = new Grant();
            grant.setAmount(
                    getDoubleValueFromCell(row.getCell(grantColumns.getGrantUtilization()), "grant utilization", rowNumber, onProblem.NOTHING, 0D)
            );
            grant.setTransactionTypeId(typeId);
            grant.setTransactionStatusId(statusId);
            grant.setDate(getImportDate());
            GrantSubType grantSubType = importBaseData.getGrantSubTypes().get(
                    getStringValueFromCell(row.getCell(grantColumns.getSubType()), "grant subType", rowNumber, onProblem.NOTHING, true)
            );
            if(grantSubType!=null){
                grant.setGrantSubTypeId(grantSubType.getId());
            }
            grant.setProject(p);
            p.setTransactions(new HashSet<>(Arrays.asList(grant)));

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
            boolean isFirstSector = true;

            if (sector!=null && importBaseData.getSectors().get(sector.toLowerCase().trim()) != null) {
                if(isFirstSector) {
                    ProjectSector ps = new ProjectSector(p, importBaseData.getSectors().get(sector.toLowerCase().trim()), 100D);
                    sectorSet.add(ps);
                }
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
            Set<Location> locationSet = new HashSet<>();
            for (String loc : locations) {
                if(StringUtils.isNotBlank(loc) && loc.indexOf(".")>0) {
                    if (importBaseData.getLocations().get(loc.trim().split("\\.")[0]) != null) {
                        locationSet.add(importBaseData.getLocations().get(loc.trim().split("\\.")[0]));
                    }
                } else {
                    if (importBaseData.getLocations().get(loc.trim()) != null) {
                        locationSet.add(importBaseData.getLocations().get(loc.trim()));
                    }
                }
            }
            p.setLocations(locationSet);

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

            importBaseData.getProjectService().save(p);
            importStats.addSuccessProjectAndTransactions(p.getTransactions().size());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
