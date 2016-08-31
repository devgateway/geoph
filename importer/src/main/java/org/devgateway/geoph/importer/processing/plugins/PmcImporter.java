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
 *         created on jul 04 2016.
 */
@Service("pmcImporter")
public class PmcImporter extends GeophProjectsImporter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmcImporter.class);
    private static final int MAX_LENGTH = 255;
    private static final String UNDEFINED = "undefined";
    private static final String LOCALLY_FUNDED = "locally-funded";

    @Autowired
    private PmcColumns pmcColumns;

    @Override
    protected void addProject(Row row, final int rowNumber) {
        Project p = new Project();
        try {
            p.setPhId(getStringValueFromCell(row.getCell(pmcColumns.getProjectId()), "project id", rowNumber, onProblem.NOTHING, false));

            String title = getStringValueFromCell(row.getCell(pmcColumns.getProjectTitle()), "project title", rowNumber, onProblem.NOTHING, false);
            if(title.length()> MAX_LENGTH){
                p.setTitle(title.substring(0, MAX_LENGTH));
            } else {
                p.setTitle(title);
            }

            String fa = getStringValueFromCell(row.getCell(pmcColumns.getFundingInstitution()), "funding institution", rowNumber, onProblem.NOTHING, true);
            if(StringUtils.isBlank(fa) || importBaseData.getFundingAgencies().get(fa.trim()) == null){
                fa = LOCALLY_FUNDED;
            }
            p.setFundingAgency(importBaseData.getFundingAgencies().get(fa.trim()));

            String[] ias = getStringArrayValueFromCell(row.getCell(pmcColumns.getImplementingAgency()), "implementing agency", rowNumber, onProblem.NOTHING);
            Set<ProjectAgency> iaSet = new HashSet<>();
            boolean isFirstPA = true;
            for (String ia : ias) {
                if (importBaseData.getImplementingAgencies().get(ia.toLowerCase().trim()) != null) {
                    ProjectAgency pa;
                    if(isFirstPA) {
                        pa = new ProjectAgency(p, importBaseData.getImplementingAgencies().get(ia.toLowerCase().trim()), 1D);
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
                ProjectAgency pa = new ProjectAgency(p, importBaseData.getImplementingAgencies().get(UNDEFINED), 1D);
                p.setImplementingAgencies(new HashSet<>(Arrays.asList(pa)));
            }

            p.setGrantClassification(importBaseData.getClassifications().get(
                    getStringValueFromCell(row.getCell(pmcColumns.getClassification()), "classification", rowNumber, onProblem.NOTHING, true)
            ));

            String ea = getStringValueFromCell(row.getCell(pmcColumns.getExecutingAgency()), "executing agency", rowNumber, onProblem.NOTHING, true);
            if(StringUtils.isBlank(ea) || importBaseData.getExecutingAgencies().get(ea.trim()) == null){
                ea = UNDEFINED;
            }
            p.setExecutingAgency(importBaseData.getExecutingAgencies().get(ea.trim()));

            p.setOriginalCurrency(importBaseData.getCurrencies().get(
                    getStringValueFromCell(row.getCell(pmcColumns.getOriginalCurrency()), "original currency", rowNumber, onProblem.NOTHING, true)
            ));

            p.setTotalProjectAmountOriginal(
                    getDoubleValueFromCell(row.getCell(pmcColumns.getPmcAmountInOriginalCurrency()), "pmc amount in original currency", rowNumber, onProblem.NOTHING)
            );

            p.setTotalProjectAmount(
                    getDoubleValueFromCell(row.getCell(pmcColumns.getPmcAmount()), "pmc amount", rowNumber, onProblem.NOTHING, 0D)
            );

            PublicInvestment pmc = new PublicInvestment();
            pmc.setAmount(
                    getDoubleValueFromCell(row.getCell(pmcColumns.getPmcUtilization()), "pmc utilization", rowNumber, onProblem.NOTHING, 0D)
            );
            pmc.setTransactionTypeId(typeId);
            pmc.setTransactionStatusId(statusId);
            pmc.setDate(getImportDate());
            
            pmc.setProject(p);
            p.setTransactions(new HashSet<>(Arrays.asList(pmc)));

            p.setStartDate(
                    getDateValueFromCell(row.getCell(pmcColumns.getStartDate()), "start date", rowNumber, onProblem.NOTHING)
            );

            p.setEndDate(
                    getDateValueFromCell(row.getCell(pmcColumns.getOriginalClosingDate()), "original closing date", rowNumber, onProblem.NOTHING)
            );

            p.setRevisedClosingDate(
                    getDateValueFromCell(row.getCell(pmcColumns.getRevisedClosingDate()), "revised closing date", rowNumber, onProblem.NOTHING)
            );

            p.setRevisedClosingDate(
                    getDateValueFromCell(row.getCell(pmcColumns.getRevisedClosingDate()), "revised closing date", rowNumber, onProblem.NOTHING)
            );

            String sector = getStringValueFromCell(row.getCell(pmcColumns.getSectors()), "sectors", rowNumber, GeophProjectsImporter.onProblem.NOTHING, false);
            Set<ProjectSector> sectorSet = new HashSet<>();

            if (sector!=null && importBaseData.getSectors().get(sector.toLowerCase().trim()) != null) {
                ProjectSector ps = new ProjectSector(p, importBaseData.getSectors().get(sector.toLowerCase().trim()), 1D);
                sectorSet.add(ps);
            }
            if(sectorSet.size()>0){
                p.setSectors(sectorSet);
            }

            String[] locations = getStringArrayValueFromCell(row.getCell(pmcColumns.getMunicipality()), "municipality", rowNumber, onProblem.NOTHING);
            if(locations.length==0){
                locations = getStringArrayValueFromCell(row.getCell(pmcColumns.getProvince()), "province", rowNumber, onProblem.NOTHING);
                if(locations.length==0){
                    locations = getStringArrayValueFromCell(row.getCell(pmcColumns.getRegion()), "region", rowNumber, onProblem.NOTHING);
                }
            }
            if(locations.length>0) {
                Set<ProjectLocation> locationSet = new HashSet<>();
                Set<Location> locationRegion = new HashSet<>();
                Set<Location> locationProvince = new HashSet<>();
                Set<Location> locationMunicipality = new HashSet<>();
                for (String loc : locations) {
                    Location l = importBaseData.getLocations().get(loc.trim());
                    if(l!=null) {
                        if(l.getLevel()==LocationAdmLevelEnum.REGION.getLevel()){
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
                locationRegion.stream().forEach(l->locationSet.add(new ProjectLocation(p, l, 1/locationRegion.size())));
                p.setLocations(locationSet);
            }
            p.setStatus(importBaseData.getStatuses().get(
                    getStringValueFromCell(row.getCell(pmcColumns.getStatus()), "status", rowNumber, onProblem.NOTHING, true)
            ));

            p.setPhysicalStatus(importBaseData.getPhysicalStatuses().get(
                    getStringValueFromCell(row.getCell(pmcColumns.getPhysicalStatus()), "physical status", rowNumber, onProblem.NOTHING, true)
            ));

            p.setActualOwpa(
                    getDoubleValueFromCell(row.getCell(pmcColumns.getActualOwpa()), "actual owpa", rowNumber, GeophProjectsImporter.onProblem.NOTHING)
            );

            p.setTargetOwpa(
                    getDoubleValueFromCell(row.getCell(pmcColumns.getTargetOwpa()), "target owpa", rowNumber, GeophProjectsImporter.onProblem.NOTHING)
            );

            p.setPhysicalProgress(
                    getDoubleValueFromCell(row.getCell(pmcColumns.getPhysicalProgress()), "physical performance", rowNumber, GeophProjectsImporter.onProblem.NOTHING)
            );

            p.setIssueType(
                    getStringValueFromCell(row.getCell(pmcColumns.getIssueType()), "issue type", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            );

            p.setIssueDetail(
                    getStringValueFromCell(row.getCell(pmcColumns.getIssueDetail()), "issue detail", rowNumber, GeophProjectsImporter.onProblem.NOTHING, true)
            );

            p.setCumulativeAllotment(
                    getDoubleValueFromCell(row.getCell(pmcColumns.getCumulativeAllotment()), "cumulative allotment", rowNumber, GeophProjectsImporter.onProblem.NOTHING)
            );

            p.setCumulativeObligations(
                    getDoubleValueFromCell(row.getCell(pmcColumns.getCumulativeObligations()), "cumulative obligations", rowNumber, GeophProjectsImporter.onProblem.NOTHING)
            );

            importBaseData.getProjectService().save(p);
            importStats.addSuccessProjectAndTransactions(p.getTransactions().size());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
