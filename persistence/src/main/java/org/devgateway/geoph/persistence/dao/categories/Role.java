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
package org.devgateway.geoph.persistence.dao.categories;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.devgateway.geoph.dao.GenericPersistable;
import org.devgateway.geoph.persistence.dao.Labelable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;


/**
 * 
 * @author mpostelnicu
 *
 */
@Entity
@Audited
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role extends GenericPersistable implements Serializable, Comparable<Role>, Labelable {
	 
 	/**
 	 * 
 	 */
 	private static final long serialVersionUID = -6007958105920327142L;
	private String authority;
	@Column(name = "authority")
	public String getAuthority() {
		return authority;
	}
 	public Role(){
 	}
 
	public Role(String authority){
		this.authority = authority;
	}
	
	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
 	}
 	
 	@Override
	public String toString() {
		return authority;
 	}
 
	@Override
	public int compareTo(Role o) {
		return this.authority.compareTo(o.getAuthority());
 	}

	@Override
	public void setLabel(String label) {
		setAuthority(label);
		
	}

	@Override
	public String getLabel() {
		return getAuthority();
	}

}