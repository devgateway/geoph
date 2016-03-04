package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author dbianco
 *         created on mar 02 2016.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@DiscriminatorValue(value="funding_agency")
public class FundingAgency extends Agency implements Serializable {

    @Column(name = "funding_code")
    private String fundingCode;

    public FundingAgency() {
    }

    public FundingAgency(String name, String code, String fundingCode) {
        super(name, code);
        this.fundingCode = fundingCode;
    }

    public String getFundingCode() {
        return fundingCode;
    }

    public void setFundingCode(String fundingCode) {
        this.fundingCode = fundingCode;
    }
}
