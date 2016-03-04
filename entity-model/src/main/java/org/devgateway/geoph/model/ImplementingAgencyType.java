package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author dbianco
 *         created on mar 02 2016.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity(name = "implementing_agency_type")
public class ImplementingAgencyType extends GenericPersistable implements Serializable {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    public ImplementingAgencyType() {
    }

    public ImplementingAgencyType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
