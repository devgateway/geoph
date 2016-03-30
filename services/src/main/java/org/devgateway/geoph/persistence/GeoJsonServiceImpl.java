package org.devgateway.geoph.persistence;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.repository.LocationRepository;
import org.devgateway.geoph.services.GeoJsonService;
import org.devgateway.geoph.util.*;
import org.geojson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
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

    public FeatureCollection getLocationsByLevel(LocationAdmLevel level) {
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
        int level = LocationAdmLevel.MUNICIPALITY.getLevel();
        for(int paramLevel:params.getLocationLevels()){
            if(paramLevel<level){
                level=paramLevel;
            }
        }
        List<Location> locations = locationRepository.findLocationsByLevel(level);
        Map<Long, LocationProperty> locationPropertyMap = new HashMap<>();
        for(Location location:locations){
            locationPropertyMap.put(location.getId(), new LocationProperty(location));
        }

        List<Object> locationResults = locationRepository.findLocationsByParams(params);
        for(Object o:locationResults){
            Location l = (Location)((Object[])o)[0];
            LocationProperty lp = null;
            if(level==1){
                lp = locationPropertyMap.get(l.getRegionId());
            } else if(level==2){
                lp = locationPropertyMap.get(l.getProvinceId());
            } else if(level==3){
                lp = locationPropertyMap.get(l.getId());
            }
            lp.addProjectCount((Long)((Object[])o)[1]);
            lp.addTransactionCount((Long)((Object[])o)[2]);
            lp.addLoan((Double)((Object[])o)[3]);
            lp.addGrant((Double)((Object[])o)[4]);
            lp.addPmc((Double)((Object[])o)[5]);
        }

        FeatureCollection featureCollection = new FeatureCollection();
        for(LocationProperty location : locationPropertyMap.values()) {
            Feature feature = new Feature();
            feature.setGeometry(new Point(location.getLongitude(), location.getLatitude()));
            feature.setProperty(PROPERTY_LOC_ID, location.getId());
            feature.setProperty(PROPERTY_LOC_NAME, location.getName());
            feature.setProperty(PROPERTY_LOC_CODE, location.getCode());
            feature.setProperty(PROPERTY_LOC_PROJ_COUNT, location.getProjectCount());
            feature.setProperty(PROPERTY_LOC_TRX_COUNT, location.getTransactionCount());
            feature.setProperty(PROPERTY_LOC_GRANTS, location.getGrant());
            feature.setProperty(PROPERTY_LOC_LOANS, location.getLoan());
            feature.setProperty(PROPERTY_LOC_PMC, location.getPmc());
            featureCollection.add(feature);
        }
        return featureCollection;
    }

    @Override
    public FeatureCollection getShapesByLevelAndDetail(LocationAdmLevel level, GeometryDetailLevel detail) {
        FeatureCollection featureCollection = new FeatureCollection();
        List<Location> locationList = locationRepository.findLocationsByLevel(level.getLevel());
        Map<Long, Location> locationMap = Maps.uniqueIndex(locationList, new Function<Location, Long>() {
            @Nullable
            @Override
            public Long apply(@Nullable Location input) {
                return input.getId();
            }
        });
        if(level == LocationAdmLevel.REGION){
            List<PostGisHelper> helperList = locationRepository.getRegionShapesWithDetail(detail);
            for(PostGisHelper helper:helperList){
                Feature feature = parseGeoJson(helper);
                setFeatureProperties(feature, locationMap.get(helper.getLocationId()), false);
                featureCollection.add(feature);
            }
        }
        //TODO Implement shapes for provinces and municipalities!
        return featureCollection;
    }

    private void setFeatureProperties(Feature feature, Location location, boolean isSectorAggregationNeeded) {
        Map<Long, Project> projectMap = new HashMap<>();
        projectMap.putAll(getProjectsFromLocations(location));
        feature.setProperty(PROPERTY_LOC_ID, location.getId());
        feature.setProperty(PROPERTY_LOC_NAME, location.getName());
        feature.setProperty(PROPERTY_LOC_CODE, location.getCode());
        feature.setProperty(PROPERTY_LOC_PROJ_COUNT, projectMap.size());

        Map<String, SectorAggregation> sectorAggregationMap = new HashMap<>();

        int trxCount = 0;
        double grants = 0, loans = 0, pmc = 0;
        boolean trxFlag = false;

        for(Project project:projectMap.values()){
            double projectGrants = 0, projectLoans = 0, projectPmc = 0;
            trxFlag = true;
            trxCount += project.getTransactions()!=null?project.getTransactions().size():0;
            for(Transaction trx:project.getTransactions()){
                if(trx instanceof Grant){
                    projectGrants += trx.getAmount();
                } else if(trx instanceof Loan){
                    projectLoans += trx.getAmount();
                } else if (trx instanceof PublicInvestment){
                    projectPmc += trx.getAmount();
                }
            }
            if(isSectorAggregationNeeded){
                for(Sector sector:project.getSectors()){
                    sectorAggregationMap.put(sector.getName() + KEY_SEPARATOR + sector.getCode()
                            , new SectorAggregation(sector.getId(), projectLoans, projectGrants, projectPmc));
                }
            }
            grants += projectGrants;
            loans += projectLoans;
            pmc += projectPmc;
        }
        if(isSectorAggregationNeeded){
            feature.setProperty(PROPERTY_LOC_SECTOR_AGGREGATION, sectorAggregationMap);
        }
        if(trxFlag){
            feature.setProperty(PROPERTY_LOC_TRX_COUNT, trxCount);
            feature.setProperty(PROPERTY_LOC_GRANTS, grants);
            feature.setProperty(PROPERTY_LOC_LOANS, loans);
            feature.setProperty(PROPERTY_LOC_PMC, pmc);
        }
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
