package org.devgateway.geoph.rest;

import org.devgateway.geoph.core.request.AppRequestParams;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.services.GeoJsonService;
import org.devgateway.geoph.enums.GeometryDetailLevelEnum;
import org.devgateway.geoph.enums.LocationAdmLevelEnum;
import org.geojson.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            AppRequestParams filters) {
        LOGGER.debug("getGeoJsonByLocationType");
        Parameters params = new Parameters(filters);
        params.setLocationLevel(level);
        return service.getLocationsByParams(params);
    }

    @RequestMapping(value = "/stats/{level}/funding", method = GET)
    public FeatureCollection getGeoJsonStatistical(
            @PathVariable final String level, AppRequestParams filters) {
        LOGGER.debug("getGeoJsonForShapes");
        Parameters params = new Parameters(filters);
        return service.getShapesByLevelAndDetail(LocationAdmLevelEnum.valueOf(level.toUpperCase()),
                GeometryDetailLevelEnum.MEDIUM.getLevel(), params);
    }

    @RequestMapping(value = "/stats/{level}/funding/detail/{detail:.+}", method = GET)
    public FeatureCollection getGeoJsonStatisticalDetailed(
            @PathVariable final String level,
            @PathVariable final double detail,
            AppRequestParams filters) {
        LOGGER.debug("getGeoJsonForShapes with detail from param");
        Parameters params = new Parameters(filters);
        return service.getShapesByLevelAndDetail(LocationAdmLevelEnum.valueOf(level.toUpperCase()), detail, params);
    }

}
