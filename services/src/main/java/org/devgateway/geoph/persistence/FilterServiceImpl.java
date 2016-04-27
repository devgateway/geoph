package org.devgateway.geoph.persistence;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.repository.*;
import org.devgateway.geoph.services.FilterService;
import org.devgateway.geoph.util.LocationAdmLevelEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ImplementingAgencyRepository impAgencyRepository;

    @Autowired
    FundingAgencyRepository fundingAgencyRepository;

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
    ClimateChangeRepository climateChangeRepository;

    @Autowired
    GenderResponsivenessRepository genderResponsivenessRepository;

    @Override
    public List<ImplementingAgency> findAllImpAgencies() {
        LOGGER.debug("Getting all implementing agencies");
        return impAgencyRepository.findAll();
    }

    @Override
    public List<FundingAgency> findAllFundingAgencies() {
        LOGGER.debug("Getting all implementing agencies");
        return fundingAgencyRepository.findAll();
    }

    @Override
    public List<Sector> findByLevel(int level) {
        LOGGER.debug("Getting all sectors");
        return sectorRepository.findByLevel(level);
    }

    @Override
    public List<Location> findAllLocations() {
        LOGGER.debug("Getting all locations");
        return locationRepository.findAll();
    }

    @Override
    public List<Location> findLocationsByLevel(LocationAdmLevelEnum level) {
        LOGGER.debug("Getting all locations of level: {}", level);
        List<Location> locationList = locationRepository.findLocationsByLevel(level.getLevel());
        expandLocationItems(locationList);
        return locationList;
    }

    private void expandLocationItems(List<Location> locationList) {
        for(Location loc : locationList){
            List<Location> items = loc.getItems();
            if(items!=null){
                expandLocationItems(items);
            }
        }
    }

    public List<Location> findLocationsByParentId(long parentId){
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
        for(FlowType flowType : flowTypes){
            Map<String, Object> flowTypesMap = new HashMap<>();
            flowTypesMap.put("id", String.valueOf(flowType.getId()));
            flowTypesMap.put("name", flowType.getName());
            if(flowType.getName().toLowerCase().equals("grant")){
                List<Classification> classifications = classificationRepository.findAll();
                List<Map<String, Object>> classificationList = new ArrayList<>();
                for(Classification classification:classifications){
                    Map<String, Object> classificationMap = new HashMap<>();
                    classificationMap.put("id", String.valueOf(flowType.getId()) +"."+ String.valueOf(classification.getId()));
                    classificationMap.put("name", classification.getName());
                    classificationList.add(classificationMap);
                }
                flowTypesMap.put("items", classificationList);
            }
            retList.add(flowTypesMap);
        }
        return retList;
    }

    @Override
    public List<ClimateChange> findAllClimateChanges() {
        return climateChangeRepository.findAll();
    }

    @Override
    public List<GenderResponsiveness> findAllGenderResponsiveness() {
        return genderResponsivenessRepository.findAll();
    }

}
