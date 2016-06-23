package org.devgateway.geoph.rest;


import org.devgateway.geoph.core.services.AppMapService;
import org.devgateway.geoph.core.services.ScreenCaptureService;
import org.devgateway.geoph.model.AppMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author dbianco
 *         created on abr 20 2016.
 */
@RestController
@RequestMapping(value = "/maps")
public class MapController {


    private static final Logger LOGGER = LoggerFactory.getLogger(MapController.class);

    private final AppMapService appMapService;


    private final ScreenCaptureService screenCaptureService;

    @Autowired
    public MapController(AppMapService appMapService, ScreenCaptureService screenCaptureService) {
        this.appMapService = appMapService;
        this.screenCaptureService = screenCaptureService;
    }

    @RequestMapping(method = GET)
    public Page<AppMap> findMaps(@PageableDefault(page = 0, size = 20, sort = "id") final Pageable pageable) {
        LOGGER.debug("findMaps");
        return appMapService.findAll(pageable);
    }

    @RequestMapping(value = "/save", method = POST)
    public AppMap saveMap(@RequestParam(value = "name", required = true) String name,
                          @RequestParam(value = "description", required = true) String description,
                          @RequestBody Object mapVariables) {
        LOGGER.debug("saveMap");
        AppMap appMap = new AppMap(name, description, mapVariables.toString());
        return appMapService.save(appMap);
    }

    @RequestMapping(value = "/id/{id}", method = GET)
    public AppMap findMapById(@PathVariable final long id) {
        LOGGER.debug("findMapById");
        return appMapService.findById(id);
    }

    @RequestMapping(value = "/key/{key}", method = GET)
    public AppMap findMapByKey(@PathVariable final String key) {
        LOGGER.debug("findMapByKey");
        return appMapService.findByKey(key);
    }


    @RequestMapping(value = "/search/{name}", method = GET)
    public List<AppMap> findMapByName(@PathVariable final String name) {
        LOGGER.debug("findMapByKey");
        return appMapService.findByNameOrDescription(name);
    }

    @RequestMapping(value = "/print", method = GET)
    public String printPage(@RequestParam(value = "url", required = true) String url) throws Exception {
        return screenCaptureService.captureUrlToImage(url);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleAppException(Exception ex) {
        LOGGER.error("Can't complete this request", ex);
        return ex.getMessage();
    }

}