package org.devgateway.geoph.services;

import org.devgateway.geoph.model.GeoPhotoSource;
import org.devgateway.geoph.model.Indicator;
import org.devgateway.geoph.model.IndicatorDetail;
import org.devgateway.geoph.response.IndicatorResponse;
import org.geojson.FeatureCollection;

import java.util.List;

/**
 * @author dbianco
 *         created on abr 25 2016.
 */
public interface LayerService {

    Indicator saveIndicator(Indicator indicator);

    IndicatorDetail saveIndicatorDetail(IndicatorDetail indicatorDetail);

    List<Indicator> getIndicatorsList();

    FeatureCollection getIndicatorsData(long indicatorId);

    List<GeoPhotoSource> getGeoPhotoSourceList();

    FeatureCollection getGeoPhotoData(long kmlId);

    IndicatorResponse getIndicatorResponse(Long id);
}
