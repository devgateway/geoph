package org.devgateway.geoph.services;

import org.devgateway.geoph.util.Parameters;

import java.util.List;
import java.util.Map;

/**
 * @author dbianco
 *         created on abr 04 2016.
 */
public interface ChartService {
    List<Map<String, String>> getFundingByFundingAgency(Parameters params);

    List<Map<String,String>> getFundingByImplementingAgency(Parameters params);

    List<Map<String,String>> getFundingBySector(Parameters params);
}
