package org.devgateway.geoph.rest;

import org.devgateway.geoph.core.request.AppRequestParams;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.response.ChartResponse;
import org.devgateway.geoph.core.services.ChartService;
import org.devgateway.geoph.core.services.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
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
        LOGGER.debug("getAllChartsData info");
        Parameters params = filters.getParameters();
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
        Map<String, Object> allCharts = getAllCharts(filters);
        allCharts.put("project", projectService.findProjectsByParams(filters.getParameters()));
        return allCharts;
    }

    @RequestMapping(value = "/fundingAgency", method = GET)
    public Collection<ChartResponse> getByFundingAgency(AppRequestParams filters) {
        LOGGER.debug("getByFundingAgency info");
        return chartService.getFundingByFundingAgency(filters.getParameters());
    }

    @RequestMapping(value = "/executingAgency", method = GET)
    public Collection<ChartResponse> getByExecutingAgency(AppRequestParams filters) {
        LOGGER.debug("getByExecutingAgency info");
        return chartService.getFundingByExecutingAgency(filters.getParameters());
    }

    @RequestMapping(value = "/impAgency", method = GET)
    public Collection<ChartResponse> getByImplementingAgency(AppRequestParams filters) {
        LOGGER.debug("getByImplementingAgency info");
        return chartService.getFundingByImplementingAgency(filters.getParameters());
    }

    @RequestMapping(value = "/sector", method = GET)
    public Collection<ChartResponse> getBySector(AppRequestParams filters) {
        LOGGER.debug("getBySector info");
        return chartService.getFundingBySector(filters.getParameters());
    }

    @RequestMapping(value = "/physicalStatus", method = GET)
    public Collection<ChartResponse> getByPhysicalStatus(AppRequestParams filters) {
        LOGGER.debug("getBySector info");
        return chartService.getFundingByPhysicalStatus(filters.getParameters());
    }
}
