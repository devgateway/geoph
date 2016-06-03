package org.devgateway.geoph.persistence;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.repository.LocationRepository;
import org.devgateway.geoph.services.GeoJsonService;
import org.devgateway.geoph.util.*;
import org.geojson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.devgateway.geoph.util.Constants.*;

/**
 * @author dbianco
 *         created on mar 11 2016.
 */
@Service
public class GeoJsonServiceImpl implements GeoJsonService {

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
        List<Location> locationList = new ArrayList<>();
        List<Object> locationResults = locationRepository.findLocationsByParams(params, 0, 0);
        for(Object o:locationResults) {
            Location l = (Location) o;
            Set<Project> projects = l.getProjects();
            for(Project p : projects){
                p.getTransactions();
            }
            locationList.add(l);
        }
        return locationList;
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
                List<Object> locationResults = locationRepository.findLocationsByParams(params, tt.getId(), ts.getId());
                for(Object o:locationResults){
                    Object[] objectList = ((Object[])o);
                    Location l = (Location)objectList[0];
                    LocationProperty lp = null;
                    if(level==1){
                        lp = locationPropertyMap.get(l.getRegionId());
                    } else if(level==2){
                        lp = l.getProvinceId()!=null ? locationPropertyMap.get(l.getProvinceId()) : null;
                    } else if(level==3){
                        lp = locationPropertyMap.get(l.getId());
                    }

                    if(lp!=null) {
                        if(objectList[2]!=null) {
                            if (tt.compareTo(TransactionTypeEnum.COMMITMENT) == 0) {
                                if(ts.compareTo(TransactionStatusEnum.TARGET) == 0) {
                                    lp.addCommitment(PROPERTY_LOC_TARGET, (Double) objectList[1]);
                                } else if(ts.compareTo(TransactionStatusEnum.ACTUAL) == 0) {
                                    lp.addCommitment(PROPERTY_LOC_ACTUAL, (Double) objectList[1]);
                                } else if(ts.compareTo(TransactionStatusEnum.CANCELLED) == 0) {
                                    lp.addCommitment(PROPERTY_LOC_CANCELLED, (Double) objectList[1]);
                                }
                            }else if (tt.compareTo(TransactionTypeEnum.DISBURSEMENT) == 0) {
                                if(ts.compareTo(TransactionStatusEnum.TARGET) == 0) {
                                    lp.addDisbursement(PROPERTY_LOC_TARGET, (Double) objectList[1]);
                                } else if(ts.compareTo(TransactionStatusEnum.ACTUAL) == 0) {
                                    lp.addDisbursement(PROPERTY_LOC_ACTUAL, (Double) objectList[1]);
                                } else if(ts.compareTo(TransactionStatusEnum.CANCELLED) == 0) {
                                    lp.addDisbursement(PROPERTY_LOC_CANCELLED, (Double) objectList[1]);
                                }
                            }else if (tt.compareTo(TransactionTypeEnum.EXPENDITURE) == 0) {
                                if(ts.compareTo(TransactionStatusEnum.TARGET) == 0) {
                                    lp.addExpenditure(PROPERTY_LOC_TARGET, (Double) objectList[1]);
                                } else if(ts.compareTo(TransactionStatusEnum.ACTUAL) == 0) {
                                    lp.addExpenditure(PROPERTY_LOC_ACTUAL, (Double) objectList[1]);
                                } else if(ts.compareTo(TransactionStatusEnum.CANCELLED) == 0) {
                                    lp.addExpenditure(PROPERTY_LOC_CANCELLED, (Double) objectList[1]);
                                }
                            }
                        }
                        lp.addTransactionCount((Long)objectList[2]);
                        lp.setActualPhysicalProgressAverage((Double)objectList[3]/(Long)objectList[4]);
                        lp.setTargetPhysicalProgressAverage((Double)objectList[5]/(Long)objectList[6]);
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



    private Map<Long, Project> getProjectsFromLocations(Location location) {
        Map<Long, Project> ret = new HashMap<>();
        for(Project project:location.getProjects()){
            ret.put(project.getId(), project);
        }
        if(location.getItems()!=null){
            for(Location innerLoc:location.getItems()){
                ret.putAll(getProjectsFromLocations(innerLoc));
            }
        }
        return ret;
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
