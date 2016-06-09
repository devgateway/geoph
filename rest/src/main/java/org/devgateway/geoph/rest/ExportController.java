package org.devgateway.geoph.rest;

import org.devgateway.geoph.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Sebastian Dimunzio on 6/9/2016.
 */
@RestController
@RequestMapping(value = "/filters")
public class ExportController {

    @Autowired
    LocationService locationService
    @RequestMapping(value = "/export", method = GET)
    public String export() {

        new Export().export(locationService.getLocations());


    }
}
