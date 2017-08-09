package org.devgateway.geoph.rest;

import org.devgateway.geoph.core.services.AppStatsService;
import org.devgateway.geoph.core.services.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author dbianco
 *         created on aug 8 2017.
 */
@RestController
@RequestMapping(value = "/application")
public class ApplicationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private AppStatsService appStatsService;

    @RequestMapping(value = "/isAuthenticated", method = GET)
    public boolean checkAuth() {
        LOGGER.debug("check authenticated user");
        return applicationService.isUserAuthenticated();
    }

    @RequestMapping(value = "/EhCache", method = GET)
    public List<Map<String, String>> getCacheStats() {
        return appStatsService.getCacheStats();
    }

    @RequestMapping(value = "/EhCache", method = DELETE)
    public void clearCache() {
        appStatsService.clearAllCache();
    }
}
