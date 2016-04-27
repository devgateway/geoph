package org.devgateway.geoph.services;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.util.LocationAdmLevelEnum;


import java.util.List;
import java.util.Map;


/**
 * @author dbianco
 *         created on feb 29 2016.
 */
public interface FilterService {

    List<ImplementingAgency> findAllImpAgencies();

    List<FundingAgency> findAllFundingAgencies();

    List<Sector> findByLevel(int level);

    List<Location> findAllLocations();

    List<Location> findLocationsByLevel(LocationAdmLevelEnum level);

    List<Location> findLocationsByParentId(long parentId);

    List<Status> findAllStatuses();

    List<PhysicalStatus> findAllPhysicalStatus();

    List<Map<String,Object>> findAllFlowTypes();

    List<ClimateChange> findAllClimateChanges();

    List<GenderResponsiveness> findAllGenderResponsiveness();
}
