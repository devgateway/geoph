package org.devgateway.geoph.persistence.services;

import org.devgateway.geoph.model.Agency;
import org.devgateway.geoph.persistence.repository.AgencyRepository;
import org.devgateway.geoph.services.FilterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    AgencyRepository agencyRepository;

    @Override
    public Page<Agency> findAllAgencies(Pageable pageable) {
        LOGGER.debug("Getting all agencies");
        return agencyRepository.findAll(pageable);
    }
}
