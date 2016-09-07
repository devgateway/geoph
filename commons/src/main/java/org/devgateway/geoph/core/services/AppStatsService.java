package org.devgateway.geoph.core.services;

import java.util.List;
import java.util.Map;

/**
 * @author dbianco
 *         created on mar 28 2016.
 */
public interface AppStatsService {

    List<Map<String, String>> getCacheStats();

    void clearAllCache();
}
