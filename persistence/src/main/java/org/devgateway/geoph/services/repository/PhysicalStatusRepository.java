package org.devgateway.geoph.services.repository;

import org.devgateway.geoph.model.PhysicalStatus;
import org.devgateway.geoph.util.Parameters;

import java.util.List;

/**
 * @author dbianco
 *         created on abr 12 2016.
 */
public interface PhysicalStatusRepository {

    List<PhysicalStatus> findAll();

    PhysicalStatus findByName(String name);

    PhysicalStatus findByCode(String code);

    List<Object> findFundingByPhysicalStatus(Parameters params);
}
