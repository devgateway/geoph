package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.FlowTypeChartProjectCountDao;
import org.devgateway.geoph.dao.FlowTypeDao;

import java.util.List;

/**
 * @author dbianco
 *         created on nov 01 2016.
 */
public interface FlowTypeChartRepository {

    List<FlowTypeDao> findFundingByFundingTypeWithTransactionStats(Parameters params);

    List<FlowTypeChartProjectCountDao> findFundingByFundingTypeWithProjectStats(Parameters params);
}
