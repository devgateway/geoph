package org.devgateway.geoph.services;

import org.devgateway.geoph.model.GeoPhotoSource;
import org.devgateway.geoph.model.Indicator;
import org.devgateway.geoph.model.IndicatorDetail;
import org.devgateway.geoph.util.GeoPhotoGeometryHelper;

import java.util.List;

/**
 * @author dbianco
 *         created on abr 25 2016.
 */
public interface LayerService {

    List<Indicator> getIndicatorsList();

    List<IndicatorDetail> getIndicatorsData(long indicatorId);

    List<GeoPhotoSource> getGeoPhotoSourceList();

    List<GeoPhotoGeometryHelper> getGeoPhotoData(long kmlId);
}
