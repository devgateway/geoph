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
import org.devgateway.geoph.util.*;
import org.geojson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Indicator indicator = indicatorRepository.findOne(indicatorId);
        List<IndicatorDetail> indicatorDetails = indicatorDetailRepository.findByIndicatorId(indicatorId);
        Map<Long, PostGisHelper> postGisHelperMap = new HashMap<>();
        List<PostGisHelper> gisHelperList = null;
        if(indicator.getAdmLevel().toUpperCase().equals(LocationAdmLevelEnum.REGION.name())){
            gisHelperList = locationRepository.getRegionShapesWithDetail(GeometryDetailLevelEnum.MEDIUM.getLevel());
        }else if(indicator.getAdmLevel().toUpperCase().equals(LocationAdmLevelEnum.PROVINCE.name())) {
            gisHelperList = locationRepository.getProvinceShapesWithDetail(GeometryDetailLevelEnum.MEDIUM.getLevel());
        } else if(indicator.getAdmLevel().toUpperCase().equals(LocationAdmLevelEnum.MUNICIPALITY.name())) {
            gisHelperList = locationRepository.getMunicipalityShapesWithDetail(GeometryDetailLevelEnum.MEDIUM.getLevel());
        }
        if(gisHelperList!=null) {
            for (PostGisHelper helper : gisHelperList) {
                postGisHelperMap.put(helper.getLocationId(), helper);
            }
        }
        for(IndicatorDetail indicatorDetail:indicatorDetails){
            Feature feature;
            if(postGisHelperMap.get(indicatorDetail.getLocationId()) != null) {
                feature = parseGeoJson(postGisHelperMap.get(indicatorDetail.getLocationId()));
            } else {
                feature = new Feature();
            }
            feature.setProperty("value", indicatorDetail.getValue());
            feature.setProperty("indicatorId", indicatorDetail.getIndicatorId());
            feature.setProperty("colorScheme", indicator.getColorScheme());
            feature.setProperty("locationId", indicatorDetail.getLocationId());
            featureCollection.add(feature);
        }
        return featureCollection;
    }

    private Feature parseGeoJson(PostGisHelper helper){
        Feature feature = new Feature();
        MultiPolygon multiPolygon = new MultiPolygon();
        for(Double[][][] inner:helper.getCoordinates()){
            Polygon polygon = new Polygon();
            for(Double[][] inner2:inner){
                List<LngLatAlt> pointList = new ArrayList<>();
                for(Double[] inner3:inner2){
                    pointList.add(new LngLatAlt(inner3[0], inner3[1]));
                }
                polygon.add(pointList);
            }
            multiPolygon.add(polygon);
        }
        feature.setGeometry(multiPolygon);
        return feature;
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
