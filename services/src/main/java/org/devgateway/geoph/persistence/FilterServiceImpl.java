package org.devgateway.geoph.persistence;

import org.devgateway.geoph.model.FlowType;
import org.devgateway.geoph.model.FundingAgency;
import org.devgateway.geoph.model.ImplementingAgency;
import org.devgateway.geoph.model.Sector;
import org.devgateway.geoph.persistence.repository.FlowTypeRepository;
import org.devgateway.geoph.persistence.repository.FundingAgencyRepository;
import org.devgateway.geoph.persistence.repository.ImplementingAgencyRepository;
import org.devgateway.geoph.persistence.repository.SectorRepository;
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
}
