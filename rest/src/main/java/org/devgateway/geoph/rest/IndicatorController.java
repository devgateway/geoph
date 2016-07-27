package org.devgateway.geoph.rest;

import org.devgateway.geoph.core.request.IndicatorRequest;
import org.devgateway.geoph.core.response.IndicatorResponse;
import org.devgateway.geoph.core.services.ImportService;
import org.devgateway.geoph.core.services.LayerService;
import org.devgateway.geoph.model.Indicator;
import org.devgateway.geoph.model.security.SystemUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author dbianco
 *         created on abr 04 2016.
 */
@RestController
@RequestMapping(value = "/indicators")
public class IndicatorController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndicatorController.class);
    private final LayerService layerService;
    private final ImportService importService;

    @Autowired
    public IndicatorController(LayerService layerService, ImportService importService) {
        this.layerService = layerService;
        this.importService = importService;
    }

    @RequestMapping(method = GET)
    public List<Indicator> getIndicatorsList() {
        LOGGER.debug("getIndicatorsList");
        return layerService.getIndicatorsList();
    }

    @RequestMapping(value = "/{id}", method = GET)
    public IndicatorResponse getIndicator(@PathVariable final long id) {
        LOGGER.debug("getIndicator: " + id);
        return layerService.getIndicatorById(id);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    @Secured("ROLE_ADMIN")
    public void deleteIndicator(@PathVariable final long id) {
        LOGGER.debug("deleteIndicator: " + id);
        layerService.deleteIndicator(id);
    }

    @RequestMapping(value = "/upload", headers = "content-type=multipart/*", method = POST)
    @Secured("ROLE_ADMIN")
    public IndicatorResponse putIndicator(@AuthenticationPrincipal SystemUser activeUser, IndicatorRequest indicatorParam,
                                          @RequestParam(value = "file", required = false) final MultipartFile file) {

             LOGGER.debug("add indicator from file called by "+activeUser.getName());
        return importService.importIndicatorFromFile(indicatorParam, file);
    }

}
