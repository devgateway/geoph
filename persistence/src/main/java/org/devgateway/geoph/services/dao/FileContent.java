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
package org.devgateway.geoph.services.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * @author idobre
 * @since 11/13/14
 *
 * Entity used to store the content of uploaded files
 */

@Entity
public class FileContent extends AbstractAuditableEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Lob
    @Column(length = 10000000)
    private byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

	@Override
	public AbstractAuditableEntity getParent() {
		return null;
	}
}
