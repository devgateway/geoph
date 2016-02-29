package org.devgateway.geoph.services;

import org.devgateway.geoph.model.Agency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @author dbianco
 *         created on feb 29 2016.
 */
public interface FilterService {

    Page<Agency> findAllAgencies(Pageable pageable);
}
