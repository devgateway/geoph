package org.devgateway.geoph.rest;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.response.GenericResponse;
import org.devgateway.geoph.services.FilterService;
import org.devgateway.geoph.util.LocationAdmLevelEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.devgateway.geoph.util.Constants.*;


/**
 * @author dbianco
 *         created on feb 29 2016.
 */

@RestController
@RequestMapping(value = "/filters")
public class FilterController extends CrossOriginSupport {

    private final FilterService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterController.class);

    @Autowired
    public FilterController(FilterService service) {
        this.service = service;
    }

    @RequestMapping(value = "/climateChange", method = GET)
    public GenericResponse findAllClimateChanges() {
        LOGGER.debug("findAllClimateChanges");
        List<ClimateChange> climateChanges = service.findAllClimateChanges();
        Collections.sort(climateChanges);
        GenericResponse resp = new GenericResponse(
                "Climate Change",
                FILTER_CLIMATE_CHANGE,
                "CLIMATE_CHANGE_SECTION",
                1,
                climateChanges,
                climateChanges!=null?climateChanges.size():0
        );

        return resp;
    }

    @RequestMapping(value = "/genderResponsiveness", method = GET)
    public GenericResponse findAllGenderResponsiveness() {
        LOGGER.debug("findAllGenderResponsiveness");
        List<GenderResponsiveness> genderResponsiveness = service.findAllGenderResponsiveness();
        GenericResponse resp = new GenericResponse(
                "Gender Responsiveness",
                FILTER_GENDER_RESPONSIVENESS,
                "GENDER_RESPONSIVENESS_SECTION",
                1,
                genderResponsiveness,
                genderResponsiveness!=null?genderResponsiveness.size():0
        );

        return resp;
    }

    @RequestMapping(value = "/financialAmountPeriod", method = GET)
    public GenericResponse findFinancialAmountPeriod() {
        LOGGER.debug("findFinancialAmountPeriod");
        List<Double> financialAmountPeriod = service.findFinancialAmountPeriod();
        GenericResponse resp = new GenericResponse(
                "Financial Amount Period",
                FILTER_FINANCIAL_AMOUNT_MAX + SLASH +FILTER_FINANCIAL_AMOUNT_MIN,
                "FINANCIAL_AMOUNT_SECTION",
                1,
                financialAmountPeriod,
                financialAmountPeriod!=null?financialAmountPeriod.size():0
        );

        return resp;
    }

    @RequestMapping(value = "/flowType", method = GET)
    public GenericResponse findAllFlowTypes() {
        LOGGER.debug("findAllFlowTypes");
        List<Map<String, Object>> flowTypes = service.findAllFlowTypes();
        GenericResponse resp = new GenericResponse(
                "Flow Types",
                FILTER_FLOW_TYPE,
                "FLOW_TYPE_SECTION",
                1,
                flowTypes,
                flowTypes!=null?flowTypes.size():0
        );

        return resp;
    }

    @RequestMapping(value = "/fundingAgency", method = GET)
    public GenericResponse findAllFundingAgencies() {
        LOGGER.debug("findAllFundingAgencies");
        List<FundingAgency> fundingAgencies = service.findAllFundingAgencies();
        Collections.sort(fundingAgencies);
        GenericResponse resp = new GenericResponse(
                "Funding Agencies",
                FILTER_FUNDING_AGENCY,
                "FUNDING_ORG_SECTION",
                1,
                fundingAgencies,
                fundingAgencies!=null?fundingAgencies.size():0
        );

        return resp;
    }
    
    @RequestMapping(value = "/impAgency", method = GET)
    public GenericResponse findAllImpAgencies() {
        LOGGER.debug("findAllImpAgencies");
        List<ImplementingAgency> impAgencies = service.findAllImpAgencies();
        Collections.sort(impAgencies);
        GenericResponse resp = new GenericResponse(
                "Implementing Agencies",
                FILTER_IMPLEMENTING_AGENCY,
                "IMPLEMENTING_AGENCY_SECTION",
                1,
                impAgencies,
                impAgencies!=null?impAgencies.size():0
        );

        return resp;
    }

    @RequestMapping(value = "/physicalStatus", method = GET)
    public GenericResponse findAllPhysicalStatus() {
        LOGGER.debug("findAllPhysicalStatus");
        List<PhysicalStatus> physicalStatuses = service.findAllPhysicalStatus();
        Collections.sort(physicalStatuses);
        GenericResponse resp = new GenericResponse(
                "Physical Statuses",
                FILTER_PHYSICAL_STATUS,
                "PHYSICAL_STATUS_SECTION",
                1,
                physicalStatuses,
                physicalStatuses!=null?physicalStatuses.size():0
        );

        return resp;
    }

    @RequestMapping(value = "/location", method = GET)
    public GenericResponse findAllLocations() {
        LOGGER.debug("findAllLocations");
        List<Location> locations = service.findLocationsByLevel(LocationAdmLevelEnum.REGION);
        GenericResponse resp = new GenericResponse(
                "Locations",
                FILTER_LOCATION,
                "LOCATIONS_SECTION",
                1,
                locations,
                locations!=null?locations.size():0
        );
        return resp;
    }

    @RequestMapping(value = "/location/{level}", method = GET)
    public GenericResponse findLocationsByLevel(@PathVariable final String level) {
        LOGGER.debug("findLocationsByLevel {}", level);
        List<Location> locations = service.findLocationsByLevel(
                LocationAdmLevelEnum.valueOf(level.toUpperCase())
        );
        GenericResponse resp = new GenericResponse(
                "Region Locations",
                FILTER_LOCATION,
                "LOCATIONS_SECTION",
                1,
                locations,
                locations!=null?locations.size():0
        );
        return resp;
    }

    @RequestMapping(value = "/location/parent/{parentId}", method = GET)
    public GenericResponse findLocationsByParentId(@PathVariable final long parentId) {
        LOGGER.debug("findLocationsByParentId {}" , parentId);
        List<Location> locations = service.findLocationsByParentId(parentId);
        GenericResponse resp = new GenericResponse(
                "Children locations of parentId " + parentId,
                FILTER_LOCATION,
                "LOCATIONS_SECTION",
                1,
                locations,
                locations!=null?locations.size():0
        );
        return resp;
    }

    @RequestMapping(value = "/sector", method = GET)
    public GenericResponse findAllSectors() {
        LOGGER.debug("findAllSectors");
        List<Sector> sectors = service.findAllSectorByLevel(1);
        sortSectors(sectors);
        GenericResponse resp = new GenericResponse(
                "Sectors",
                FILTER_SECTOR,
                "SECTORS_SECTION",
                1,
                sectors,
                sectors!=null?sectors.size():0
        );

        return resp;
    }

    private void sortSectors(List<Sector> sectors){
        for(Sector sector : sectors){
            sortSectors(sector.getItems());
        }
        Collections.sort(sectors);
    }

    @RequestMapping(value = "/status", method = GET)
    public GenericResponse findAllStatuses() {
        LOGGER.debug("findAllStatuses");
        List<Status> statuses = service.findAllStatuses();
        GenericResponse resp = new GenericResponse(
                "Statuses",
                FILTER_STATUS,
                "STATUSES_SECTION",
                1,
                statuses,
                statuses!=null?statuses.size():0
        );

        return resp;
    }

    @RequestMapping(value = "/impPeriod", method = GET)
    public GenericResponse findImpPeriod() {
        LOGGER.debug("findImpPeriod");
        List<Map<String, String>> maxDates = new ArrayList<>();
        Map<String, String> maxDatesMap = new HashMap<>();
        maxDatesMap.put("minDate", "2010-01-01");
        maxDatesMap.put("maxDate", "2020-12-31");
        maxDates.add(maxDatesMap);

        GenericResponse resp = new GenericResponse(
                "Implementation Period",
                FILTER_START_DATE + SLASH + FILTER_END_DATE,
                "IMP_PERIOD_SECTION",
                1,
                maxDates,
                maxDates!=null?maxDates.size():0
        );

        return resp;
    }

    @RequestMapping(value = "/grantPeriod", method = GET)
    public GenericResponse findGrantPeriod() {
        LOGGER.debug("findGrantPeriod");
        List<Map<String, String>> maxDates = new ArrayList<>();
        Map<String, String> maxDatesMap = new HashMap<>();
        maxDatesMap.put("minDate", "2010-01-01");
        maxDatesMap.put("maxDate", "2020-12-31");
        maxDates.add(maxDatesMap);

        GenericResponse resp = new GenericResponse(
                "Performance Period",
                FILTER_PERFORMANCE_START + SLASH + FILTER_PERFORMANCE_END,
                "GRANT_PERIOD_SECTION",
                1,
                maxDates,
                maxDates!=null?maxDates.size():0
        );

        return resp;
    }
}
