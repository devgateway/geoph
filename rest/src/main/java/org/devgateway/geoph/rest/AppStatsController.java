package org.devgateway.geoph.rest;

import org.devgateway.geoph.services.AppStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author dbianco
 *         created on mar 28 2016.
 */
@RestController
@RequestMapping(value = "/appStats")
public class AppStatsController {


    private final AppStatsService appStatsService;

    @Autowired
    public AppStatsController(AppStatsService appStatsService){
        this.appStatsService=appStatsService;
    }


    @RequestMapping(value = "/EhCache", method = GET)
    public String getCacheStats() {
        return appStatsService.getCacheStats();
    }
}
