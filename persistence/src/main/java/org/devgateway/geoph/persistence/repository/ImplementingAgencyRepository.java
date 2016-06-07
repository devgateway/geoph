package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.ImplementingAgency;
import org.devgateway.geoph.util.Parameters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 04 2016.
 */
public interface ImplementingAgencyRepository  {

    List<ImplementingAgency> findAll();

    Integer count();

    List<Object> findFundingByImplementingAgency(Parameters params);
}
