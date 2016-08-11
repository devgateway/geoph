package org.devgateway.geoph.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.devgateway.geoph.core.request.JsonRequestParams;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.request.PrintParams;
import org.devgateway.geoph.core.response.ChartResponse;
import org.devgateway.geoph.core.services.*;
import org.devgateway.geoph.core.util.MD5Generator;
import org.devgateway.geoph.enums.AppMapTypeEnum;
import org.devgateway.geoph.model.AppMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by sebas on 8/3/2016.
 */

@RestController
@RequestMapping(value = "/export")
public class MapExport {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapExport.class);

    @Autowired
    ScreenCaptureService screenCaptureService;

    @Autowired
    AppMapService appMapService;

    @Autowired
    PrintService printService;

    @Autowired
    FileService fileService;

    @Autowired
    ChartService chartService;


    @RequestMapping(value = "/pdf", produces = "application/json")
    public Map<String, String> toPdf(@RequestBody PrintParams params, HttpServletResponse response) throws Exception {
        LOGGER.debug("shareMap");
        fillParams(params);

        AppMap map = saveMap(params);

        String name = screenCaptureService.createPdfFromHtmlString(params, map.getKey());
        Map<String, String> values = ImmutableMap.of("file", name);
        return values;
    }

    @RequestMapping(value = "/download/{name:.+}   ",method = GET)
    public void download(HttpServletResponse response, @PathVariable String name) throws Exception {
        File file = fileService.getFile(name);
        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);
        /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        response.setContentLength((int) file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        //Copy bytes from source to destination(outputstream in this example), closes both streams.
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

    private AppMap saveMap(PrintParams params) throws JsonProcessingException {
        String mapJson = new ObjectMapper().writeValueAsString(params.getData());
        String md5 = MD5Generator.getMD5(mapJson);
        AppMap map = appMapService.findByMD5(md5);
        if(map==null){
            map = appMapService.save(new AppMap(params.getName(),
                    params.getDescription(),
                    mapJson,
                    UUID.randomUUID().toString(),
                    md5,
                    AppMapTypeEnum.PRINT.getName(),null));
        }
        return map;
    }

    private void fillParams(PrintParams params) throws java.io.IOException {
        Map filterMap = (Map) ((Map) params.getData()).get("filters");
        if (filterMap != null) {
            params.setFilters(printService.getFilterNamesFromJson(filterMap));
        }
        List jsonLayers = (List) ((Map) ((Map) params.getData()).get("map")).get("visibleLayers");
        if (jsonLayers != null) {
            params.setLayers(printService.getLayerNamesFromJson(jsonLayers));
        }
        JsonRequestParams jsonFilters = new ObjectMapper().readValue(new ObjectMapper().writeValueAsString(filterMap), JsonRequestParams.class);
        Parameters chartParams = Parameters.getParameters(jsonFilters);

        Map<String, Collection<ChartResponse>> chartData = new HashMap<>();
        chartData.put("Funding Agency", chartService.getFundingByFundingAgency(chartParams));
        chartData.put("Implementing Agency", chartService.getFundingByImplementingAgency(chartParams));
        chartData.put("Sector", chartService.getFundingBySector(chartParams));
        chartData.put("Physical Status", chartService.getFundingByPhysicalStatus(chartParams));
        params.setAllChartsData(chartData);
    }

}
