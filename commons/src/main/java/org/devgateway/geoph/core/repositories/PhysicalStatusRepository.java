package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.PhysicalStatusDao;
import org.devgateway.geoph.model.PhysicalStatus;

import java.util.List;

/**
 * @author dbianco
 *         created on abr 12 2016.
 */
public interface PhysicalStatusRepository {

    List<PhysicalStatus> findAll();

    PhysicalStatus findByName(String name);

    PhysicalStatus findByCode(String code);

    List<PhysicalStatusDao> findFundingByPhysicalStatus(Parameters params);
}
