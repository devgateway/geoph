package org.devgateway.geoph.rest;

import org.devgateway.geoph.persistence.ChartService;
import org.devgateway.geoph.persistence.ProjectService;
import org.devgateway.geoph.util.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    @Autowired
    ProjectService projectService;

    @RequestMapping(method = GET)
    public Map<String, Object> getAllCharts(
            @RequestParam(value = FILTER_START_DATE_MAX, required = false) String startDateMax,
            @RequestParam(value = FILTER_START_DATE_MIN, required = false) String startDateMin,
            @RequestParam(value = FILTER_END_DATE_MAX, required = false) String endDateMax,
            @RequestParam(value = FILTER_END_DATE_MIN, required = false) String endDateMin,
            @RequestParam(value = FILTER_PERFORMANCE_START_MAX, required = false) String performanceStartMax,
            @RequestParam(value = FILTER_PERFORMANCE_START_MIN, required = false) String performanceStartMin,
            @RequestParam(value = FILTER_PERFORMANCE_END_MAX, required = false) String performanceEndMax,
            @RequestParam(value = FILTER_PERFORMANCE_END_MIN, required = false) String performanceEndMin,
            @RequestParam(value = FILTER_SECTOR, required = false) String sectors,
            @RequestParam(value = FILTER_STATUS, required = false) String statuses,
            @RequestParam(value = FILTER_LOCATION, required = false) String locations,
            @RequestParam(value = FILTER_PROJECT, required = false) String projects,
            @RequestParam(value = FILTER_IMPLEMENTING_AGENCY, required = false) String impAgencies,
            @RequestParam(value = FILTER_FUNDING_AGENCY, required = false) String fundingAgencies,
            @RequestParam(value = FILTER_FLOW_TYPE, required = false) String flowTypes,
            @RequestParam(value = FILTER_PROJECT_TITLE, required = false) String projectTitle,
            @RequestParam(value = FILTER_PHYSICAL_STATUS, required = false) String physicalStatuses,
            @RequestParam(value = FILTER_CLIMATE_CHANGE, required = false) String climateChange,
            @RequestParam(value = FILTER_GENDER_RESPONSIVENESS, required = false) String genderResponsiveness,
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MAX, required = false) Double financialAmountMax,
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MIN, required = false) Double financialAmountMin) {
        LOGGER.debug("getAllCharts");
        Parameters params = new Parameters(startDateMax, startDateMin, endDateMax, endDateMin, performanceStartMax,
                performanceStartMin, performanceEndMax, performanceEndMin, sectors, statuses, locations,
                projects, impAgencies, fundingAgencies, flowTypes,
                projectTitle, physicalStatuses, climateChange, genderResponsiveness,
                financialAmountMin, financialAmountMax, null);
        Map<String, Object> allCharts = new HashMap<>();
        allCharts.put("fundingAgency", chartService.getFundingByFundingAgency(params));
        allCharts.put("implementingAgency", chartService.getFundingByImplementingAgency(params));
        allCharts.put("sector", chartService.getFundingBySector(params));
        allCharts.put("physicalStatus", chartService.getFundingByPhysicalStatus(params));
        return allCharts;
    }



    @RequestMapping(value = "/projects", method = GET)
    public Map<String, Object> getAllChartsProjects(
            @RequestParam(value = FILTER_START_DATE_MAX, required = false) String startDateMax,
            @RequestParam(value = FILTER_START_DATE_MIN, required = false) String startDateMin,
            @RequestParam(value = FILTER_END_DATE_MAX, required = false) String endDateMax,
            @RequestParam(value = FILTER_END_DATE_MIN, required = false) String endDateMin,
            @RequestParam(value = FILTER_PERFORMANCE_START_MAX, required = false) String performanceStartMax,
            @RequestParam(value = FILTER_PERFORMANCE_START_MIN, required = false) String performanceStartMin,
            @RequestParam(value = FILTER_PERFORMANCE_END_MAX, required = false) String performanceEndMax,
            @RequestParam(value = FILTER_PERFORMANCE_END_MIN, required = false) String performanceEndMin,
            @RequestParam(value = FILTER_SECTOR, required = false) String sectors,
            @RequestParam(value = FILTER_STATUS, required = false) String statuses,
            @RequestParam(value = FILTER_LOCATION, required = false) String locations,
            @RequestParam(value = FILTER_PROJECT, required = false) String projects,
            @RequestParam(value = FILTER_IMPLEMENTING_AGENCY, required = false) String impAgencies,
            @RequestParam(value = FILTER_FUNDING_AGENCY, required = false) String fundingAgencies,
            @RequestParam(value = FILTER_FLOW_TYPE, required = false) String flowTypes,
            @RequestParam(value = FILTER_PROJECT_TITLE, required = false) String projectTitle,
            @RequestParam(value = FILTER_PHYSICAL_STATUS, required = false) String physicalStatuses,
            @RequestParam(value = FILTER_CLIMATE_CHANGE, required = false) String climateChange,
            @RequestParam(value = FILTER_GENDER_RESPONSIVENESS, required = false) String genderResponsiveness,
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MAX, required = false) Double financialAmountMax,
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MIN, required = false) Double financialAmountMin) {
        LOGGER.debug("getAllChartsProjects");
        Parameters params = new Parameters(startDateMax, startDateMin, endDateMax, endDateMin, performanceStartMax,
                performanceStartMin, performanceEndMax, performanceEndMin, sectors, statuses, locations,
                projects, impAgencies, fundingAgencies, flowTypes,
                projectTitle, physicalStatuses, climateChange, genderResponsiveness,
                financialAmountMin, financialAmountMax, null);
        Map<String, Object> allCharts = new HashMap<>();
        allCharts.put("fundingAgency", chartService.getFundingByFundingAgency(params));
        allCharts.put("implementingAgency", chartService.getFundingByImplementingAgency(params));
        allCharts.put("sector", chartService.getFundingBySector(params));
        allCharts.put("physicalStatus", chartService.getFundingByPhysicalStatus(params));
        allCharts.put("project", projectService.findProjectsByParams(params));
        return allCharts;
    }

    @RequestMapping(value = "/fundingAgency", method = GET)
    public List<Map<String, Object>> getByFundingAgency(
            @RequestParam(value = FILTER_START_DATE_MAX, required = false) String startDateMax,
            @RequestParam(value = FILTER_START_DATE_MIN, required = false) String startDateMin,
            @RequestParam(value = FILTER_END_DATE_MAX, required = false) String endDateMax,
            @RequestParam(value = FILTER_END_DATE_MIN, required = false) String endDateMin,
            @RequestParam(value = FILTER_PERFORMANCE_START_MAX, required = false) String performanceStartMax,
            @RequestParam(value = FILTER_PERFORMANCE_START_MIN, required = false) String performanceStartMin,
            @RequestParam(value = FILTER_PERFORMANCE_END_MAX, required = false) String performanceEndMax,
            @RequestParam(value = FILTER_PERFORMANCE_END_MIN, required = false) String performanceEndMin,
            @RequestParam(value = FILTER_SECTOR, required = false) String sectors,
            @RequestParam(value = FILTER_STATUS, required = false) String statuses,
            @RequestParam(value = FILTER_LOCATION, required = false) String locations,
            @RequestParam(value = FILTER_PROJECT, required = false) String projects,
            @RequestParam(value = FILTER_IMPLEMENTING_AGENCY, required = false) String impAgencies,
            @RequestParam(value = FILTER_FUNDING_AGENCY, required = false) String fundingAgencies,
            @RequestParam(value = FILTER_FLOW_TYPE, required = false) String flowTypes,
            @RequestParam(value = FILTER_PROJECT_TITLE, required = false) String projectTitle,
            @RequestParam(value = FILTER_PHYSICAL_STATUS, required = false) String physicalStatuses,
            @RequestParam(value = FILTER_CLIMATE_CHANGE, required = false) String climateChange,
            @RequestParam(value = FILTER_GENDER_RESPONSIVENESS, required = false) String genderResponsiveness,
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MAX, required = false) Double financialAmountMax,
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MIN, required = false) Double financialAmountMin) {
        LOGGER.debug("getByFundingAgency");
        Parameters params = new Parameters(startDateMax, startDateMin, endDateMax, endDateMin, performanceStartMax,
                performanceStartMin, performanceEndMax, performanceEndMin, sectors, statuses, locations,
                projects, impAgencies, fundingAgencies, flowTypes,
                projectTitle, physicalStatuses, climateChange, genderResponsiveness,
                financialAmountMin, financialAmountMax, null);
        return chartService.getFundingByFundingAgency(params);
    }

    @RequestMapping(value = "/impAgency", method = GET)
    public List<Map<String, Object>> getByImplementingAgency(
            @RequestParam(value = FILTER_START_DATE_MAX, required = false) String startDateMax,
            @RequestParam(value = FILTER_START_DATE_MIN, required = false) String startDateMin,
            @RequestParam(value = FILTER_END_DATE_MAX, required = false) String endDateMax,
            @RequestParam(value = FILTER_END_DATE_MIN, required = false) String endDateMin,
            @RequestParam(value = FILTER_PERFORMANCE_START_MAX, required = false) String performanceStartMax,
            @RequestParam(value = FILTER_PERFORMANCE_START_MIN, required = false) String performanceStartMin,
            @RequestParam(value = FILTER_PERFORMANCE_END_MAX, required = false) String performanceEndMax,
            @RequestParam(value = FILTER_PERFORMANCE_END_MIN, required = false) String performanceEndMin,
            @RequestParam(value = FILTER_SECTOR, required = false) String sectors,
            @RequestParam(value = FILTER_STATUS, required = false) String statuses,
            @RequestParam(value = FILTER_LOCATION, required = false) String locations,
            @RequestParam(value = FILTER_PROJECT, required = false) String projects,
            @RequestParam(value = FILTER_IMPLEMENTING_AGENCY, required = false) String impAgencies,
            @RequestParam(value = FILTER_FUNDING_AGENCY, required = false) String fundingAgencies,
            @RequestParam(value = FILTER_FLOW_TYPE, required = false) String flowTypes,
            @RequestParam(value = FILTER_PROJECT_TITLE, required = false) String projectTitle,
            @RequestParam(value = FILTER_PHYSICAL_STATUS, required = false) String physicalStatuses,
            @RequestParam(value = FILTER_CLIMATE_CHANGE, required = false) String climateChange,
            @RequestParam(value = FILTER_GENDER_RESPONSIVENESS, required = false) String genderResponsiveness,
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MAX, required = false) Double financialAmountMax,
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MIN, required = false) Double financialAmountMin) {
        LOGGER.debug("getByImplementingAgency");
        Parameters params = new Parameters(startDateMax, startDateMin, endDateMax, endDateMin, performanceStartMax,
                performanceStartMin, performanceEndMax, performanceEndMin, sectors, statuses, locations,
                projects, impAgencies, fundingAgencies, flowTypes,
                projectTitle, physicalStatuses, climateChange, genderResponsiveness,
                financialAmountMin, financialAmountMax, null);
        return chartService.getFundingByImplementingAgency(params);
    }

    @RequestMapping(value = "/sector", method = GET)
    public List<Map<String, Object>> getBySector(
            @RequestParam(value = FILTER_START_DATE_MAX, required = false) String startDateMax,
            @RequestParam(value = FILTER_START_DATE_MIN, required = false) String startDateMin,
            @RequestParam(value = FILTER_END_DATE_MAX, required = false) String endDateMax,
            @RequestParam(value = FILTER_END_DATE_MIN, required = false) String endDateMin,
            @RequestParam(value = FILTER_PERFORMANCE_START_MAX, required = false) String performanceStartMax,
            @RequestParam(value = FILTER_PERFORMANCE_START_MIN, required = false) String performanceStartMin,
            @RequestParam(value = FILTER_PERFORMANCE_END_MAX, required = false) String performanceEndMax,
            @RequestParam(value = FILTER_PERFORMANCE_END_MIN, required = false) String performanceEndMin,
            @RequestParam(value = FILTER_SECTOR, required = false) String sectors,
            @RequestParam(value = FILTER_STATUS, required = false) String statuses,
            @RequestParam(value = FILTER_LOCATION, required = false) String locations,
            @RequestParam(value = FILTER_PROJECT, required = false) String projects,
            @RequestParam(value = FILTER_IMPLEMENTING_AGENCY, required = false) String impAgencies,
            @RequestParam(value = FILTER_FUNDING_AGENCY, required = false) String fundingAgencies,
            @RequestParam(value = FILTER_FLOW_TYPE, required = false) String flowTypes,
            @RequestParam(value = FILTER_PROJECT_TITLE, required = false) String projectTitle,
            @RequestParam(value = FILTER_PHYSICAL_STATUS, required = false) String physicalStatuses,
            @RequestParam(value = FILTER_CLIMATE_CHANGE, required = false) String climateChange,
            @RequestParam(value = FILTER_GENDER_RESPONSIVENESS, required = false) String genderResponsiveness,
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MAX, required = false) Double financialAmountMax,
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MIN, required = false) Double financialAmountMin) {
        LOGGER.debug("getBySector");
        Parameters params = new Parameters(startDateMax, startDateMin, endDateMax, endDateMin, performanceStartMax,
                performanceStartMin, performanceEndMax, performanceEndMin, sectors, statuses, locations,
                projects, impAgencies, fundingAgencies, flowTypes,
                projectTitle, physicalStatuses, climateChange, genderResponsiveness,
                financialAmountMin, financialAmountMax, null);
        return chartService.getFundingBySector(params);
    }

    @RequestMapping(value = "/physicalStatus", method = GET)
    public List<Map<String, Object>> getByPhysicalStatus(
            @RequestParam(value = FILTER_START_DATE_MAX, required = false) String startDateMax,
            @RequestParam(value = FILTER_START_DATE_MIN, required = false) String startDateMin,
            @RequestParam(value = FILTER_END_DATE_MAX, required = false) String endDateMax,
            @RequestParam(value = FILTER_END_DATE_MIN, required = false) String endDateMin,
            @RequestParam(value = FILTER_PERFORMANCE_START_MAX, required = false) String performanceStartMax,
            @RequestParam(value = FILTER_PERFORMANCE_START_MIN, required = false) String performanceStartMin,
            @RequestParam(value = FILTER_PERFORMANCE_END_MAX, required = false) String performanceEndMax,
            @RequestParam(value = FILTER_PERFORMANCE_END_MIN, required = false) String performanceEndMin,
            @RequestParam(value = FILTER_SECTOR, required = false) String sectors,
            @RequestParam(value = FILTER_STATUS, required = false) String statuses,
            @RequestParam(value = FILTER_LOCATION, required = false) String locations,
            @RequestParam(value = FILTER_PROJECT, required = false) String projects,
            @RequestParam(value = FILTER_IMPLEMENTING_AGENCY, required = false) String impAgencies,
            @RequestParam(value = FILTER_FUNDING_AGENCY, required = false) String fundingAgencies,
            @RequestParam(value = FILTER_FLOW_TYPE, required = false) String flowTypes,
            @RequestParam(value = FILTER_PROJECT_TITLE, required = false) String projectTitle,
            @RequestParam(value = FILTER_PHYSICAL_STATUS, required = false) String physicalStatuses,
            @RequestParam(value = FILTER_CLIMATE_CHANGE, required = false) String climateChange,
            @RequestParam(value = FILTER_GENDER_RESPONSIVENESS, required = false) String genderResponsiveness,
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MAX, required = false) Double financialAmountMax,
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MIN, required = false) Double financialAmountMin) {
        LOGGER.debug("getBySector");
        Parameters params = new Parameters(startDateMax, startDateMin, endDateMax, endDateMin, performanceStartMax,
                performanceStartMin, performanceEndMax, performanceEndMin, sectors, statuses, locations,
                projects, impAgencies, fundingAgencies, flowTypes,
                projectTitle, physicalStatuses, climateChange, genderResponsiveness,
                financialAmountMin, financialAmountMax, null);
        return chartService.getFundingByPhysicalStatus(params);
    }
}
