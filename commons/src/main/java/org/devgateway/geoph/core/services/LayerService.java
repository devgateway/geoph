package org.devgateway.geoph.core.services;

import org.devgateway.geoph.model.GeoPhotoSource;
import org.devgateway.geoph.model.Indicator;
import org.geojson.FeatureCollection;

import java.util.List;

/**
 * @author dbianco
 *         created on abr 25 2016.
 */
public interface LayerService {

    List<Indicator> getIndicatorsList();

    FeatureCollection getIndicatorsData(long indicatorId);

    List<GeoPhotoSource> getGeoPhotoSourceList();

    FeatureCollection getGeoPhotoData(long kmlId);
}
