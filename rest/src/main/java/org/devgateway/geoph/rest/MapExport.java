package org.devgateway.geoph.rest;

import org.devgateway.geoph.core.services.ScreenCaptureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by sebas on 8/3/2016.
 */

@RestController
@RequestMapping(value = "/export")
public class MapExport {

    private final ScreenCaptureService screenCaptureService;

    @Autowired
    public MapExport(ScreenCaptureService screenCaptureService) {
        this.screenCaptureService = screenCaptureService;
    }

    @RequestMapping(value = "/pdf", method = POST)
    public String toPdf(@RequestBody Map<String, String> params) throws Exception {

        Integer width=Integer.parseInt(params.get("width"));
        Integer height=Integer.parseInt(params.get("height"));
        String html = params.get("html");

        return screenCaptureService.createPdfFromHtmlString(width, height, html);
    }

}
