package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.AgencyResultsDao;
import org.devgateway.geoph.model.ExecutingAgency;

import java.util.List;

/**
 * @author dbianco
 *         created on may 05 2016.
 */
public interface ExecutingAgencyRepository {

    List<ExecutingAgency> findAll();

    List<AgencyResultsDao> findFundingByExecutingAgency(Parameters params);

    Integer countAll();
<<<<<<< .merge_file_a98572
=======

    ExecutingAgency findById(Long id);
>>>>>>> .merge_file_a99764
}
