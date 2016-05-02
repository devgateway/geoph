package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.persistence.spring.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * @author dbianco
 *         created on mar 28 2016.
 */
@Service
public class DefaultAppStatsRepository implements AppStatsRepository{

    @Autowired
    private CacheConfiguration conf;

    public String getCacheStats() {
        CacheManager cacheManager = conf.getCacheManager();
        cacheManager.getCacheNames();
        return cacheManager.getCache("org.devgateway.geoph.model.Location").toString();
    }
}
