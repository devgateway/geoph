package org.devgateway.geoph.rest;

import org.devgateway.geoph.services.GeoJsonService;
import org.devgateway.geoph.util.GeometryDetailLevel;
import org.devgateway.geoph.util.LocationAdmLevel;
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
            @RequestParam(value = FILTER_DATE_START, required = false) String startDate,
            @RequestParam(value = FILTER_DATE_END, required = false) String endDate,
            @RequestParam(value = FILTER_SECTOR, required = false) String sectors,
            @RequestParam(value = FILTER_STATUS, required = false) String statuses,
            @RequestParam(value = FILTER_LOCATION, required = false) String locations,
            @RequestParam(value = FILTER_PROJECT, required = false) String projects,
            @RequestParam(value = FILTER_IMPLEMENTING_AGENCY, required = false) String impAgencies,
            @RequestParam(value = FILTER_FUNDING_AGENCY, required = false) String fundingAgencies,
            @RequestParam(value = FILTER_FLOW_TYPE, required = false) String flowTypes){
        LOGGER.debug("getGeoJsonByLocationType");
        Parameters params = new Parameters(startDate, endDate, sectors, statuses,
                locations, projects, impAgencies, fundingAgencies, flowTypes, null);
        params.setLocationLevel(level);
        return service.getLocationsByParams(params);
    }

    @RequestMapping(value = "/stats/{level}/funding", method = GET)
    public FeatureCollection getGeoJsonStatistical(
            @PathVariable final String level){
        LOGGER.debug("getGeoJsonForShapes");
        return service.getShapesByLevelAndDetail(LocationAdmLevel.valueOf(level.toUpperCase()),
                GeometryDetailLevel.MEDIUM);
    }

    @RequestMapping(value = "/stats/{level}/funding/detail/{detail}", method = GET)
    public FeatureCollection getGeoJsonStatisticalDetailed(
            @PathVariable final String level,
            @PathVariable final String detail){
        LOGGER.debug("getGeoJsonForShapes2");
        return service.getShapesByLevelAndDetail(LocationAdmLevel.valueOf(level.toUpperCase()),
                GeometryDetailLevel.valueOf(detail.toUpperCase()));
    }

}
