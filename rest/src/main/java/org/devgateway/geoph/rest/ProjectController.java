package org.devgateway.geoph.rest;

import org.apache.commons.lang3.StringUtils;
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


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.devgateway.geoph.util.Constants.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


/**
 * @author dbianco
 *         created on mar 08 2016.
 */
@RestController
@RequestMapping(value = "/projects")
public class ProjectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterController.class);


    private final ProjectService service;
    @Autowired
    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @RequestMapping(method = GET)
    public Page<Project> findProjectsByParams(
            @RequestParam(value = FILTER_DATE_START, required = false) String startDate,
            @RequestParam(value = FILTER_DATE_END, required = false) String endDate,
            @RequestParam(value = FILTER_SECTOR, required = false) String sectors,
            @RequestParam(value = FILTER_STATUS, required = false) String statuses,
            @RequestParam(value = FILTER_LOCATION, required = false) String locations,
            @RequestParam(value = FILTER_PROJECT, required = false) String projects,
            @PageableDefault(page = 0, size = 20, sort = "id") final Pageable pageable) {
        LOGGER.debug("findProjectsByParams");
        Parameters params = new Parameters(startDate, endDate, sectors, statuses, locations, projects, pageable);
        return service.findProjectsByParams(params);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public Project findProjectById(@PathVariable final long id) {
        LOGGER.debug("findProjectById");
        return service.findById(id);
    }

}
