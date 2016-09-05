package org.devgateway.geoph.core.services;

import org.devgateway.geoph.core.request.Parameters;
import org.geojson.FeatureCollection;

/**
 * Created by sebas on 8/30/2016.
 */
public interface GeoPhotosService {

    FeatureCollection getGeoPhotoData(Parameters parameters);
}
