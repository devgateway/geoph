package org.devgateway.geoph.rest;

import org.devgateway.geoph.core.services.FilterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

//TODO:return all app config including end points???

@RestController
@RequestMapping(value = "/config")
public class ConfigController {

    private final FilterService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterController.class);

    @Autowired
    public ConfigController(FilterService service) {
        this.service = service;
    }

    @RequestMapping(value = "/filters", method = GET)
    public Map<String, Object> findFiltersConfig() {
        LOGGER.debug("findFiltersConfig");
        Map<String, Object> allConfig = new HashMap<>();
        allConfig.put("financialAmountPeriod", service.findFinancialAmountPeriod());
        allConfig.put("impPeriod", service.findImpPeriodBoundaries());
        allConfig.put("impAgenciesCount", service.countImpAgencies());
        allConfig.put("targetReachedPeriod", service.findTargetReachedPeriodBoundaries());
        allConfig.put("grantPeriod", service.findGrantPeriodBoundaries());

        return allConfig;
    }
}
