package org.devgateway.geoph.rest;

import org.devgateway.geoph.core.request.AppRequestParams;
import org.devgateway.geoph.core.services.ExportService;
import org.devgateway.geoph.core.services.LocationService;
import org.devgateway.geoph.services.exporter.generators.XLSGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/xls", method = GET)
    public String getXLS(AppRequestParams filters) throws Exception {
        LOGGER.debug("XLS export called");
        return exportService.export(new XLSGenerator(), filters.getParameters());

    }

    @RequestMapping(value = "/csv", method = GET)
    public String getCSV(AppRequestParams filters) throws Exception {
        LOGGER.debug("CSV export Called");
        // exportService.export(new CSVGenerator(),  filters.getParameters());
        return "";
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleAppException(Exception ex) {
        return ex.getMessage();
    }


}
