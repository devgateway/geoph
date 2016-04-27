package org.devgateway.geoph.services;

import org.devgateway.geoph.model.Indicator;
import org.devgateway.geoph.model.IndicatorDetail;

import java.util.List;

/**
 * @author dbianco
 *         created on abr 25 2016.
 */
public interface LayerService {

    List<Indicator> getLayerList();

    List<IndicatorDetail> getLayerData(long indicatorId);
}
