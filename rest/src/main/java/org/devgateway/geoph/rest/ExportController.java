package org.devgateway.geoph.rest;

import org.devgateway.geoph.core.export.DefinitionsProvider;
import org.devgateway.geoph.core.export.Generator;
import org.devgateway.geoph.core.export.Stylists;
import org.devgateway.geoph.core.request.AppRequestParams;
import org.devgateway.geoph.core.services.ExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Sebastian Dimunzio on 6/9/2016.
 */
@RestController
@RequestMapping(value = "/export")
public class ExportController  extends BaseController {
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
    @Qualifier("indicatorTemplateDefinitions")
    DefinitionsProvider indicatorTemplateDefProvider;

    @Autowired
    @Qualifier("indicatorDefinitions")
    DefinitionsProvider indicatorDefProvider;

    @Autowired
    Stylists stylists;

    @RequestMapping(value = "/data/xls", method = GET)
    public void getDataXLS(HttpServletResponse response, AppRequestParams filters) throws Exception {
        LOGGER.debug("XLS export called");
        downloadFileHelper(
                response,
                exportService.exportLocationProject(locationProjectDefProvider.getNewInstance(stylists), xlsGenerator.getNewInstance(), filters.getParameters()));

    }

    @RequestMapping(value = "/data/csv", method = GET)
    public void getDataCSV(HttpServletResponse response, AppRequestParams filters) throws Exception {
        LOGGER.debug("CSV export Called");
        downloadFileHelper(
                response,
                exportService.exportLocationProject(locationProjectDefProvider.getNewInstance(stylists), csvGenerator.getNewInstance(), filters.getParameters()));
    }

    @RequestMapping(value = "/indicators/{id}", method = GET)
    public void getIndicatorDefault(HttpServletResponse response, @PathVariable final Long id) throws Exception {
        LOGGER.debug("CSV indicators export Called");
        getIndicatorCSV(response, id);
    }

    @RequestMapping(value = "/indicators/{id}/csv", method = GET)
    public void getIndicatorCSV(HttpServletResponse response, @PathVariable final Long id) throws Exception {
        LOGGER.debug("CSV indicators export Called");
        downloadFileHelper(
                response,
                exportService.exportIndicator(indicatorDefProvider.getNewInstance(stylists), csvGenerator.getNewInstance(), id));

    }

    @RequestMapping(value = "/indicators/{id}/xls", method = GET)
    public void getIndicatorXLS(HttpServletResponse response, @PathVariable final Long id) throws Exception {
        LOGGER.debug("XLS indicators export");
        downloadFileHelper(
                response,
                exportService.exportIndicator(indicatorDefProvider.getNewInstance(stylists), xlsGenerator.getNewInstance(), id));
    }

    @RequestMapping(value = "/template/{level}", method = GET)
    public void getIndicatorTemplate(HttpServletResponse response, @PathVariable final String level) throws Exception {
        LOGGER.debug("getIndicatorTemplate: " + level);
        downloadFileHelper(
                response,
                exportService.exportIndicatorTemplate(indicatorTemplateDefProvider.getNewInstance(stylists), xlsGenerator.getNewInstance(), level));
    }

    private void downloadFileHelper(HttpServletResponse response, File file) throws Exception {

        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        response.setContentLength((int) file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

}
