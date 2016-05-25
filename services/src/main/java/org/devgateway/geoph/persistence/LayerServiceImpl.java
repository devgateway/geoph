package org.devgateway.geoph.persistence;

import org.devgateway.geoph.model.GeoPhotoSource;
import org.devgateway.geoph.model.Indicator;
import org.devgateway.geoph.model.IndicatorDetail;
import org.devgateway.geoph.model.Location;
import org.devgateway.geoph.persistence.repository.GeoPhotoRepository;
import org.devgateway.geoph.persistence.repository.IndicatorDetailRepository;
import org.devgateway.geoph.persistence.repository.IndicatorRepository;
import org.devgateway.geoph.persistence.repository.LocationRepository;
import org.devgateway.geoph.response.IndicatorResponse;
import org.devgateway.geoph.services.LayerService;
import org.devgateway.geoph.util.GeoPhotoGeometryHelper;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.Point;
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

    @Autowired
    LocationRepository locationRepository;


    @Override
    public Indicator saveIndicator(Indicator indicator) {
        return indicatorRepository.save(indicator);
    }

    @Override
    public IndicatorDetail saveIndicatorDetail(IndicatorDetail indicatorDetail) {
        return indicatorDetailRepository.save(indicatorDetail);
    }

    @Override
    public List<Indicator> getIndicatorsList() {
        return indicatorRepository.findAll();
    }

    @Override
    public FeatureCollection getIndicatorsData(long indicatorId) {
        FeatureCollection featureCollection = new FeatureCollection();
        List<IndicatorDetail> indicatorDetails = indicatorDetailRepository.findByIndicatorId(indicatorId);
        for(IndicatorDetail indicatorDetail:indicatorDetails){
            Feature feature = new Feature();
            feature.setProperty("value", indicatorDetail.getValue());
            feature.setProperty("indicatorId", indicatorDetail.getIndicatorId());
            Location l = locationRepository.findById(indicatorDetail.getLocationId());
            if(l!=null){
                Point point = new Point(l.getLongitude(), l.getLatitude());
                feature.setGeometry(point);
            }
            featureCollection.add(feature);
        }
        return featureCollection;
    }

    @Override
    public List<GeoPhotoSource> getGeoPhotoSourceList() {
        return geoPhotoRepository.findAllGeoPhotoSources();
    }

    @Override
    public FeatureCollection getGeoPhotoData(long kmlId) {
        FeatureCollection featureCollection = new FeatureCollection();
        List<GeoPhotoGeometryHelper> geometryHelpers = geoPhotoRepository.getGeoPhotoGeometryByKmlId(kmlId);
        for(GeoPhotoGeometryHelper geometryHelper:geometryHelpers){
            Feature feature = new Feature();
            feature.setProperty("gid", geometryHelper.getGid());
            feature.setProperty("kmlId", geometryHelper.getKmlId());
            feature.setProperty("name", geometryHelper.getName());
            feature.setProperty("symbolId", geometryHelper.getSymbolId());
            feature.setProperty("type", geometryHelper.getType());
            feature.setProperty("description", geometryHelper.getDescription());
            feature.setProperty("imagePath", geometryHelper.getImagePath());
            if(geometryHelper.getCoordinates()!=null && geometryHelper.getCoordinates().length>1){
                Point point = new Point(geometryHelper.getCoordinates()[0], geometryHelper.getCoordinates()[1]);
                feature.setGeometry(point);
            }
            featureCollection.add(feature);
        }
        return featureCollection;
    }

    @Override
    public IndicatorResponse getIndicatorResponse(Long id) {
        IndicatorResponse response = new IndicatorResponse(indicatorRepository.findOne(id));
        response.addDetails(indicatorDetailRepository.findByIndicatorId(id));
        return response;
    }
}
