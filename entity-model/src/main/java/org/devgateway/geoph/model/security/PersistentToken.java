package org.devgateway.geoph.model.security;


import org.hibernate.annotations.Cache;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

/**
 * @author dbianco
 *         created on mar 01 2016.
 */
@Entity
@Cache(usage = READ_WRITE)
public class PersistentToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String series;

    private int validDays;

    private String username;

    private String tokenValue;

    private Date date;

    public PersistentToken() {
    }

    public PersistentToken(String username, String series, String tokenValue, Date date, int validDays) {
        this.series = series;
        this.username = username;
        this.tokenValue = tokenValue;
        this.date = date;
        this.validDays = validDays;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public String getSeries() {
        return series;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getValidDays() {
        return validDays;
    }

    public void setValidDays(int validDays) {
        this.validDays = validDays;
    }
}
