package org.devgateway.geoph.rest;

import org.devgateway.geoph.model.Agency;
import org.devgateway.geoph.services.FilterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;


/**
 * @author dbianco
 *         created on feb 29 2016.
 */

@RestController
@RequestMapping(value = "/filter", produces = "application/json", consumes = "application/json")
public class FilterController {

    private final FilterService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterController.class);

    @Autowired
    public FilterController(FilterService service) {
        this.service = service;
    }

    @RequestMapping(value = "/agency", method = GET)
    //@Secured("ROLE_READ")
    public Page<Agency> getAllAgencies(@PageableDefault(page = 0, size = 20, sort = "id")
                                                  final Pageable pageable) {
        LOGGER.debug("getAllAgencies");
        return service.findAllAgencies(pageable);
    }
}
