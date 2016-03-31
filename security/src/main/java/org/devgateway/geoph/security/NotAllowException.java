package org.devgateway.geoph.security;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * @author dbianco
 *         created on mar 31 2016.
 */
@ResponseStatus(value = UNAUTHORIZED, reason = "Could not perform operation")
public class NotAllowException extends RuntimeException {
    public NotAllowException(String message) {
        super(message);
    }
}
