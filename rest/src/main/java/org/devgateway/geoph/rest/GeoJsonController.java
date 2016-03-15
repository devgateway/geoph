package org.devgateway.geoph.rest;

import org.apache.commons.lang3.StringUtils;
import org.devgateway.geoph.services.GeoJsonService;
import org.devgateway.geoph.util.LocationAdmLevel;
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
            @RequestParam(value = FILTER_SECTOR, required = false) String st){
        LOGGER.debug("getGeoJsonByLocationType");
        Map<String, String[]> params = new HashMap<>();
        int admLevel = LocationAdmLevel.valueOf(level.toUpperCase()).getLevel();
        params.put(PROPERTY_LOC_TYPE, new String[]{""+admLevel});
        if(StringUtils.isNotBlank(st)){
            params.put(FILTER_SECTOR, st.split(PARAM_SEPARATOR));
        }
        return service.getLocationsByParams(params);
    }

}
