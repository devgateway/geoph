package org.devgateway.geoph.services;

import org.geojson.FeatureCollection;

import java.util.Map;

/**
 * @author dbianco
 *         created on mar 11 2016.
 */
public interface GeoJsonService {

    public FeatureCollection getLocationsByLevel(int level);

    FeatureCollection getLocationsByParams(Map<String, String[]> params);

}
