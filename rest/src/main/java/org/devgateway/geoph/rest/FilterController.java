package org.devgateway.geoph.rest;

import org.devgateway.geoph.model.Agency;
import org.devgateway.geoph.model.ImplementingAgency;
import org.devgateway.geoph.response.GenericResponse;
import org.devgateway.geoph.services.FilterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;


/**
 * @author dbianco
 *         created on feb 29 2016.
 */

@RestController
@RequestMapping(value = "/filters", produces = "application/json", consumes = "application/json")
public class FilterController {

    private final FilterService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterController.class);

    @Autowired
    public FilterController(FilterService service) {
        this.service = service;
    }

    @RequestMapping(value = "/impagency", method = GET)
    //@Secured("ROLE_READ")
    public GenericResponse getAllImpAgencies(@PageableDefault(page = 0, size = 20, sort = "id")
                                                  final Pageable pageable) {
        LOGGER.debug("getAllImpAgencies");
        List<ImplementingAgency> agencies = service.findAllImpAgencies();
        GenericResponse resp = new GenericResponse(
                "Implementing Agencies",
                "ia",
                "IMPLEMENTING_AGENCY_SECTION",
                "1",
                new HashSet<>(agencies)
        );

        return resp;
    }
}
