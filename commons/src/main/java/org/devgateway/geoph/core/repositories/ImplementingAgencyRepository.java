package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.AgencyResultsDao;
import org.devgateway.geoph.model.ImplementingAgency;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 04 2016.
 */
public interface ImplementingAgencyRepository {

    List<ImplementingAgency> findAll();

<<<<<<< .merge_file_a00064
=======
    ImplementingAgency findById(Long id);

>>>>>>> .merge_file_a99820
    Integer countAll();

    List<AgencyResultsDao> findFundingByImplementingAgency(Parameters params);
}
