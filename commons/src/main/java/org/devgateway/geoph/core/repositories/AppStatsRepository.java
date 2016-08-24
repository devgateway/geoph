package org.devgateway.geoph.core.repositories;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author dbianco
 *         created on mar 28 2016.
 */
@Transactional
public interface AppStatsRepository {

    List<Map<String, String>> getCacheStats();

}
