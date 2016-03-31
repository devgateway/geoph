package org.devgateway.geoph.model.security;

import org.devgateway.geoph.model.GenericPersistable;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.io.Serializable;

import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY;

/**
 * @author dbianco
 *         created on mar 01 2016.
 */
@Entity
@Cache(usage = READ_ONLY)
public class GrantedAuthority extends GenericPersistable implements org.springframework.security.core.GrantedAuthority, Serializable {

    public static final String ROLE_PREFIX = "ROLE_";

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String label;

    public GrantedAuthority() {

    }

    public GrantedAuthority(String name, String label) {
        this.name = name;
        this.label = label;
    }


    @Override
    public String getAuthority() {
        return ROLE_PREFIX + label.toUpperCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return ROLE_PREFIX + label;
    }
}
