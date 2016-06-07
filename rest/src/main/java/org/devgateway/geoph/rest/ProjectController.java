package org.devgateway.geoph.rest;

import org.devgateway.geoph.model.Project;
import org.devgateway.geoph.response.StatsResponse;
import org.devgateway.geoph.services.ProjectService;
import org.devgateway.geoph.util.Parameters;
import org.devgateway.geoph.util.ProjectOrder;
import org.hibernate.jpa.criteria.OrderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.persistence.criteria.Order;

import static org.devgateway.geoph.util.Constants.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


/**
 * @author dbianco
 *         created on mar 08 2016.
 */
@RestController
@RequestMapping(value = "/projects")
public class ProjectController  extends CrossOriginSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterController.class);

    private final ProjectService service;

    @Autowired
    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @RequestMapping(method = GET)
    public Page<Project> findProjectsByParams(
            @RequestParam(value = FILTER_REACHED_OWPA_MAX, required = false) Float reachedOwpaMax,
            @RequestParam(value = FILTER_REACHED_OWPA_MIN, required = false) Float reachedOwpaMin,
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
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MIN, required = false) Double financialAmountMin,
            @PageableDefault(page = 0, size = 20, sort = "id") final Pageable pageable) {
        LOGGER.debug("findProjectsByParams");
        Parameters params = new Parameters(reachedOwpaMax, reachedOwpaMin,
                startDateMax, startDateMin, endDateMax, endDateMin, performanceStartMax,
                performanceStartMin, performanceEndMax, performanceEndMin, sectors, statuses, locations,
                projects, impAgencies, fundingAgencies, flowTypes,
                projectTitle, physicalStatuses, climateChange, genderResponsiveness,
                financialAmountMin, financialAmountMax, pageable);
        return service.findProjectsByParams(params);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public Project findProjectById(@PathVariable final long id) {
        LOGGER.debug("findProjectById");
        return service.findById(id);
    }

    @RequestMapping(value = "/all", method = GET)
    public Page<Project> findAllProjects(
            @RequestParam(value = FILTER_REACHED_OWPA_MAX, required = false) Float reachedOwpaMax,
            @RequestParam(value = FILTER_REACHED_OWPA_MIN, required = false) Float reachedOwpaMin,
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
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MIN, required = false) Double financialAmountMin,
            ProjectOrder projectOrder) {
        LOGGER.debug("findAllProjects");
        Parameters params = new Parameters(reachedOwpaMax, reachedOwpaMin,
                startDateMax, startDateMin, endDateMax, endDateMin, performanceStartMax,
                performanceStartMin, performanceEndMax, performanceEndMin, sectors, statuses, locations,
                projects, impAgencies, fundingAgencies, flowTypes,
                projectTitle, physicalStatuses, climateChange, genderResponsiveness,
                financialAmountMin, financialAmountMax, null);
        params.setProjectOrder(projectOrder);
        return service.findProjectsByParams(params);
    }


    @RequestMapping(value = "/count", method = GET)
    public StatsResponse countProjects(
            @RequestParam(value = FILTER_REACHED_OWPA_MAX, required = false) Float reachedOwpaMax,
            @RequestParam(value = FILTER_REACHED_OWPA_MIN, required = false) Float reachedOwpaMin,
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
        LOGGER.debug("countProjects");
        Parameters params = new Parameters(reachedOwpaMax, reachedOwpaMin,
                startDateMax, startDateMin, endDateMax, endDateMin, performanceStartMax,
                performanceStartMin, performanceEndMax, performanceEndMin, sectors, statuses, locations,
                projects, impAgencies, fundingAgencies, flowTypes,
                projectTitle, physicalStatuses, climateChange, genderResponsiveness,
                financialAmountMin, financialAmountMax, null);
        return service.countProjectsByParams(params);
    }
}
