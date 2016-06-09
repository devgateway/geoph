package org.devgateway.geoph.services;

import org.devgateway.geoph.core.repositories.AppStatsRepository;
import org.devgateway.geoph.core.services.AppStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dbianco
 *         created on mar 28 2016.
 */
@Service
public class AppStatsServiceImpl implements AppStatsService {

    @Autowired
    AppStatsRepository appStatsRepository;

    @Override
    public String getCacheStats() {
        return appStatsRepository.getCacheStats();
    }
}
