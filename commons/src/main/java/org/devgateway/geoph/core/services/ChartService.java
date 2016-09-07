package org.devgateway.geoph.core.services;


import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.response.ChartResponse;

import java.util.Collection;

/**
 * @author dbianco
 *         created on abr 04 2016.
 */
public interface ChartService {

    Collection<ChartResponse> getFundingByFundingAgency(Parameters params);

    Collection<ChartResponse> getFundingByExecutingAgency(Parameters params);

    Collection<ChartResponse> getFundingByImplementingAgency(Parameters params);

    Collection<ChartResponse> getFundingBySector(Parameters params);

    Collection<ChartResponse> getFundingByPhysicalStatus(Parameters params);

    Collection<ChartResponse> getFundingByLocation(Parameters params);
    
}
