package org.devgateway.geoph.rest;

import com.ui4j.api.browser.BrowserFactory;
import org.devgateway.geoph.model.AppMap;
import org.devgateway.geoph.services.AppMapService;
import org.devgateway.geoph.util.PropsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Random;

import static org.devgateway.geoph.util.Constants.ALPHABET;
import static org.devgateway.geoph.util.Constants.ALPHABET_NUMBER;
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

    private final AppMapService service;

    @RequestMapping(method = GET)
    public Page<AppMap> findMaps( @PageableDefault(page = 0, size = 20, sort = "id") final Pageable pageable) {
        LOGGER.debug("findMaps");
        return service.findAll(pageable);
    }

    @Autowired
    public MapController(AppMapService service) {
        this.service = service;
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
    public List <AppMap> findMapByName(@PathVariable final String name) {
        LOGGER.debug("findMapByKey");
        return service.findByNameOrDescription(name);
    }

    @RequestMapping(value = "/print", method = GET)
    public String printPage(@RequestParam(value = "url", required = true) String url){
        String filename = null;
        try {
            com.ui4j.api.browser.Page page = BrowserFactory.getWebKit().navigate(url);
            page.show(true);
            Thread.sleep(PropsHelper.getScreenCaptureTimeToWait());
            filename = getRandomKey() + ".png";
            page.captureScreen(new FileOutputStream(new File(PropsHelper.getScreenCaptureDir() + filename)));

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return filename;
    }

    private static String getRandomKey(){
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            sb.append(ALPHABET.charAt(r.nextInt(ALPHABET_NUMBER)));
        }
        return sb.toString().toLowerCase();
    }

}
