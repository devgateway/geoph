package org.devgateway.geoph.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.devgateway.geoph.core.request.JsonRequestParams;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.request.PrintData;
import org.devgateway.geoph.core.request.PrintParams;
import org.devgateway.geoph.core.response.ChartResponse;
import org.devgateway.geoph.core.services.AppMapService;
import org.devgateway.geoph.core.services.ChartService;
import org.devgateway.geoph.core.services.FileService;
import org.devgateway.geoph.core.services.PrintService;
import org.devgateway.geoph.core.services.ProjectService;
import org.devgateway.geoph.core.services.ScreenCaptureService;
import org.devgateway.geoph.core.util.MD5Generator;
import org.devgateway.geoph.enums.AppMapTypeEnum;
import org.devgateway.geoph.enums.TransactionStatusEnum;
import org.devgateway.geoph.enums.TransactionTypeEnum;
import org.devgateway.geoph.model.AppMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by sebas on 8/3/2016.
 */

@RestController
@RequestMapping(value = "/export")
@CrossOrigin
@CacheConfig(keyGenerator = "genericFilterKeyGenerator", cacheNames = "mapExportControllerCache")
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

    @Autowired
    ProjectService projectService;


    @RequestMapping(value = "/pdf", produces = "application/json")
    @Cacheable
    public Map<String, String> toPdf(@RequestBody PrintParams paramsFromUI) throws Exception {
        LOGGER.debug("get Pdf map");
        AppMap map = saveMap(paramsFromUI);

        List dataList = paramsFromUI.getData();
        List<PrintData> printDataList = new ArrayList<>();
        dataList.forEach(data -> {
            printDataList.add(getPrintData((Map) data));
        });

        String name = screenCaptureService.createPdfFromHtmlString(paramsFromUI, printDataList, map.getKey());

        return ImmutableMap.of("file", name);
    }

    @RequestMapping(value = "/download/{name:.+}   ", method = GET)
    public void download(HttpServletResponse response, @PathVariable String name) throws Exception {
        File file = fileService.getFile(name);
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
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
        if (map == null) {
            map = appMapService.save(new AppMap(params.getName(),
                    params.getDescription(),
                    mapJson,
                    UUID.randomUUID().toString(),
                    md5,
                    AppMapTypeEnum.PRINT.getName(), null));
        }
        return map;
    }

    private PrintData getPrintData(Map dataMap) {
        PrintData printData = new PrintData();
        Map filterMap = (Map) dataMap.get("filters");
        if (filterMap != null) {
            printData.setFilters(printService.getFilterNamesFromJson(filterMap));
        }
        List jsonLayers = (List) dataMap.get("visibleLayers");
        if (jsonLayers != null && jsonLayers.size() > 0) {
            printData.setVisibleLayers(printService.getLayerNamesFromJson(jsonLayers));
        }
        Map settings = (Map) dataMap.get("settings");
        if (settings != null && settings.size() > 0) {
            Map fundingVars = (Map) settings.get("fundingType");
            if (fundingVars != null) {
                printData.setTrxType(fundingVars.get("measure").toString());
                printData.setTrxStatus(fundingVars.get("type").toString());
            }
        }
        JsonRequestParams jsonFilters = null;
        try {
            jsonFilters = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(new ObjectMapper().writeValueAsString(filterMap), JsonRequestParams.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parameters chartParams = Parameters.getParameters(jsonFilters);
        if (StringUtils.isNotBlank(printData.getTrxType())) {
            chartParams.setTrxType(TransactionTypeEnum.valueOf(printData.getTrxType().toUpperCase()).getId());
            chartParams.setTrxTypeSort(TransactionTypeEnum.valueOf(printData.getTrxType().toUpperCase()).getId());
        }
        if (StringUtils.isNotBlank(printData.getTrxStatus())) {
            chartParams.setTrxStatus(TransactionStatusEnum.valueOf(printData.getTrxStatus().toUpperCase()).getId());
            chartParams.setTrxStatusSort(TransactionStatusEnum.valueOf(printData.getTrxStatus().toUpperCase()).getId());
        }

        Map<String, Collection<ChartResponse>> chartData = new HashMap<>();
        chartData.put("Financing Institution", chartService.getFundingByFundingAgency(chartParams));
        chartData.put("Implementing Agency", chartService.getFundingByImplementingAgency(chartParams));
        chartData.put("Sector", chartService.getFundingBySector(chartParams));
        chartData.put("Physical Status", chartService.getFundingByPhysicalStatus(chartParams));
        printData.setAllChartsData(chartData);

        printData.setStats(projectService.getStats(chartParams));
        return printData;
    }

}
