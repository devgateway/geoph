package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.FundingAgency;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dbianco
 *         created on mar 04 2016.
 */
public interface FundingAgencyRepository extends JpaRepository<FundingAgency, Long> {

}
