package org.devgateway.geoph.dao;

import org.devgateway.geoph.enums.FlowTypeEnum;

/**
 * @author dbianco
 *         created on nov 01 2016.
 */
public class FlowTypeChartProjectCountDao extends ChartProjectCountDao {

    public FlowTypeChartProjectCountDao(String flowType, Long projectCount) {
        super(Long.valueOf(FlowTypeEnum.valueOf(flowType.toUpperCase()).getId()), projectCount);
    }
}
