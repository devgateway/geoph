package org.devgateway.geoph.services;

import org.geojson.FeatureCollection;

/**
 * @author dbianco
 *         created on mar 11 2016.
 */
public interface GeoJsonService {

    FeatureCollection getLocationsByType(String type);
}
