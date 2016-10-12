package org.devgateway.geoph.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Sebastian Dimunzio on 3/15/2016.
 */
@ControllerAdvice
@CrossOrigin(origins = "*", maxAge = 3600)
public abstract class BaseController {


    private static final Logger LOGGER = LoggerFactory.getLogger(MapController.class);


    @ExceptionHandler(Exception.class)
    @ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleAppException(Exception ex) {
        LOGGER.error("Can't complete this request", ex);
        return "Error: " + ex.getMessage();
    }

}
