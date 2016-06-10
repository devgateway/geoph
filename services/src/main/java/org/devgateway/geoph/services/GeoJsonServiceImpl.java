package org.devgateway.geoph.services;

import org.devgateway.geoph.core.repositories.LocationRepository;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.services.GeoJsonService;
import org.devgateway.geoph.dao.LocationProperty;
import org.devgateway.geoph.dao.LocationResultsQueryHelper;
import org.devgateway.geoph.dao.PostGisHelper;
import org.devgateway.geoph.enums.LocationAdmLevelEnum;
import org.devgateway.geoph.enums.TransactionStatusEnum;
import org.devgateway.geoph.enums.TransactionTypeEnum;
import org.devgateway.geoph.model.Location;
import org.devgateway.geoph.model.Project;
import org.geojson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.devgateway.geoph.core.constants.Constants.*;

/**
 * @author dbianco
 *         created on mar 11 2016.
 */
@Service
public class GeoJsonServiceImpl implements GeoJsonService {

    public static final int REGION_LEVEL = 1;
    public static final int PROVINCE_LEVEL = 2;
    public static final int MUNICIPALITY_LEVEL = 3;
    @Autowired
    LocationRepository locationRepository;

    public FeatureCollection getLocationsByLevel(LocationAdmLevelEnum level) {
        List<Location> locationList = locationRepository.findLocationsByLevel(level.getLevel());

        FeatureCollection featureCollection = new FeatureCollection();
        for(Location location : locationList) {
            Feature feature = new Feature();
            feature.setGeometry(new Point(location.getLongitude(), location.getLatitude()));
            feature.setProperty(PROPERTY_LOC_NAME, location.getName());
            feature.setProperty(PROPERTY_LOC_CODE, location.getCode());
            feature.setProperty(PROPERTY_LOC_PROJ_COUNT, location.getProjects().size());
            featureCollection.add(feature);
        }
        return featureCollection;
    }

    public FeatureCollection getLocationsByParams(Parameters params) {
        int level = getUpperLevel(params);
        List<Location> locations = locationRepository.findLocationsByLevel(level);
        Map<Long, LocationProperty> locationPropertyMap = new HashMap<>();
        for(Location location:locations){
            locationPropertyMap.put(location.getId(), new LocationProperty(location));
        }

        aggregateResults(params, level, locationPropertyMap);
        addProjectCount(params, level, locationPropertyMap);

        FeatureCollection featureCollection = new FeatureCollection();
        for(LocationProperty location : locationPropertyMap.values()) {
            if(location.getProjectCount()>0) {
                Feature feature = new Feature();
                feature.setGeometry(new Point(location.getLongitude(), location.getLatitude()));
                feature.setProperty(PROPERTY_LOC_ID, location.getId());
                feature.setProperty(PROPERTY_LOC_NAME, location.getName());
                feature.setProperty(PROPERTY_LOC_CODE, location.getCode());
                feature.setProperty(PROPERTY_LOC_PROJ_COUNT, location.getProjectCount());
                feature.setProperty(PROPERTY_LOC_TRX_COUNT, location.getTransactionCount());
                feature.setProperty(PROPERTY_LOC_COMMITMENTS, location.getCommitments());
                feature.setProperty(PROPERTY_LOC_DISBURSEMENTS, location.getDisbursements());
                feature.setProperty(PROPERTY_LOC_EXPENDITURES, location.getExpenditures());
                featureCollection.add(feature);
            }
        }
        return featureCollection;
    }

    public List<Location> getLocationsForExport(Parameters params){
        return locationRepository.findLocationsByParams(params);
    }

    private void addProjectCount(Parameters params, int level, Map<Long, LocationProperty> locationPropertyMap) {
        List<Object> locationResults = locationRepository.countLocationProjectsByParams(params);
        for(Object o:locationResults) {
            Object[] objectList = ((Object[]) o);
            Location l = (Location) objectList[0];
            LocationProperty lp = null;
            if (level == 1) {
                lp = locationPropertyMap.get(l.getRegionId());
            } else if (level == 2) {
                lp = l.getProvinceId() != null ? locationPropertyMap.get(l.getProvinceId()) : null;
            } else if (level == 3) {
                lp = locationPropertyMap.get(l.getId());
            }

            if (lp != null) {
                lp.addProjectCount((Long) objectList[1]);
            }
        }
    }

