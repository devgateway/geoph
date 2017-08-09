/*
 * ******************************************************************************
 *  * Copyright (c) 2015 Development Gateway, Inc and others.
 *  *
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the MIT License (MIT)
 *  * which accompanies this distribution, and is available at
 *  * https://opensource.org/licenses/MIT
 *  *
 *  * Contributors:
 *  * Development Gateway - initial API and implementation
 *  ******************************************************************************
 */

package org.devgateway.geoph.services;

import org.devgateway.geoph.core.services.ApplicationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by dbianco on 27/03/2017.
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Override
    public boolean isUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication.getClass());
    }
}
