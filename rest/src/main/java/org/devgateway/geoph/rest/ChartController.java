package org.devgateway.geoph.rest;

import org.devgateway.geoph.services.ChartService;
import org.devgateway.geoph.util.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static org.devgateway.geoph.util.Constants.*;
import static org.devgateway.geoph.util.Constants.FILTER_FLOW_TYPE;
import static org.devgateway.geoph.util.Constants.FILTER_FUNDING_AGENCY;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author dbianco
 *         created on abr 04 2016.
 */
@RestController
@RequestMapping(value = "/charts")
public class ChartController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChartController.class);

    @Autowired
    ChartService chartService;

    @RequestMapping(value = "/fundingAgency", method = GET)
    public List<Map<String, String>> getByFundingAgency(
            @RequestParam(value = FILTER_DATE_START, required = false) String startDate,
            @RequestParam(value = FILTER_DATE_END, required = false) String endDate,
            @RequestParam(value = FILTER_SECTOR, required = false) String sectors,
            @RequestParam(value = FILTER_STATUS, required = false) String statuses,
            @RequestParam(value = FILTER_LOCATION, required = false) String locations,
            @RequestParam(value = FILTER_PROJECT, required = false) String projects,
            @RequestParam(value = FILTER_IMPLEMENTING_AGENCY, required = false) String impAgencies,
            @RequestParam(value = FILTER_FUNDING_AGENCY, required = false) String fundingAgencies,
            @RequestParam(value = FILTER_FLOW_TYPE, required = false) String flowTypes) {
        LOGGER.debug("getByFundingAgency");
        Parameters params = new Parameters(startDate, endDate, sectors, statuses,
                locations, projects, impAgencies, fundingAgencies, flowTypes, null);
        return chartService.getFundingByFundingAgency(params);
    }
}
