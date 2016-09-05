package org.devgateway.geoph.core.services;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.enums.GeometryDetail;
import org.devgateway.geoph.enums.LocationAdmLevelEnum;
import org.geojson.FeatureCollection;


/**
 * @author dbianco
 *         created on mar 11 2016.
 */
public interface GeoJsonService {

    FeatureCollection getLocationsByLevel(LocationAdmLevelEnum level);

    FeatureCollection getGeoProjects(LocationAdmLevelEnum level, Parameters params);

    FeatureCollection getGeoFunding(LocationAdmLevelEnum level, GeometryDetail detail, Parameters params);

    FeatureCollection getPhysicalProgressAverageByParamsAndDetail(Parameters params, Double detail);
}
