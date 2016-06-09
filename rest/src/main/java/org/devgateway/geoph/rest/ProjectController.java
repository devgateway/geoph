package org.devgateway.geoph.rest;

import org.devgateway.geoph.core.request.AppRequestParams;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.request.ProjectOrder;
import org.devgateway.geoph.core.response.StatsResponse;
import org.devgateway.geoph.core.services.ProjectService;
import org.devgateway.geoph.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


/**
 * @author dbianco
 *         created on mar 08 2016.
 */
@RestController
@RequestMapping(value = "/projects")
public class ProjectController extends CrossOriginSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterController.class);

    private final ProjectService service;

    @Autowired
    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @RequestMapping(method = GET)
    public Page<Project> findProjectsByParams(AppRequestParams filters,
                                              @PageableDefault(page = 0, size = 20, sort = "id") final Pageable pageable) {
        LOGGER.debug("findProjectsByParams");
        Parameters params = filters.getParameters();
        params.setPageable(pageable);
        return service.findProjectsByParams(params);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public Project findProjectById(@PathVariable final long id) {
        LOGGER.debug("findProjectById");
        return service.findById(id);
    }

    @RequestMapping(value = "/all", method = GET)
    public Page<Project> findAllProjects(AppRequestParams filters,
                                         ProjectOrder projectOrder) {
        LOGGER.debug("findAllProjects");
        Parameters params = filters.getParameters();
        params.setProjectOrder(projectOrder);
        return service.findProjectsByParams(params);
    }


    @RequestMapping(value = "/count", method = GET)
    public StatsResponse countProjects(AppRequestParams filters) {
        LOGGER.debug("countProjects");
        return service.countProjectsByParams(filters.getParameters());
    }
}
