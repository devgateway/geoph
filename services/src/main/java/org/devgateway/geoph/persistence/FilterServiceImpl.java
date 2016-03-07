package org.devgateway.geoph.persistence;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.repository.*;
import org.devgateway.geoph.services.FilterService;
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
    FlowTypeRepository flowTypeRepository;

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    LocationRepository locationRepository;

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
    public List<FlowType> findAllFlowTypes() {
        LOGGER.debug("Getting all funding sources");
        return flowTypeRepository.findAll();
    }

    @Override
    public List<Sector> findAllSectors() {
        LOGGER.debug("Getting all sectors");
        return sectorRepository.findAll();
    }

    @Override
    public List<Location> findLocationsByType(int type) {
        LOGGER.debug("Getting all locations of type: {}", type);
        return locationRepository.findLocationsByType(type);
    }

    public List<Location> findLocationsByParentId(long parentId){
        LOGGER.debug("Getting all children locations for parentId {}", parentId);
        return locationRepository.findLocationsByParentId(parentId);
    }
}
