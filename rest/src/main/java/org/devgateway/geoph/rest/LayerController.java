package org.devgateway.geoph.rest;

import org.devgateway.geoph.model.Indicator;
import org.devgateway.geoph.model.IndicatorDetail;
import org.devgateway.geoph.security.NotAllowException;
import org.devgateway.geoph.services.LayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author dbianco
 *         created on abr 04 2016.
 */
@RestController
@RequestMapping(value = "/layers")
public class LayerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LayerController.class);

    @Autowired
    LayerService service;

    @RequestMapping(value = "/secure", method = GET)
    @Secured("ROLE_READ")
    public String secureHelloWorld() {
        LOGGER.debug("secureHelloWorld");
        return "Secured Hello World";
    }

    @RequestMapping(value = "/notSecure", method = GET)
    public String notSecureHelloWorld() {
        LOGGER.debug("notSecureHelloWorld");
        return "Hello World";
    }

    @RequestMapping(value = "/list", method = GET)
    //@Secured("ROLE_READ")
    public List<Indicator> getLayerList() {
        LOGGER.debug("getLayerList");
        return service.getLayerList();
    }

    @RequestMapping(value = "/data/id/{indicatorId}", method = GET)
    //@Secured("ROLE_READ")
    public List<IndicatorDetail> getLayerData(@PathVariable final long indicatorId) {
        LOGGER.debug("getLayerData for indicator id:" + indicatorId);
        return service.getLayerData(indicatorId);
    }

    @ExceptionHandler(NotAllowException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Map<String,Object> handleNotAllowException(NotAllowException nae) {
        Map<String, Object> result = new HashMap<>();
        result.put("error", "Not Allowed");
        result.put("message", nae.getMessage());
        result.put("status", 401);
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }
}
