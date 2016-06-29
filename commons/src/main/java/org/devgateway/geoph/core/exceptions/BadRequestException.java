package org.devgateway.geoph.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author dbianco
 *         created on jun 27 2016.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad Request, please check your request")
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message, Exception e) {
        super(message, e);
    }

    public BadRequestException(String message) {
        super(message);
    }
}
