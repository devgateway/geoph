package org.devgateway.geoph.rest;

import org.apache.commons.lang3.StringUtils;
import org.devgateway.geoph.model.Project;
import org.devgateway.geoph.services.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping(value = "/project")
public class ProjectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterController.class);


    private final ProjectService service;
    @Autowired
    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = GET)
    //@Secured("ROLE_READ")
    public List<Project> findAllProjects() {
        LOGGER.debug("findAllProjects");
        return service.findAllProjects();
    }


    @RequestMapping(value = "/test", method = GET)
    //@Secured("ROLE_READ")
    public Page<Project> findProjectsByParams(
            @RequestParam(value = FILTER_SECTOR, required = false) String st,
            @RequestParam(value = FILTER_LOCATION, required = false) String lo,
            @RequestParam(value = "op", required = false) String op,
            @PageableDefault(page = 0, size = 20, sort = "id") final Pageable pageable) {
        LOGGER.debug("findProjectsByParams");

        Map<String, String[]> params = new HashMap<>();
        if(StringUtils.isNotBlank(st)){
            params.put(FILTER_SECTOR, st.split(PARAM_SEPARATOR));
        }
        if(StringUtils.isNotBlank(lo)){
            params.put(FILTER_LOCATION, lo.split(PARAM_SEPARATOR));
        }

        return service.findProjectsByParams(params, pageable);
    }

}
