package org.devgateway.geoph.rest;

import org.apache.commons.lang3.StringUtils;
import org.devgateway.geoph.services.GeoJsonService;
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

import java.util.HashMap;
import java.util.Map;

import static org.devgateway.geoph.util.Constants.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author dbianco
 *         created on mar 11 2016.
 */
@RestController
@RequestMapping(value = "/geodata")
public class GeoJsonController {

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
            @RequestParam(value = FILTER_PROJECT, required = false) String projects){
        LOGGER.debug("getGeoJsonByLocationType");
        Parameters params = new Parameters(startDate, endDate, sectors, statuses, locations, projects, null);
        params.setLocationLevel(level);
        return service.getLocationsByParams(params);
    }

}
