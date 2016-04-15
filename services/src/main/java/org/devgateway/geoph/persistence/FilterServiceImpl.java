package org.devgateway.geoph.persistence;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.repository.*;
import org.devgateway.geoph.services.FilterService;
import org.devgateway.geoph.util.LocationAdmLevelEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
