package org.devgateway.geoph.persistence.repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author dbianco
 *         created on mar 28 2016.
 */
@Transactional
public interface AppStatsRepository {

    public String getCacheStats();

}
