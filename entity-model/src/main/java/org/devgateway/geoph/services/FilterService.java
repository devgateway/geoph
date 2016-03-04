package org.devgateway.geoph.services;

import org.devgateway.geoph.model.FlowType;
import org.devgateway.geoph.model.FundingAgency;
import org.devgateway.geoph.model.ImplementingAgency;
import org.devgateway.geoph.model.Sector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * @author dbianco
 *         created on feb 29 2016.
 */
public interface FilterService {

    List<ImplementingAgency> findAllImpAgencies();

    List<FundingAgency> findAllFundingAgencies();

    List<FlowType> findAllFlowTypes();

    List<Sector> findAllSectors();
}
