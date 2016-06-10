package org.devgateway.geoph.rest;

import org.apache.http.HttpStatus;
import org.devgateway.geoph.core.request.AppRequestParams;
import org.devgateway.geoph.core.services.ExportService;
import org.devgateway.geoph.core.services.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.apache.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Sebastian Dimunzio on 6/9/2016.
 */
@RestController
@RequestMapping(value = "/export")
public class ExportController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExportController.class);

    @Autowired
    LocationService locationService;

    @Autowired
    ExportService exportService;

    public static final String COMMA = ",";

    @RequestMapping(value = "/{fileType}/{language}", method = GET)
    public String exportData(@PathVariable final String fileType,@PathVariable final String language,AppRequestParams filters) throws IOException {
            LOGGER.debug("exportData");
            exportService.export(filters.getParameters());
        return "";
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleAppException(Exception ex) {
        return ex.getMessage();
    }


}
