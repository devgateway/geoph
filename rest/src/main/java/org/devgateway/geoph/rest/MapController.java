package org.devgateway.geoph.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.devgateway.geoph.core.exceptions.BadRequestException;
import org.devgateway.geoph.core.services.AppMapService;
import org.devgateway.geoph.core.services.ScreenCaptureService;
import org.devgateway.geoph.core.util.MD5Generator;
import org.devgateway.geoph.enums.AppMapTypeEnum;
import org.devgateway.geoph.model.AppMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
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
    private static final String SHARED_MAP_DESC = "Shared map";

    private final AppMapService appMapService;

    private final ScreenCaptureService screenCaptureService;

    @Autowired
    public MapController(AppMapService appMapService, ScreenCaptureService screenCaptureService) {
        this.screenCaptureService=screenCaptureService;
        this.appMapService = appMapService;
    }

    @RequestMapping(method = GET)
    public Page<AppMap> findMaps(@PageableDefault(page = 0, size = 20, sort = "id") final Pageable pageable, @RequestParam(required = false)  AppMapTypeEnum type) {
        LOGGER.debug("findMaps");
        return appMapService.findAll(pageable);
    }


    @RequestMapping(value = "/save", method = POST)
    public AppMap saveMap(@RequestBody Map<String, Object> mapVariables) throws IOException, SQLException {
        LOGGER.debug("saveMap");
        String mapName = (String) mapVariables.get(NAME_STR);
        String base64 = null;
        if (checkIfMapNameIsValid(mapName)) {
            String html = (String) mapVariables.get("html");
            Integer width = (Integer) mapVariables.get("width");
            Integer height = (Integer) mapVariables.get("height");
            Integer scaleWidth = (Integer) mapVariables.get("scaleWidth");
            Integer scaleHeight = (Integer) mapVariables.get("scaleHeight");

            if (html != null) {
                //get preview image
                BufferedImage image = screenCaptureService.captureImage(width, height, screenCaptureService.buildPage(width, height, html).toURI());

                if (scaleWidth != null) {
                    image = screenCaptureService.scaleWidth(image, scaleWidth);
                } else if (scaleHeight != null) {
                    image = screenCaptureService.scaleWidth(image, scaleHeight);
                }
                base64 = screenCaptureService.toBase64(image);

            }
            String mapDesc = (String) mapVariables.get(DESCRIPTION_STR);
            String mapJson = new ObjectMapper().writeValueAsString(mapVariables.get(DATA_TO_SAVE_STR));
             AppMap appMap = new AppMap(mapName, mapDesc, mapJson, UUID.randomUUID().toString(), MD5Generator.getMD5(mapJson), AppMapTypeEnum.SAVE.getName(),base64);

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
            map = appMapService.save(new AppMap(mapName, mapDesc, mapJson, mapName, md5, AppMapTypeEnum.SHARE.getName(),null));
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

