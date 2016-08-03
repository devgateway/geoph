package org.devgateway.geoph.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.devgateway.geoph.core.exceptions.BadRequestException;
import org.devgateway.geoph.core.services.AppMapService;
import org.devgateway.geoph.core.services.ScreenCaptureService;
import org.devgateway.geoph.core.util.MD5Generator;
import org.devgateway.geoph.model.AppMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    private static final String NAME_STR = "name";
    private static final String DESCRIPTION_STR = "description";
    private static final String DATA_TO_SAVE_STR = "data";
    private static final String BAD_REQUEST_NAME_INVALID = "The name used to save the map is not valid or it is already in use";
    private static final String URL_STR = "url";
    private static final String PDF_DESCRIPTION_MSG = "Map created automatically to generate a PDF file";
    private static final String IMG_DESCRIPTION_MSG = "Map created automatically to generate a IMG file";
    private static final String SHARED_MAP_DESC = "Shared map";

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
    public AppMap saveMap(@RequestBody Map<String, Object> mapVariables) throws JsonProcessingException, SQLException {
        LOGGER.debug("saveMap");
        String mapName = (String) mapVariables.get(NAME_STR);
        if(checkIfMapNameIsValid(mapName)) {
            String mapDesc = (String) mapVariables.get(DESCRIPTION_STR);
            String mapJson = new ObjectMapper().writeValueAsString(mapVariables.get(DATA_TO_SAVE_STR));
            AppMap appMap = new AppMap(mapName, mapDesc, mapJson, UUID.randomUUID().toString(), null);
            return appMapService.save(appMap);
        } else {
            throw new BadRequestException(BAD_REQUEST_NAME_INVALID);
        }
    }



    @RequestMapping(value = "/share", method = POST)
    public AppMap shareMap(@RequestBody Map<String, Object> mapVariables) throws JsonProcessingException, SQLException {
        LOGGER.debug("shareMap");
        String mapJson = new ObjectMapper().writeValueAsString(mapVariables.get(DATA_TO_SAVE_STR));
        String md5 = MD5Generator.getMD5(mapJson);
        AppMap map = appMapService.findByMD5(md5);
        if(map==null){
            String mapName = UUID.randomUUID().toString();
            String mapDesc = SHARED_MAP_DESC;
            AppMap appMap = new AppMap(mapName, mapDesc, mapJson, mapName, md5);
            map = appMapService.save(appMap);
        }

        return map;
    }

    private boolean checkIfMapNameIsValid(String mapName){
        boolean ret = false;
        if(StringUtils.isNotBlank(mapName)) {
            if(appMapService.findByName(mapName)==null){
                ret = true;
            }
        }
        return ret;
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

    @RequestMapping(value = "/urlToImage", method = GET)
    public String printPage(@RequestParam(value = "url", required = true) String url) throws Exception {
        return screenCaptureService.captureUrlToImage(url);
    }



    @RequestMapping(value = "/keyToImage", method = GET)
    public String printPageByKey(@RequestParam(value = "key", required = true) String key) throws Exception {
        return screenCaptureService.captureKeyToImage(key);
    }

    @RequestMapping(value = "/keyToPdf", method = GET)
    public String convertKeyMapToPDF(@RequestParam(value = "key", required = true) String key) throws Exception {
        return screenCaptureService.captureKeyToPDF(key);
    }

    @RequestMapping(value = "/urlToPdf", method = GET)
    public String convertPageToPDF(@RequestParam(value = "url", required = true) String url) throws Exception {
        return screenCaptureService.captureUrlToPDF(url);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleAppException(Exception ex) {
        LOGGER.error("Can't complete this request", ex);
        return ex.getMessage();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String,Object> handleBadRequestException(BadRequestException exception) {
        Map<String, Object> result = new HashMap<>();
        result.put("error", "Bad Request");
        result.put("message", exception.getMessage());
        result.put("status", 400);
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }
}

