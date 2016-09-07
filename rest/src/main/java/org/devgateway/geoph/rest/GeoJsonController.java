package org.devgateway.geoph.rest;

import org.devgateway.geoph.core.request.AppRequestParams;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.services.GeoJsonService;
import org.devgateway.geoph.enums.GeometryDetail;
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
public class GeoJsonController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeoJsonController.class);

    private final GeoJsonService geoJsonService;


    @Autowired
    public GeoJsonController(GeoJsonService geoJsonService) {
        this.geoJsonService = geoJsonService;


    }

    @RequestMapping(value = "/{level}/projects", method = GET)
    public FeatureCollection getProjects(@PathVariable final String level,AppRequestParams filters) {
        LOGGER.debug("getGeoJsonByLocationType");
        Parameters params = filters.getParameters();
        params.setLocationLevel(level);
        // REGION(1), PROVINCE(2), MUNICIPALITY(3);
        return geoJsonService.getProjectPoints(LocationAdmLevelEnum.valueOf(level.toUpperCase()), params);
    }

    @RequestMapping(value = "/{level}/funding", method = GET)
    public FeatureCollection getFunding(@PathVariable final String level, AppRequestParams filters) {
        LOGGER.debug("getGeoJsonForShapes");
        Parameters parameters = filters.getParameters();
        parameters.setLocationLevel(level);
        // REGION(1), PROVINCE(2), MUNICIPALITY(3);
        return geoJsonService.getFundingShapes(LocationAdmLevelEnum.valueOf(level.toUpperCase()), GeometryDetail.MEDIUM, parameters);
    }

    @RequestMapping(value = "/{level}/funding/detail/{detail:.+}", method = GET)
    public FeatureCollection getFundingWithSimplification(
            @PathVariable final String level,
            @PathVariable final String detail,
            AppRequestParams filters) {
        // LOW(0.05), MEDIUM(0.025), HIGH(0.01), ULTRA(0.005);
        // REGION(1), PROVINCE(2), MUNICIPALITY(3);
        LOGGER.debug("getGeoJsonForShapes with detail from param");
        Parameters params = filters.getParameters();
        params.setLocationLevel(level.toUpperCase());
        return geoJsonService.getFundingShapes(LocationAdmLevelEnum.valueOf(level.toUpperCase()), GeometryDetail.valueOf(detail.toUpperCase()), params);
    }

    @RequestMapping(value = "/indicators/{indicatorId}", method = GET)
    public FeatureCollection getIndicatorsData(@PathVariable final long indicatorId) {
        LOGGER.debug("getIndicatorsData for indicator id:" + indicatorId);
        return geoJsonService.getIndicatorShapes(indicatorId, GeometryDetail.MEDIUM);
    }

    @RequestMapping(value = "/indicators/{indicatorId}/detail/{detail:.+}", method = GET)
    public FeatureCollection getIndicatorsWithDetail(@PathVariable final long indicatorId,   @PathVariable final String detail) {
        LOGGER.debug("getIndicatorsData for indicator id:" + indicatorId);
        return geoJsonService.getIndicatorShapes(indicatorId,GeometryDetail.valueOf(detail.toUpperCase()));
    }


    @RequestMapping(value = "/geophotos", method = GET)
    public FeatureCollection getGeoPhotosData(AppRequestParams filters) {
        LOGGER.debug("getGeoPhotosData");
        return geoJsonService.getPhotoPoints(filters.getParameters());
    }


    @RequestMapping(value = "/{level}/physicalProgress", method = GET)
    public FeatureCollection getGeoJsonByPhysicalProgress(@PathVariable final String level,AppRequestParams filters) {
        LOGGER.debug("getGeoJsonByPhysicalProgress");
        Parameters params = filters.getParameters();
        params.setLocationLevel(level);
        return geoJsonService.getProjectShapes(LocationAdmLevelEnum.valueOf(level.toUpperCase()), GeometryDetail.MEDIUM, params);
    }

    @RequestMapping(value = "/{level}/physicalProgress/detail/{detail:.+}", method = GET)
    public FeatureCollection getGeoJsonByPhysicalProgressDetailed(@PathVariable final String level,@PathVariable final String detail,AppRequestParams filters) {
        LOGGER.debug("getGeoJsonByPhysicalProgressDetailed");
        Parameters params = filters.getParameters();
        params.setLocationLevel(level);
        return geoJsonService.getProjectShapes(LocationAdmLevelEnum.valueOf(level.toUpperCase()), GeometryDetail.valueOf(detail.toUpperCase()), params);
    }

}
