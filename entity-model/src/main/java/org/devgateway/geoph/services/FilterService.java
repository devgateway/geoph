package org.devgateway.geoph.services;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.util.FlowType;
import org.devgateway.geoph.util.LocationAdmLevel;


import java.util.List;


/**
 * @author dbianco
 *         created on feb 29 2016.
 */
public interface FilterService {

    List<ImplementingAgency> findAllImpAgencies();

    List<FundingAgency> findAllFundingAgencies();

    List<Sector> findByLevel(int level);

    List<Location> findAllLocations();

    List<Location> findLocationsByLevel(LocationAdmLevel level);

    List<Location> findLocationsByParentId(long parentId);

    List<Status> findAllStatuses();

    List<PhysicalStatus> findAllPhysicalStatus();
}
