package org.devgateway.geoph.rest;

import org.devgateway.geoph.persistence.GeoJsonService;
import org.devgateway.geoph.util.GeometryDetailLevelEnum;
import org.devgateway.geoph.util.LocationAdmLevelEnum;
import org.devgateway.geoph.util.Parameters;
import org.geojson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import static org.devgateway.geoph.util.Constants.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author dbianco
 *         created on mar 11 2016.
 */
@RestController
@RequestMapping(value = "/geodata")
public class GeoJsonController extends CrossOriginSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeoJsonController.class);

    private final GeoJsonService service;

    @Autowired
    public GeoJsonController(GeoJsonService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{level}/projects", method = GET)
    public FeatureCollection getGeoJsonByLocationType(
            @PathVariable final String level,
            @RequestParam(value = FILTER_START_DATE_MAX, required = false) String startDateMax,
            @RequestParam(value = FILTER_START_DATE_MIN, required = false) String startDateMin,
            @RequestParam(value = FILTER_END_DATE_MAX, required = false) String endDateMax,
            @RequestParam(value = FILTER_END_DATE_MIN, required = false) String endDateMin,
            @RequestParam(value = FILTER_PERFORMANCE_START_MAX, required = false) String performanceStartMax,
            @RequestParam(value = FILTER_PERFORMANCE_START_MIN, required = false) String performanceStartMin,
            @RequestParam(value = FILTER_PERFORMANCE_END_MAX, required = false) String performanceEndMax,
            @RequestParam(value = FILTER_PERFORMANCE_END_MIN, required = false) String performanceEndMin,
            @RequestParam(value = FILTER_SECTOR, required = false) String sectors,
            @RequestParam(value = FILTER_STATUS, required = false) String statuses,
            @RequestParam(value = FILTER_LOCATION, required = false) String locations,
            @RequestParam(value = FILTER_PROJECT, required = false) String projects,
            @RequestParam(value = FILTER_IMPLEMENTING_AGENCY, required = false) String impAgencies,
            @RequestParam(value = FILTER_FUNDING_AGENCY, required = false) String fundingAgencies,
            @RequestParam(value = FILTER_FLOW_TYPE, required = false) String flowTypes,
            @RequestParam(value = FILTER_PROJECT_TITLE, required = false) String projectTitle,
            @RequestParam(value = FILTER_PHYSICAL_STATUS, required = false) String physicalStatuses,
            @RequestParam(value = FILTER_CLIMATE_CHANGE, required = false) String climateChange,
            @RequestParam(value = FILTER_GENDER_RESPONSIVENESS, required = false) String genderResponsiveness,
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MAX, required = false) Double financialAmountMax,
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MIN, required = false) Double financialAmountMin){
        LOGGER.debug("getGeoJsonByLocationType");
        Parameters params = new Parameters(startDateMax, startDateMin, endDateMax, endDateMin, performanceStartMax,
                performanceStartMin, performanceEndMax, performanceEndMin, sectors, statuses, locations,
                projects, impAgencies, fundingAgencies, flowTypes,
                projectTitle, physicalStatuses, climateChange, genderResponsiveness,
                financialAmountMin, financialAmountMax, null);
        params.setLocationLevel(level);
        return service.getLocationsByParams(params);
    }

    @RequestMapping(value = "/stats/{level}/funding", method = GET)
    public FeatureCollection getGeoJsonStatistical(
            @PathVariable final String level,
            @RequestParam(value = FILTER_START_DATE_MAX, required = false) String startDateMax,
            @RequestParam(value = FILTER_START_DATE_MIN, required = false) String startDateMin,
            @RequestParam(value = FILTER_END_DATE_MAX, required = false) String endDateMax,
            @RequestParam(value = FILTER_END_DATE_MIN, required = false) String endDateMin,
            @RequestParam(value = FILTER_PERFORMANCE_START_MAX, required = false) String performanceStartMax,
            @RequestParam(value = FILTER_PERFORMANCE_START_MIN, required = false) String performanceStartMin,
            @RequestParam(value = FILTER_PERFORMANCE_END_MAX, required = false) String performanceEndMax,
            @RequestParam(value = FILTER_PERFORMANCE_END_MIN, required = false) String performanceEndMin,
            @RequestParam(value = FILTER_SECTOR, required = false) String sectors,
            @RequestParam(value = FILTER_STATUS, required = false) String statuses,
            @RequestParam(value = FILTER_LOCATION, required = false) String locations,
            @RequestParam(value = FILTER_PROJECT, required = false) String projects,
            @RequestParam(value = FILTER_IMPLEMENTING_AGENCY, required = false) String impAgencies,
            @RequestParam(value = FILTER_FUNDING_AGENCY, required = false) String fundingAgencies,
            @RequestParam(value = FILTER_FLOW_TYPE, required = false) String flowTypes,
            @RequestParam(value = FILTER_PROJECT_TITLE, required = false) String projectTitle,
            @RequestParam(value = FILTER_PHYSICAL_STATUS, required = false) String physicalStatuses,
            @RequestParam(value = FILTER_CLIMATE_CHANGE, required = false) String climateChange,
            @RequestParam(value = FILTER_GENDER_RESPONSIVENESS, required = false) String genderResponsiveness,
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MAX, required = false) Double financialAmountMax,
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MIN, required = false) Double financialAmountMin){
        LOGGER.debug("getGeoJsonForShapes");
        Parameters params = new Parameters(startDateMax, startDateMin, endDateMax, endDateMin, performanceStartMax,
                performanceStartMin, performanceEndMax, performanceEndMin, sectors, statuses, locations,
                projects, impAgencies, fundingAgencies, flowTypes,
                projectTitle, physicalStatuses, climateChange, genderResponsiveness,
                financialAmountMin, financialAmountMax, null);
        return service.getShapesByLevelAndDetail(LocationAdmLevelEnum.valueOf(level.toUpperCase()),
                GeometryDetailLevelEnum.MEDIUM.getLevel(), params);
    }

    @RequestMapping(value = "/stats/{level}/funding/detail/{detail:.+}", method = GET)
    public FeatureCollection getGeoJsonStatisticalDetailed(
            @PathVariable final String level,
            @PathVariable final double detail,
            @RequestParam(value = FILTER_START_DATE_MAX, required = false) String startDateMax,
            @RequestParam(value = FILTER_START_DATE_MIN, required = false) String startDateMin,
            @RequestParam(value = FILTER_END_DATE_MAX, required = false) String endDateMax,
            @RequestParam(value = FILTER_END_DATE_MIN, required = false) String endDateMin,
            @RequestParam(value = FILTER_PERFORMANCE_START_MAX, required = false) String performanceStartMax,
            @RequestParam(value = FILTER_PERFORMANCE_START_MIN, required = false) String performanceStartMin,
            @RequestParam(value = FILTER_PERFORMANCE_END_MAX, required = false) String performanceEndMax,
            @RequestParam(value = FILTER_PERFORMANCE_END_MIN, required = false) String performanceEndMin,
            @RequestParam(value = FILTER_SECTOR, required = false) String sectors,
            @RequestParam(value = FILTER_STATUS, required = false) String statuses,
            @RequestParam(value = FILTER_LOCATION, required = false) String locations,
            @RequestParam(value = FILTER_PROJECT, required = false) String projects,
            @RequestParam(value = FILTER_IMPLEMENTING_AGENCY, required = false) String impAgencies,
            @RequestParam(value = FILTER_FUNDING_AGENCY, required = false) String fundingAgencies,
            @RequestParam(value = FILTER_FLOW_TYPE, required = false) String flowTypes,
            @RequestParam(value = FILTER_PROJECT_TITLE, required = false) String projectTitle,
            @RequestParam(value = FILTER_PHYSICAL_STATUS, required = false) String physicalStatuses,
            @RequestParam(value = FILTER_CLIMATE_CHANGE, required = false) String climateChange,
            @RequestParam(value = FILTER_GENDER_RESPONSIVENESS, required = false) String genderResponsiveness,
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MAX, required = false) Double financialAmountMax,
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MIN, required = false) Double financialAmountMin){
        LOGGER.debug("getGeoJsonForShapes2");
        Parameters params = new Parameters(startDateMax, startDateMin, endDateMax, endDateMin, performanceStartMax,
                performanceStartMin, performanceEndMax, performanceEndMin, sectors, statuses, locations,
                projects, impAgencies, fundingAgencies, flowTypes,
                projectTitle, physicalStatuses, climateChange, genderResponsiveness,
                financialAmountMin, financialAmountMax, null);
        return service.getShapesByLevelAndDetail(LocationAdmLevelEnum.valueOf(level.toUpperCase()), detail, params);
    }

}
