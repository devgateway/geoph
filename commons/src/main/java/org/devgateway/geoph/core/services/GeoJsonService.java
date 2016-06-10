package org.devgateway.geoph.core.services;

import org.devgateway.geoph.model.Location;
import org.devgateway.geoph.enums.LocationAdmLevelEnum;
import org.devgateway.geoph.core.request.Parameters;
import org.geojson.FeatureCollection;

import java.util.List;


/**
 * @author dbianco
 *         created on mar 11 2016.
 */
public interface GeoJsonService {

    FeatureCollection getLocationsByLevel(LocationAdmLevelEnum level);

    FeatureCollection getLocationsByParams(Parameters params);


    FeatureCollection getShapesByLevelAndDetail(LocationAdmLevelEnum level, double detail, Parameters params);

}
