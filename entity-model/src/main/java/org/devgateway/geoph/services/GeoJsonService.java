package org.devgateway.geoph.services;

import org.devgateway.geoph.util.LocationAdmLevelEnum;
import org.devgateway.geoph.util.Parameters;
import org.geojson.FeatureCollection;


/**
 * @author dbianco
 *         created on mar 11 2016.
 */
public interface GeoJsonService {

    public FeatureCollection getLocationsByLevel(LocationAdmLevelEnum level);

    FeatureCollection getLocationsByParams(Parameters params);

    FeatureCollection getShapesByLevelAndDetail(LocationAdmLevelEnum level, double detail, Parameters params);

}
