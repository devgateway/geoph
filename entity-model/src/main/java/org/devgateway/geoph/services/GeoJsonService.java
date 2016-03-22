package org.devgateway.geoph.services;

import org.devgateway.geoph.util.GeometryDetailLevel;
import org.devgateway.geoph.util.LocationAdmLevel;
import org.devgateway.geoph.util.Parameters;
import org.geojson.FeatureCollection;


/**
 * @author dbianco
 *         created on mar 11 2016.
 */
public interface GeoJsonService {

    public FeatureCollection getLocationsByLevel(LocationAdmLevel level);

    FeatureCollection getLocationsByParams(Parameters params);

    FeatureCollection getShapesByLevelAndDetail(LocationAdmLevel level, GeometryDetailLevel detail);

}
