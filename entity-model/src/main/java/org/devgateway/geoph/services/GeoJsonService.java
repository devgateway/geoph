package org.devgateway.geoph.services;

import org.devgateway.geoph.util.Parameters;
import org.geojson.FeatureCollection;

import java.util.Map;

/**
 * @author dbianco
 *         created on mar 11 2016.
 */
public interface GeoJsonService {

    public FeatureCollection getLocationsByLevel(int level);

    FeatureCollection getLocationsByParams(Parameters params);

}
