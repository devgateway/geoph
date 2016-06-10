package org.devgateway.geoph.rest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.devgateway.geoph.core.services.AppMapService;
import org.devgateway.geoph.core.services.GeoJsonService;
import org.devgateway.geoph.dao.PropsHelper;
import org.devgateway.geoph.model.AppMap;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Random;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author dbianco
 *         created on abr 20 2016.
 */
@RestController
@RequestMapping(value = "/maps")
public class MapController {
    public static final String ALPHABET = "BCDFGHIJKLMNPQRSTVWXZ";
    public static final int ALPHABET_NUMBER = ALPHABET.length();

    private static final Logger LOGGER = LoggerFactory.getLogger(MapController.class);

    private final AppMapService service;

    private final GeoJsonService geoJsonService;

    @RequestMapping(method = GET)
    public Page<AppMap> findMaps(@PageableDefault(page = 0, size = 20, sort = "id") final Pageable pageable) {
        LOGGER.debug("findMaps");
        return service.findAll(pageable);
    }

    @Autowired
    public MapController(AppMapService service, GeoJsonService geoJsonService) {
        this.service = service;
        this.geoJsonService = geoJsonService;
    }

    @RequestMapping(value = "/save", method = POST)
    public AppMap saveMap(@RequestParam(value = "name", required = true) String name,
                          @RequestParam(value = "description", required = true) String description,
                          @RequestBody Object mapVariables) {
        LOGGER.debug("saveMap");
        AppMap appMap = new AppMap(name, description, mapVariables.toString());
        return service.save(appMap);
    }

    @RequestMapping(value = "/id/{id}", method = GET)
    public AppMap findMapById(@PathVariable final long id) {
        LOGGER.debug("findMapById");
        return service.findById(id);
    }

    @RequestMapping(value = "/key/{key}", method = GET)
    public AppMap findMapByKey(@PathVariable final String key) {
        LOGGER.debug("findMapByKey");
        return service.findByKey(key);
    }


    @RequestMapping(value = "/search/{name}", method = GET)
    public List<AppMap> findMapByName(@PathVariable final String name) {
        LOGGER.debug("findMapByKey");
        return service.findByNameOrDescription(name);
    }

    @RequestMapping(value = "/print", method = GET)
    public String printPage(@RequestParam(value = "url", required = true) String url) {
        String filename = null;
        try {
            if (StringUtils.isNotBlank(PropsHelper.getScreenFirefoxExe())) {
                File pathToBinary = new File(PropsHelper.getScreenFirefoxExe());
                FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);
                driver.get(url);
                Thread.sleep(PropsHelper.getScreenCaptureTimeToWait());
                filename = getRandomKey() + ".png";
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(PropsHelper.getScreenCaptureDir() + filename));
                driver.close();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return filename;
    }


    //TODO:move to utils or use a key generator lib
    private static String getRandomKey() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            sb.append(ALPHABET.charAt(r.nextInt(ALPHABET_NUMBER)));
        }
        return sb.toString().toLowerCase();
    }

}
