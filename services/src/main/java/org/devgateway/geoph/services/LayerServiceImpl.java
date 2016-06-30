package org.devgateway.geoph.services;

import org.devgateway.geoph.core.repositories.GeoPhotoRepository;
import org.devgateway.geoph.core.repositories.IndicatorDetailRepository;
import org.devgateway.geoph.core.repositories.IndicatorRepository;
import org.devgateway.geoph.core.repositories.LocationRepository;
import org.devgateway.geoph.core.response.IndicatorResponse;
import org.devgateway.geoph.core.services.LayerService;
import org.devgateway.geoph.dao.GeoPhotoGeometryDao;
import org.devgateway.geoph.dao.PostGisDao;
import org.devgateway.geoph.enums.GeometryDetailLevelEnum;
import org.devgateway.geoph.enums.LocationAdmLevelEnum;
import org.devgateway.geoph.model.GeoPhotoSource;
import org.devgateway.geoph.model.Indicator;
import org.devgateway.geoph.model.IndicatorDetail;
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
    private static final String GID = "gid";
    private static final String KML_ID = "kmlId";
    private static final String NAME = "name";
    private static final String SYMBOL_ID = "symbolId";
    private static final String TYPE = "type";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_PATH = "imagePath";

    @Autowired
    IndicatorRepository indicatorRepository;

    @Autowired
    IndicatorDetailRepository indicatorDetailRepository;

    @Autowired
    GeoPhotoRepository geoPhotoRepository;

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
            gisHelperList = locationRepository.getRegionShapesWithDetail(GeometryDetailLevelEnum.MEDIUM.getLevel());
        } else if (indicator.getAdmLevel().toUpperCase().equals(LocationAdmLevelEnum.PROVINCE.name())) {
            gisHelperList = locationRepository.getProvinceShapesWithDetail(GeometryDetailLevelEnum.MEDIUM.getLevel());
        } else if (indicator.getAdmLevel().toUpperCase().equals(LocationAdmLevelEnum.MUNICIPALITY.name())) {
            gisHelperList = locationRepository.getMunicipalityShapesWithDetail(GeometryDetailLevelEnum.MEDIUM.getLevel());
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
            feature.setProperty("value", indicatorDetail.getValue());
            feature.setProperty("indicatorId", indicatorDetail.getIndicatorId());
            feature.setProperty("colorScheme", indicator.getColorScheme());
            feature.setProperty("locationId", indicatorDetail.getLocationId());
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

    @Override
    public List<GeoPhotoSource> getGeoPhotoSourceList() {
        return geoPhotoRepository.findAllGeoPhotoSources();
    }

    @Override

    public FeatureCollection getGeoPhotoData(long kmlId) {
        FeatureCollection featureCollection = new FeatureCollection();
        List<GeoPhotoGeometryDao> geometryHelpers = geoPhotoRepository.getGeoPhotoGeometryByKmlId(kmlId);
        for (GeoPhotoGeometryDao geometryHelper : geometryHelpers) {
            Feature feature = new Feature();
            feature.setProperty(GID, geometryHelper.getGid());
            feature.setProperty(KML_ID, geometryHelper.getKmlId());
            feature.setProperty(NAME, geometryHelper.getName());
            feature.setProperty(SYMBOL_ID, geometryHelper.getSymbolId());
            feature.setProperty(TYPE, geometryHelper.getType());
            feature.setProperty(DESCRIPTION, geometryHelper.getDescription());
            feature.setProperty(IMAGE_PATH, geometryHelper.getImagePath());
            if (geometryHelper.getCoordinates() != null && geometryHelper.getCoordinates().length > LAT) {
                Point point = new Point(geometryHelper.getCoordinates()[LONG], geometryHelper.getCoordinates()[LAT]);
                feature.setGeometry(point);
            }
            featureCollection.add(feature);
        }
        return featureCollection;
    }
}
