package org.devgateway.geoph.persistence;

import org.devgateway.geoph.response.ChartResponse;
import org.devgateway.geoph.util.Parameters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author dbianco
 *         created on abr 04 2016.
 */
public interface ChartService {
    Collection<ChartResponse> getFundingByFundingAgency(Parameters params);

    Collection<ChartResponse> getFundingByImplementingAgency(Parameters params);

    Collection<ChartResponse> getFundingBySector(Parameters params);

    Collection<ChartResponse> getFundingByPhysicalStatus(Parameters params);
}
