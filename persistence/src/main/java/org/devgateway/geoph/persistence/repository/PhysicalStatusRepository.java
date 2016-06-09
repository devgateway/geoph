package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.PhysicalStatus;
import org.devgateway.geoph.util.Parameters;
import org.devgateway.geoph.util.queries.PhysicalStatusQueryHelper;

import java.util.List;

/**
 * @author dbianco
 *         created on abr 12 2016.
 */
public interface PhysicalStatusRepository {

    List<PhysicalStatus> findAll();

    PhysicalStatus findByName(String name);

    PhysicalStatus findByCode(String code);

    List<PhysicalStatusQueryHelper> findFundingByPhysicalStatus(Parameters params, int trxTypeId, int trxStatusId);
}
