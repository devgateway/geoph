package org.devgateway.geoph.services;

import org.devgateway.geoph.model.Agency;
import org.devgateway.geoph.model.FundingType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * @author dbianco
 *         created on feb 29 2016.
 */
public interface FilterService {

    List<Agency> findAllImpAgencies();

    Page<FundingType> findAllFundingTypes(Pageable pageable);
}
