package org.devgateway.geoph.services;

import org.devgateway.geoph.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * @author dbianco
 *         created on feb 29 2016.
 */
public interface FilterService {

    List<ImplementingAgency> findAllImpAgencies();

    List<FundingAgency> findAllFundingAgencies();

    List<FlowType> findAllFlowTypes();

    List<Sector> findByLevel(int level);

    List<Location> findAllLocations();

    List<Location> findLocationsByLevel(int level);

    List<Location> findLocationsByParentId(long parentId);

    List<Status> findAllStatuses();
}
