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

package org.devgateway.geoph.core.services;

/**
 * Created by dbianco on 27/03/2017.
 */
@FunctionalInterface
public interface ApplicationService {

    boolean isUserAuthenticated();

}
