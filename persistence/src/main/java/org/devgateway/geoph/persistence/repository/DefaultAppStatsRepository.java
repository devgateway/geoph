package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.core.repositories.AppStatsRepository;
import org.devgateway.geoph.persistence.spring.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dbianco
 *         created on mar 28 2016.
 */
@Service
public class DefaultAppStatsRepository implements AppStatsRepository {

    @Autowired
    private CacheConfiguration conf;

    public List<Map<String, String>> getCacheStats() {
        CacheManager cacheManager = conf.getCacheManager();
        cacheManager.getCacheNames();
        List<Map<String, String>> cacheList = new ArrayList<>();
        for(String cacheName : cacheManager.getCacheNames()) {
            Map<String, String> propsMap = new HashMap<>();
            propsMap.put("name", cacheName);
            propsMap.put("size", String.valueOf(((ConcurrentHashMap)cacheManager.getCache(cacheName).getNativeCache()).size()));
            cacheList.add(propsMap);
        }
        return cacheList;
    }

    public void clearAllCache(){
        CacheManager cacheManager = conf.getCacheManager();
        cacheManager.getCacheNames();
        for(String cacheName : cacheManager.getCacheNames()) {
            cacheManager.getCache(cacheName).clear();
        }
    }
}
