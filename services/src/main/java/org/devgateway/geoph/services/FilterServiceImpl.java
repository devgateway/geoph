package org.devgateway.geoph.services;

import org.devgateway.geoph.core.repositories.*;
import org.devgateway.geoph.core.services.FilterService;
import org.devgateway.geoph.enums.LocationAdmLevelEnum;
import org.devgateway.geoph.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dbianco
 *         created on feb 29 2016.
 */
@Service
public class FilterServiceImpl implements FilterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterServiceImpl.class);


    private static final Sort SORT_BY_NAME = new Sort(Sort.Direction.ASC, "name");

    @Autowired
    ImplementingAgencyRepository impAgencyRepository;

    @Autowired
    FundingAgencyRepository fundingAgencyRepository;

    @Autowired
    ExecutingAgencyRepository executingAgencyRepository;

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    PhysicalStatusRepository physicalStatusRepository;

    @Autowired
    FlowTypeRepository flowTypeRepository;

    @Autowired
    ClassificationRepository classificationRepository;

    @Autowired
    GrantSubTypeRepository grantSubTypeRepository;

    @Autowired
    ClimateChangeRepository climateChangeRepository;

    @Autowired
    GenderResponsivenessRepository genderResponsivenessRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public List<ImplementingAgency> findAllImpAgencies() {
        LOGGER.debug("Getting all implementing agencies");
        return impAgencyRepository.findAll();
    }

    @Override
    public Integer countImpAgencies() {
        LOGGER.debug("Count implementing agencies");
        return impAgencyRepository.count();
    }

    @Override
    public List<FundingAgency> findAllFundingAgencies() {
        LOGGER.debug("Getting all funding agencies");
        return fundingAgencyRepository.findAll();
    }

    @Override
    public List<ExecutingAgency> findAllExecutingAgencies() {
        LOGGER.debug("Getting all executing agencies");
        return executingAgencyRepository.findAll();
    }

    @Override
    public List<Sector> findAllSectorByLevel(int level) {
        LOGGER.debug("Getting all sectors by level");
        return sectorRepository.findByLevel(level);
    }

    @Override
    public List<Sector> findAllSectors() {
        LOGGER.debug("Getting all sectors");
        return sectorRepository.findAll();
    }

    @Override
    public List<Location> findAllLocations() {
        LOGGER.debug("Getting all locations");
        return locationRepository.findAll();
    }

    @Override
    public Location findLocationByCode(String code) {
        LOGGER.debug("Getting location by code");
        return locationRepository.findByCode(code);
    }

    @Override
    public List<Location> findLocationsByLevel(LocationAdmLevelEnum level) {
        LOGGER.debug("Getting all locations of level: {}", level);
        List<Location> locationList = locationRepository.findLocationsByLevel(level.getLevel());
        expandLocationItems(locationList);
        return locationList;
    }

    private void expandLocationItems(List<Location> locationList) {
        for (Location loc : locationList) {
            List<Location> items = loc.getItems();
            if (items != null) {
                expandLocationItems(items);
            }
        }
    }

    public List<Location> findLocationsByParentId(long parentId) {
        LOGGER.debug("Getting all children locations for parentId {}", parentId);
        return locationRepository.findLocationsByParentId(parentId);
    }

    @Override
    public List<Status> findAllStatuses() {
        LOGGER.debug("Getting all statuses");
        return statusRepository.findAll();
    }

    @Override
    public List<PhysicalStatus> findAllPhysicalStatus() {
        LOGGER.debug("Getting all statuses");
        return physicalStatusRepository.findAll();
    }

    @Override
    public List<Map<String, Object>> findAllFlowTypes() {
        List<Map<String, Object>> retList = new ArrayList<>();
        List<FlowType> flowTypes = flowTypeRepository.findAll();
        for (FlowType flowType : flowTypes) {
            Map<String, Object> flowTypesMap = new HashMap<>();
            flowTypesMap.put("id", String.valueOf(flowType.getId()));
            flowTypesMap.put("name", flowType.getName());
            if (flowType.getName().toLowerCase().equals("grant")) {
                List<GrantSubType> grantSubTypes = grantSubTypeRepository.findAll();
                List<Map<String, Object>> grantSubTypeList = new ArrayList<>();
                for (GrantSubType grantSubType : grantSubTypes) {
                    Map<String, Object> grantSubTypeMap = new HashMap<>();
                    grantSubTypeMap.put("id", String.valueOf(flowType.getId()) + "." + String.valueOf(grantSubType.getId()));
                    grantSubTypeMap.put("name", grantSubType.getName());
                    grantSubTypeList.add(grantSubTypeMap);
                }
                flowTypesMap.put("items", grantSubTypeList);
            }
            retList.add(flowTypesMap);
        }
        return retList;
    }

    @Override
    public List<ClimateChange> findAllClimateChanges() {
        return climateChangeRepository.findAll(SORT_BY_NAME);
    }

    @Override
    public List<GenderResponsiveness> findAllGenderResponsiveness() {
        return genderResponsivenessRepository.findAll();
    }

    @Override
    public List<Classification> findAllClassifications() {
        return classificationRepository.findAll();
    }

    @Override
    public List<GrantSubType> findAllGrantSubTypes() {
        return grantSubTypeRepository.findAll();
    }

    @Override
    public List<Double> findFinancialAmountPeriod() {
        return projectRepository.getFinancialAmountBoundaries();
    }

    @Override
    public List<String> findImpPeriodBoundaries() {
        return projectRepository.getImpPeriodBoundaries();
    }

    @Override
    public List<String> findGrantPeriodBoundaries() {
        return projectRepository.getGrantPeriodBoundaries();
    }

    @Override
    public List<Float> findTargetReachedPeriodBoundaries() {
        return projectRepository.getTargetReachedPeriodBoundaries();
    }

    @Override
    public List<Currency> findAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public Location findLocationById(Long locId) {
        return locationRepository.findById(locId);
    }

}
