package org.devgateway.geoph.rest;

import org.devgateway.geoph.response.ChartResponse;
import org.devgateway.geoph.services.ChartService;
import org.devgateway.geoph.services.ProjectService;
import org.devgateway.geoph.util.AppRequestParams;
import org.devgateway.geoph.util.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    ProjectService projectService;

    @RequestMapping(method = GET)
    public Map<String, Object> getAllCharts(AppRequestParams filters) {
        LOGGER.debug("getAllCharts info");
        Parameters params = new Parameters(filters);
        Map<String, Object> allCharts = new HashMap<>();
        allCharts.put("fundingAgency", chartService.getFundingByFundingAgency(params));
        allCharts.put("implementingAgency", chartService.getFundingByImplementingAgency(params));
        allCharts.put("sector", chartService.getFundingBySector(params));
        allCharts.put("physicalStatus", chartService.getFundingByPhysicalStatus(params));
        return allCharts;
    }


    @RequestMapping(value = "/projects", method = GET)
    public Map<String, Object> getAllChartsAndProjects(AppRequestParams filters) {
        LOGGER.debug("getAllChartsAndProjects info");
        Parameters params = new Parameters(filters);
        Map<String, Object> allCharts = new HashMap<>();
        allCharts.put("fundingAgency", chartService.getFundingByFundingAgency(params));
        allCharts.put("implementingAgency", chartService.getFundingByImplementingAgency(params));
        allCharts.put("sector", chartService.getFundingBySector(params));
        allCharts.put("physicalStatus", chartService.getFundingByPhysicalStatus(params));
        allCharts.put("project", projectService.findProjectsByParams(params));
        return allCharts;
    }

    @RequestMapping(value = "/fundingAgency", method = GET)
    public Collection<ChartResponse> getByFundingAgency(AppRequestParams filters) {
        LOGGER.debug("getByFundingAgency info");
        Parameters params = new Parameters(filters);
        return chartService.getFundingByFundingAgency(params);
    }

    @RequestMapping(value = "/impAgency", method = GET)
    public Collection<ChartResponse> getByImplementingAgency(AppRequestParams filters) {
        LOGGER.debug("getByImplementingAgency info");
        Parameters params = new Parameters(filters);
        return chartService.getFundingByImplementingAgency(params);
    }

    @RequestMapping(value = "/sector", method = GET)
    public Collection<ChartResponse> getBySector(AppRequestParams filters) {
        LOGGER.debug("getBySector info");
        Parameters params = new Parameters(filters);
        return chartService.getFundingBySector(params);
    }

    @RequestMapping(value = "/physicalStatus", method = GET)
    public Collection<ChartResponse> getByPhysicalStatus(AppRequestParams filters) {
        LOGGER.debug("getBySector info");
        Parameters params = new Parameters(filters);
        return chartService.getFundingByPhysicalStatus(params);
    }
}
