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
package org.devgateway.geoph.persistence.repository;


import org.devgateway.geoph.persistence.dao.categories.Group;
import org.devgateway.geoph.persistence.dao.categories.Group;
import org.devgateway.geoph.persistence.repository.category.CategoryRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author mpostelnicu
 *
 */
@Transactional
public interface GroupRepository extends CategoryRepository<Group>{

	public Group findByLabel(String label);
}
