package org.devgateway.geoph.persistence;

import org.devgateway.geoph.model.GeoPhotoSource;
import org.devgateway.geoph.model.Indicator;
import org.devgateway.geoph.model.IndicatorDetail;
import org.devgateway.geoph.persistence.repository.GeoPhotoRepository;
import org.devgateway.geoph.persistence.repository.IndicatorDetailRepository;
import org.devgateway.geoph.persistence.repository.IndicatorRepository;
import org.devgateway.geoph.services.LayerService;
import org.devgateway.geoph.util.GeoPhotoGeometryHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dbianco
 *         created on abr 25 2016.
 */
@Service
public class LayerServiceImpl implements LayerService{

    @Autowired
    IndicatorRepository indicatorRepository;

    @Autowired
    IndicatorDetailRepository indicatorDetailRepository;

    @Autowired
    GeoPhotoRepository geoPhotoRepository;


    @Override
    public List<Indicator> getIndicatorsList() {
        return indicatorRepository.findAll();
    }

    @Override
    public List<IndicatorDetail> getIndicatorsData(long indicatorId) {
        return indicatorDetailRepository.findByIndicatorId(indicatorId);
    }

    @Override
    public List<GeoPhotoSource> getGeoPhotoSourceList() {
        return geoPhotoRepository.findAllGeoPhotoSources();
    }

    @Override
    public List<GeoPhotoGeometryHelper> getGeoPhotoData(long kmlId) {
        return geoPhotoRepository.getGeoPhotoGeometryByKmlId(kmlId);
    }
}
