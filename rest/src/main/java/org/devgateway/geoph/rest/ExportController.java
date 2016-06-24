package org.devgateway.geoph.rest;

import org.devgateway.geoph.core.export.DefinitionsProvider;
import org.devgateway.geoph.core.export.Generator;
import org.devgateway.geoph.core.request.AppRequestParams;
import org.devgateway.geoph.core.services.ExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Sebastian Dimunzio on 6/9/2016.
 */
@RestController
@RequestMapping(value = "/export")
public class ExportController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExportController.class);

    @Autowired
    ExportService exportService;

    @Autowired
    @Qualifier("xlsGenerator")
    Generator xlsGenerator;

    @Autowired
    @Qualifier("csvGenerator")
    Generator csvGenerator;

    @Autowired
    @Qualifier("locationProjectDefinitions")
    DefinitionsProvider locationProjectDefProvider;

    @Autowired
    @Qualifier("indicatorDefinitions")
    DefinitionsProvider indicatorDefProvider;


    @RequestMapping(value = "/data/xls", method = GET)
    public String getDataXLS(AppRequestParams filters) throws Exception {
        LOGGER.debug("XLS export called");
        return exportService.exportLocationProject(locationProjectDefProvider, xlsGenerator.getNewInstance(), filters.getParameters());

    }

    @RequestMapping(value = "/data/csv", method = GET)
    public String getDataCSV(AppRequestParams filters) throws Exception {
        LOGGER.debug("CSV export Called");
        return exportService.exportLocationProject(locationProjectDefProvider, csvGenerator.getNewInstance(), filters.getParameters());
    }

    @RequestMapping(value = "/indicator/{id}", method = GET)
    public String getCSV(@PathVariable final Long id) throws Exception {
        LOGGER.debug("CSV indicators export Called");
        return exportService.exportIndicator(indicatorDefProvider, csvGenerator.getNewInstance(), id);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleAppException(Exception ex) {
        LOGGER.error("Can't complete this request", ex);
        return ex.getMessage();
    }

}
