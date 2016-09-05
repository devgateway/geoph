package org.devgateway.geoph.services;

import org.devgateway.geoph.core.repositories.IndicatorDetailRepository;
import org.devgateway.geoph.core.repositories.IndicatorRepository;
import org.devgateway.geoph.core.repositories.LocationRepository;
import org.devgateway.geoph.core.response.IndicatorResponse;
import org.devgateway.geoph.core.services.LayerService;
import org.devgateway.geoph.dao.GeoPhotoGeometryDao;
import org.devgateway.geoph.dao.PostGisDao;
import org.devgateway.geoph.enums.LocationAdmLevelEnum;
import org.devgateway.geoph.model.Indicator;
import org.devgateway.geoph.model.IndicatorDetail;
import org.devgateway.geoph.services.util.FeatureHelper;
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
public class LayerServiceImpl implements LayerService {

    private static final int LONG = 0;
    private static final int LAT = 1;


    @Autowired
    IndicatorRepository indicatorRepository;

    @Autowired
    IndicatorDetailRepository indicatorDetailRepository;

    @Autowired
    LocationRepository locationRepository;

    @Override
    public List<Indicator> getIndicatorsList() {
        return indicatorRepository.findAll();
    }

    @Override
    public IndicatorResponse getIndicatorById(Long id) {
        IndicatorResponse response = new IndicatorResponse(indicatorRepository.findOne(id));
        if(response.getId()!=null){
            response.addDetails(indicatorDetailRepository.findByIndicatorId(id));
        }
        return response;
    }

    @Override
    public void deleteIndicator(Long id) {
        indicatorDetailRepository.delete(indicatorDetailRepository.findByIndicatorId(id));
        indicatorRepository.delete(id);
    }

    @Override
    public FeatureCollection getIndicatorsData(long indicatorId) {
        FeatureCollection featureCollection = new FeatureCollection();
        Indicator indicator = indicatorRepository.findOne(indicatorId);
        List<IndicatorDetail> indicatorDetails = indicatorDetailRepository.findByIndicatorId(indicatorId);
        Map<Long, PostGisDao> postGisHelperMap = new HashMap<>();
        List<PostGisDao> gisHelperList = null;
        if (indicator.getAdmLevel().toUpperCase().equals(LocationAdmLevelEnum.REGION.name())) {
            //gisHelperList = locationRepository.getRegionShapesWithDetail(GeometryDetail.MEDIUM.getValue());
        } else if (indicator.getAdmLevel().toUpperCase().equals(LocationAdmLevelEnum.PROVINCE.name())) {
            //gisHelperList = locationRepository.getProvinceShapesWithDetail(GeometryDetail.MEDIUM.getValue());
        } else if (indicator.getAdmLevel().toUpperCase().equals(LocationAdmLevelEnum.MUNICIPALITY.name())) {
            //gisHelperList = locationRepository.getMunicipalityShapesWithDetail(GeometryDetail.MEDIUM.getValue());
        }
        if (gisHelperList != null) {
            for (PostGisDao helper : gisHelperList) {
                postGisHelperMap.put(helper.getLocationId(), helper);
            }
        }
        for (IndicatorDetail indicatorDetail : indicatorDetails) {
            Feature feature;
            if (postGisHelperMap.get(indicatorDetail.getLocationId()) != null) {
                feature = parseGeoJson(postGisHelperMap.get(indicatorDetail.getLocationId()));
            } else {
                feature = new Feature();
            }
            String name = null;
            if(postGisHelperMap.get(indicatorDetail.getLocationId())!=null){
                name = postGisHelperMap.get(indicatorDetail.getLocationId()).getName();
            }
            FeatureHelper.setIndicatorDetailFeature(feature, indicatorDetail, name, indicator.getColorScheme());
            featureCollection.add(feature);
        }
        return featureCollection;
    }

    private Feature parseGeoJson(PostGisDao helper) {
        Feature feature = new Feature();
        MultiPolygon multiPolygon = new MultiPolygon();
        for (Double[][][] inner : helper.getCoordinates()) {
            Polygon polygon = new Polygon();
            for (Double[][] inner2 : inner) {
                List<LngLatAlt> pointList = new ArrayList<>();
                for (Double[] inner3 : inner2) {
                    pointList.add(new LngLatAlt(inner3[0], inner3[1]));
                }
                polygon.add(pointList);
            }
            multiPolygon.add(polygon);
        }
        feature.setGeometry(multiPolygon);
        return feature;
    }

    private FeatureCollection getGeoPhotoDataFromRepo(List<GeoPhotoGeometryDao> geometryHelpers) {
        FeatureCollection featureCollection = new FeatureCollection();
        for (GeoPhotoGeometryDao geometryHelper : geometryHelpers) {
            Feature feature = new Feature();
            FeatureHelper.setGeoPhotoGeometryFeature(feature, geometryHelper);
            if (geometryHelper.getCoordinates() != null && geometryHelper.getCoordinates().length > LAT) {
                Point point = new Point(geometryHelper.getCoordinates()[LONG], geometryHelper.getCoordinates()[LAT]);
                feature.setGeometry(point);
            }
            featureCollection.add(feature);
        }
        return featureCollection;
    }
}
