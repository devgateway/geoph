package org.devgateway.geoph.persistence;

import org.devgateway.geoph.util.Parameters;

import java.util.List;
import java.util.Map;

/**
 * @author dbianco
 *         created on abr 04 2016.
 */
public interface ChartService {
    List<Map<String, Object>> getFundingByFundingAgency(Parameters params);

    List<Map<String, Object>> getFundingByImplementingAgency(Parameters params);

    List<Map<String, Object>> getFundingBySector(Parameters params);

    List<Map<String, Object>> getFundingByPhysicalStatus(Parameters params);
}
