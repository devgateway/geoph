package org.devgateway.geoph.core.repositories;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author dbianco
 *         created on mar 28 2016.
 */
@Transactional
public interface AppStatsRepository {

    String getCacheStats();

}
