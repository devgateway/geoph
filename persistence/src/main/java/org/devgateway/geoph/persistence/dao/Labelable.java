/*******************************************************************************
 * Copyright (c) 2015 Development Gateway, Inc and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the MIT License (MIT)
 * which accompanies this distribution, and is available at
 * https://opensource.org/licenses/MIT
 *
 * Contributors:
 * Development Gateway - initial API and implementation
 *******************************************************************************/
/**
 * 
 */
package org.devgateway.geoph.persistence.dao;

/**
 * @author mpostelnicu An entity that has a {@link String} label property
 */
public interface Labelable {

	public void setLabel(String label);

	public String getLabel();

}