    private void aggregateResults(Parameters params, int level, Map<Long, LocationProperty> locationPropertyMap) {

        for(TransactionTypeEnum tt:TransactionTypeEnum.values()){
            for(TransactionStatusEnum ts:TransactionStatusEnum.values()){
                List<LocationResultsQueryHelper> locationResults = locationRepository.findLocationsByParamsTypeStatus(params, tt.getId(), ts.getId());
                for(LocationResultsQueryHelper locHelper:locationResults){
                    LocationProperty lp = null;
                    if(level== REGION_LEVEL){
                        lp = locationPropertyMap.get(locHelper.getLocation().getRegionId());
                    } else if(level== PROVINCE_LEVEL){
                        lp = locHelper.getLocation().getProvinceId()!=null ? locationPropertyMap.get(locHelper.getLocation().getProvinceId()) : null;
                    } else if(level== MUNICIPALITY_LEVEL){
                        lp = locationPropertyMap.get(locHelper.getLocation().getId());
                    }

                    if(lp!=null) {
                        if (tt.compareTo(TransactionTypeEnum.COMMITMENT) == 0) {
                            if(ts.compareTo(TransactionStatusEnum.TARGET) == 0) {
                                lp.addCommitment(PROPERTY_LOC_TARGET, locHelper.getTrxAmount());
                            } else if(ts.compareTo(TransactionStatusEnum.ACTUAL) == 0) {
                                lp.addCommitment(PROPERTY_LOC_ACTUAL, locHelper.getTrxAmount());
                            } else if(ts.compareTo(TransactionStatusEnum.CANCELLED) == 0) {
                                lp.addCommitment(PROPERTY_LOC_CANCELLED, locHelper.getTrxAmount());
                            }
                        }else if (tt.compareTo(TransactionTypeEnum.DISBURSEMENT) == 0) {
                            if(ts.compareTo(TransactionStatusEnum.TARGET) == 0) {
                                lp.addDisbursement(PROPERTY_LOC_TARGET, locHelper.getTrxAmount());
                            } else if(ts.compareTo(TransactionStatusEnum.ACTUAL) == 0) {
                                lp.addDisbursement(PROPERTY_LOC_ACTUAL, locHelper.getTrxAmount());
                            } else if(ts.compareTo(TransactionStatusEnum.CANCELLED) == 0) {
                                lp.addDisbursement(PROPERTY_LOC_CANCELLED, locHelper.getTrxAmount());
                            }
                        }else if (tt.compareTo(TransactionTypeEnum.EXPENDITURE) == 0) {
                            if(ts.compareTo(TransactionStatusEnum.TARGET) == 0) {
                                lp.addExpenditure(PROPERTY_LOC_TARGET, locHelper.getTrxAmount());
                            } else if(ts.compareTo(TransactionStatusEnum.ACTUAL) == 0) {
                                lp.addExpenditure(PROPERTY_LOC_ACTUAL, locHelper.getTrxAmount());
                            } else if(ts.compareTo(TransactionStatusEnum.CANCELLED) == 0) {
                                lp.addExpenditure(PROPERTY_LOC_CANCELLED, locHelper.getTrxAmount());
                            }
                        }

                        lp.addTransactionCount(locHelper.getTrxCount());
                        lp.setActualPhysicalProgressAverage(locHelper.getActualPhysicalProgressAverage());
                        lp.setTargetPhysicalProgressAverage(locHelper.getTargetPhysicalProgressAverage());

                    }
                }
            }
        }
    }

    private int getUpperLevel(Parameters params) {
        int level = LocationAdmLevelEnum.MUNICIPALITY.getLevel();
        for(int paramLevel:params.getLocationLevels()){
            if(paramLevel<level){
                level=paramLevel;
            }
        }
        return level;
    }

    @Override
    public FeatureCollection getShapesByLevelAndDetail(LocationAdmLevelEnum level, double detail, Parameters params) {
        List<Location> locations = locationRepository.findLocationsByLevel(level.getLevel());

        Map<Long, PostGisHelper> postGisHelperMap = new HashMap<>();
        List<PostGisHelper> gisHelperList = null;
        if(level == LocationAdmLevelEnum.REGION){
            gisHelperList = locationRepository.getRegionShapesWithDetail(detail);
        } else if(level == LocationAdmLevelEnum.PROVINCE) {
            gisHelperList = locationRepository.getProvinceShapesWithDetail(detail);
        } else if(level == LocationAdmLevelEnum.MUNICIPALITY) {
            gisHelperList = locationRepository.getMunicipalityShapesWithDetail(detail);
        }
        if(gisHelperList!=null) {
            for (PostGisHelper helper : gisHelperList) {
                postGisHelperMap.put(helper.getLocationId(), helper);
            }
        }
        Map<Long, LocationProperty> locationPropertyMap = new HashMap<>();
        for(Location location:locations){
            locationPropertyMap.put(location.getId(), new LocationProperty(location));
        }
        aggregateResults(params, level.getLevel(), locationPropertyMap);
        addProjectCount(params, level.getLevel(), locationPropertyMap);

        FeatureCollection featureCollection = new FeatureCollection();
        for(LocationProperty location : locationPropertyMap.values()) {
            Feature feature;
            if(postGisHelperMap.get(location.getId()) != null) {
                feature = parseGeoJson(postGisHelperMap.get(location.getId()));
            } else {
                feature = new Feature();
            }
            feature.setProperty(PROPERTY_LOC_ID, location.getId());
            feature.setProperty(PROPERTY_LOC_NAME, location.getName());
            feature.setProperty(PROPERTY_LOC_CODE, location.getCode());
            feature.setProperty(PROPERTY_LOC_PROJ_COUNT, location.getProjectCount());
            feature.setProperty(PROPERTY_LOC_TRX_COUNT, location.getTransactionCount());
            feature.setProperty(PROPERTY_LOC_COMMITMENTS, location.getCommitments());
            feature.setProperty(PROPERTY_LOC_DISBURSEMENTS, location.getDisbursements());
            feature.setProperty(PROPERTY_LOC_EXPENDITURES, location.getExpenditures());
            feature.setProperty(PROPERTY_LOC_ACTUAL_PHY_AVG, location.getActualPhysicalProgressAverage());
            feature.setProperty(PROPERTY_LOC_TARGET_PHY_AVG, location.getTargetPhysicalProgressAverage());
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
}
