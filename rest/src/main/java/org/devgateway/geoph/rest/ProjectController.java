package org.devgateway.geoph.rest;

import org.devgateway.geoph.model.Project;
import org.devgateway.geoph.services.ProjectService;
import org.devgateway.geoph.util.Parameters;
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
            @RequestParam(value = FILTER_START_DATE, required = false) String startDate,
            @RequestParam(value = FILTER_END_DATE, required = false) String endDate,
            @RequestParam(value = FILTER_PERFORMANCE_START, required = false) String performanceStart,
            @RequestParam(value = FILTER_PERFORMANCE_END, required = false) String performanceEnd,
            @RequestParam(value = FILTER_SECTOR, required = false) String sectors,
            @RequestParam(value = FILTER_STATUS, required = false) String statuses,
            @RequestParam(value = FILTER_LOCATION, required = false) String locations,
            @RequestParam(value = FILTER_PROJECT, required = false) String projects,
            @RequestParam(value = FILTER_IMPLEMENTING_AGENCY, required = false) String impAgencies,
            @RequestParam(value = FILTER_FUNDING_AGENCY, required = false) String fundingAgencies,
            @RequestParam(value = FILTER_FLOW_TYPE, required = false) String flowTypes,
            @RequestParam(value = FILTER_PROJECT_TITLE, required = false) String projectTitle,
            @RequestParam(value = FILTER_PHYSICAL_STATUS, required = false) String physicalStatuses,
            @PageableDefault(page = 0, size = 20, sort = "id") final Pageable pageable) {
        LOGGER.debug("findProjectsByParams");
        Parameters params = new Parameters(startDate, endDate, performanceStart,
                performanceEnd, sectors, statuses, locations,
                projects, impAgencies, fundingAgencies, flowTypes,
                projectTitle, physicalStatuses, pageable);
        return service.findProjectsByParams(params);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public Project findProjectById(@PathVariable final long id) {
        LOGGER.debug("findProjectById");
        return service.findById(id);
    }

}
