package org.devgateway.geoph.model.security;

import com.google.common.base.Predicate;
import org.devgateway.geoph.model.GenericPersistable;
import org.hibernate.annotations.Cache;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static com.google.common.collect.Iterables.any;
import static javax.persistence.FetchType.EAGER;
import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

/**
 * @author dbianco
 *         created on mar 01 2016.
 */
@Entity
@Cache(usage = READ_WRITE)
public class SystemUser extends GenericPersistable implements Serializable, Cloneable, UserDetails {

    @Column(unique = true)
    private String email;

    private String name;

    private String lastName;

    private String password;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    private Date created;

    private Date lastLogin;

    @ManyToMany(fetch = EAGER)
    private Collection<GrantedAuthority> authorities;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public int hashCode() {
        return Objects.hashCode(this.getId());
    }


    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final SystemUser other = (SystemUser) obj;
        return Objects.equals(this.getId(), other.getId());
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + this.getId() +
                ", email='" + email + '\'' +
                '}';
    }

    public boolean hasRoles(final List<String> roles) {
        return any(this.getAuthorities(), new Predicate<GrantedAuthority>() {
            @Override
            public boolean apply(GrantedAuthority input) {
                for (String role : roles){
                    if (role.equals(input.getAuthority())){
                        return true;
                    }
                }
                return false;
            }
        });
    }
}
