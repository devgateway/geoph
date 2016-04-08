package org.devgateway.geoph.services;

import org.devgateway.geoph.model.security.PersistentToken;

/**
 * @author dbianco
 *         created on mar 31 2016.
 */
public interface TokenService {

    String encodeToken(String[] tokens);

    String generateSeriesData();

    String generateTokenData();

    int getDefaultValidDays();

    void savePersistentToken(PersistentToken token);

    PersistentToken findBySeries(String presentedSeries);
}
