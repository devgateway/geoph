package org.devgateway.geoph.persistence;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.repository.*;
import org.devgateway.geoph.services.FilterService;
import org.devgateway.geoph.util.FlowType;
import org.devgateway.geoph.util.LocationAdmLevel;
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
    public List<Location> findLocationsByLevel(LocationAdmLevel level) {
        LOGGER.debug("Getting all locations of level: {}", level);
        return locationRepository.findLocationsByLevel(level.getLevel());
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
}
