package org.devgateway.geoph.persistence;

import org.devgateway.geoph.persistence.repository.AppStatsRepository;
import org.devgateway.geoph.services.AppStatsService;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
