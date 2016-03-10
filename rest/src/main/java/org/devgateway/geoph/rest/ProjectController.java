package org.devgateway.geoph.rest;

import org.devgateway.geoph.model.Project;
import org.devgateway.geoph.services.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public Page<Project> findAllProjects(@PageableDefault(page = 0, size = 20, sort = "id") final Pageable pageable) {
        LOGGER.debug("findAllProjects");
        return service.findAllProjects(pageable);
    }


    @RequestMapping(value = "/test", method = GET)
    //@Secured("ROLE_READ")
    public Page<Project> findProjectsByParams(@PageableDefault(page = 0, size = 20, sort = "id") final Pageable pageable) {
        LOGGER.debug("findProjectsByParams");
        return service.findProjectsByParams("st=35,19&lo=230", pageable);
    }

}
