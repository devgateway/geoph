package org.devgateway.geoph.rest;

import org.devgateway.geoph.core.services.LayerService;
import org.devgateway.geoph.model.GeoPhotoSource;
import org.devgateway.geoph.security.NotAllowException;
import org.geojson.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author dbianco
 *         created on jun 09 2016.
 */
@RestController
@RequestMapping(value = "/geophotos")
public class PhotoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndicatorController.class);

    private final LayerService layerService;

    @Autowired
    public PhotoController(LayerService layerService) {
        this.layerService = layerService;
    }

    @RequestMapping(value = "/list", method = GET)
    public List<GeoPhotoSource> getGeoPhotosList() {
        LOGGER.debug("getGeoPhotosList");
        return layerService.getGeoPhotoSourceList();
    }

    @ExceptionHandler(NotAllowException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Map<String, Object> handleNotAllowException(NotAllowException nae) {
        Map<String, Object> result = new HashMap<>();
        result.put("error", "Not Allowed");
        result.put("message", nae.getMessage());
        result.put("status", 401);
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }
}
