package org.devgateway.geoph.core.services;

import org.devgateway.geoph.enums.LocationAdmLevelEnum;
import org.devgateway.geoph.model.*;

import java.util.List;
import java.util.Map;


/**
 * @author dbianco
 *         created on feb 29 2016.
 */
public interface FilterService {

    List<ImplementingAgency> findAllImpAgencies();

    Integer countImpAgencies();

    List<FundingAgency> findAllFundingAgencies();

    List<ExecutingAgency> findAllExecutingAgencies();

    List<Sector> findAllSectors();

    List<Sector> findAllSectorByLevel(int level);

    List<Location> findAllLocations();

    Location findLocationByCode(String code);

    List<Location> findLocationsByLevel(LocationAdmLevelEnum level);

    List<Location> findLocationsByParentId(long parentId);

    List<Status> findAllStatuses();

    List<PhysicalStatus> findAllPhysicalStatus();

    List<Map<String,Object>> findAllFlowTypes();

    List<ClimateChange> findAllClimateChanges();

    List<GenderResponsiveness> findAllGenderResponsiveness();

    List<Classification> findAllClassifications();

    List<GrantSubType> findAllGrantSubTypes();

    List<Double> findFinancialAmountPeriod();

    List<String> findImpPeriodBoundaries();

    List<String> findGrantPeriodBoundaries();

    List<Float> findTargetReachedPeriodBoundaries();

    List<Currency> findAllCurrencies();

    Location findLocationById(Long locId);

    Integer countFundingAgencies();

    Integer countExecutingAgencies();

    Map findAllTrxFunding();
}
