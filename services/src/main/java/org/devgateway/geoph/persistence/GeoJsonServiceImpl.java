package org.devgateway.geoph.persistence;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.repository.LocationRepository;
import org.devgateway.geoph.services.GeoJsonService;
import org.devgateway.geoph.util.*;
import org.geojson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        FeatureCollection featureCollection = new FeatureCollection();
        for(LocationProperty location : locationPropertyMap.values()) {
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
        return featureCollection;
    }

    private void aggregateResults(Parameters params, int level, Map<Long, LocationProperty> locationPropertyMap) {
        List<Object> locationResults = locationRepository.findLocationsByParams(params);
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
                lp.addProjectCount((Long)objectList[1]);
                long[] transactions = new long[]{(Long)objectList[3], (Long)objectList[5], (Long)objectList[7],
                        (Long)objectList[9], (Long)objectList[11], (Long)objectList[13],
                        (Long)objectList[15], (Long)objectList[17], (Long)objectList[19],};
                long aggregationTimes = 1;
                long count = 0;
                for(long t:transactions){
                    if(t>0){
                        aggregationTimes*=t;
                        count+=t;
                    }
                }
                lp.addCommitment(PROPERTY_LOC_TARGET, transactions[0]>0?(Double)objectList[2]*transactions[0]/aggregationTimes:0);
                lp.addCommitment(PROPERTY_LOC_ACTUAL, transactions[1]>0?(Double)objectList[4]*transactions[1]/aggregationTimes:0);
                lp.addCommitment(PROPERTY_LOC_CANCELLED, transactions[2]>0?(Double)objectList[6]*transactions[2]/aggregationTimes:0);


                lp.addDisbursement(PROPERTY_LOC_TARGET, transactions[3]>0?(Double)objectList[8]*transactions[3]/aggregationTimes:0);
                lp.addDisbursement(PROPERTY_LOC_ACTUAL, transactions[4]>0?(Double)objectList[10]*transactions[4]/aggregationTimes:0);
                lp.addDisbursement(PROPERTY_LOC_CANCELLED, transactions[5]>0?(Double)objectList[12]*transactions[5]/aggregationTimes:0);


                lp.addExpenditure(PROPERTY_LOC_TARGET, transactions[6]>0?(Double)objectList[14]*transactions[6]/aggregationTimes:0);
                lp.addExpenditure(PROPERTY_LOC_ACTUAL, transactions[7]>0?(Double)objectList[16]*transactions[7]/aggregationTimes:0);
                lp.addExpenditure(PROPERTY_LOC_CANCELLED, transactions[8]>0?(Double)objectList[18]*transactions[8]/aggregationTimes:0);

                lp.addTransactionCount(count);
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
        if(level == LocationAdmLevelEnum.REGION){
            List<PostGisHelper> gisHelperList = locationRepository.getRegionShapesWithDetail(detail);
            for(PostGisHelper helper:gisHelperList){
                postGisHelperMap.put(helper.getLocationId(), helper);
            }
        }
        //TODO Implement shapes for provinces and municipalities!


        Map<Long, LocationProperty> locationPropertyMap = new HashMap<>();
        for(Location location:locations){
            locationPropertyMap.put(location.getId(), new LocationProperty(location));
        }
        aggregateResults(params, level.getLevel(), locationPropertyMap);

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
