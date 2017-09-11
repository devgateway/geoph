/*******************************************************************************
 * Copyright (c) 2015 Development Gateway, Inc and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the MIT License (MIT)
 * which accompanies this distribution, and is available at
 * https://opensource.org/licenses/MIT
 *
 * Contributors:
 * Development Gateway - initial API and implementation
 *******************************************************************************/
/**
 *
 */
package org.devgateway.geoph.persistence.spring;


import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dbianco
 *
 */
@Configuration
public class CacheConfiguration {

    @Bean
    public CacheManager getCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<>();
        caches.add(new ConcurrentMapCache("locationsByLevel"));
        caches.add(new ConcurrentMapCache("locationsByCode"));
        caches.add(new ConcurrentMapCache("locationsById"));
        caches.add(new ConcurrentMapCache("locationsByParams"));
        caches.add(new ConcurrentMapCache("findLocationsByParentId"));
        caches.add(new ConcurrentMapCache("locationsByLevelUacsNotNull"));
        caches.add(new ConcurrentMapCache("findLocationsByParamsTypeStatus"));
        caches.add(new ConcurrentMapCache("countLocationProjectsByParams"));
        caches.add(new ConcurrentMapCache("shapesWithDetail"));
        caches.add(new ConcurrentMapCache("locationWithTransactionStats"));
        caches.add(new ConcurrentMapCache("locationWithProjectStats"));
        caches.add(new ConcurrentMapCache("getProjectStats"));
        caches.add(new ConcurrentMapCache("findProjectsByParams"));
        caches.add(new ConcurrentMapCache("findProjectMiniByParams"));
        caches.add(new ConcurrentMapCache("findProjectsById"));
        caches.add(new ConcurrentMapCache("findFundingAgencyById"));
        caches.add(new ConcurrentMapCache("findFundingAgencyByParams"));
        caches.add(new ConcurrentMapCache("findFundingAgencyByParamsWithProjectStats"));
        caches.add(new ConcurrentMapCache("findImplementingAgencyById"));
        caches.add(new ConcurrentMapCache("findImplementingAgencyByParams"));
        caches.add(new ConcurrentMapCache("findImplementingAgencyByParamsWithProjectStats"));
        caches.add(new ConcurrentMapCache("findSectorById"));
        caches.add(new ConcurrentMapCache("findSectorByParams"));
        caches.add(new ConcurrentMapCache("findSectorByParamsWithProjectStats"));
        caches.add(new ConcurrentMapCache("findPhysicalStatusById"));
        caches.add(new ConcurrentMapCache("findPhysicalStatusByParams"));
        caches.add(new ConcurrentMapCache("findPhysicalStatusByParamsWithProjectStats"));

        // cache for controllers
        caches.add(new ConcurrentMapCache("filterControllerCache"));
        caches.add(new ConcurrentMapCache("chartControllerCache"));
        caches.add(new ConcurrentMapCache("geoControllerCache"));
        caches.add(new ConcurrentMapCache("indicatorControllerCache"));
        caches.add(new ConcurrentMapCache("projectControllerCache"));
        caches.add(new ConcurrentMapCache("mapExportControllerCache"));
        caches.add(new ConcurrentMapCache("mapControllerCache"));
        cacheManager.setCaches(caches);
        return cacheManager;
    }

}
