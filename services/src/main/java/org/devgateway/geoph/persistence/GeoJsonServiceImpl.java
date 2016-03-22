package org.devgateway.geoph.persistence;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.repository.LocationRepository;
import org.devgateway.geoph.services.GeoJsonService;
import org.devgateway.geoph.util.GeometryDetailLevel;
import org.devgateway.geoph.util.LocationAdmLevel;
import org.devgateway.geoph.util.PostGisHelper;
import org.devgateway.geoph.util.Parameters;
import org.geojson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<Location> locationList = locationRepository.findLocationsByParams(params);

        FeatureCollection featureCollection = new FeatureCollection();
        for(Location location : locationList) {
            featureCollection.add(getFeature(location));
        }
        return featureCollection;
    }

    @Override
    public FeatureCollection getShapesByLevelAndDetail(LocationAdmLevel level, GeometryDetailLevel detail) {
        FeatureCollection featureCollection = new FeatureCollection();
        if(level == LocationAdmLevel.REGION){
            List<PostGisHelper> helperList = locationRepository.getRegionShapesWithDetail(detail);
            for(PostGisHelper helper:helperList){
                featureCollection.add(parseGeoJson(helper));
            }
        }
        //TODO Implement shapes for provinces and municipalities!
        return featureCollection;
    }

    private Feature getFeature(Location location) {
        Feature feature = new Feature();
        feature.setGeometry(new Point(location.getLongitude(), location.getLatitude()));
        feature.setProperty(PROPERTY_LOC_NAME, location.getName());
        feature.setProperty(PROPERTY_LOC_CODE, location.getCode());
        feature.setProperty(PROPERTY_LOC_PROJ_COUNT, location.getProjects().size());
        int trxCount = 0;
        double grants = 0, loans = 0, pmc = 0;
        boolean trxFlag = false;
        for(Project project:location.getProjects()){
            trxFlag = true;
            trxCount += project.getTransactions()!=null?project.getTransactions().size():0;
            for(Transaction trx:project.getTransactions()){
                if(trx instanceof Grant){
                    grants += trx.getAmount();
                } else if(trx instanceof Loan){
                    loans += trx.getAmount();
                } else if (trx instanceof PublicInvestment){
                    pmc += trx.getAmount();
                }
            }
        }
        if(trxFlag){
            feature.setProperty(PROPERTY_LOC_TRX_COUNT, trxCount);
            feature.setProperty(PROPERTY_LOC_GRANTS, grants);
            feature.setProperty(PROPERTY_LOC_LOANS, loans);
            feature.setProperty(PROPERTY_LOC_PMC, pmc);
        }

        return feature;
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
        feature.setProperty("regionName", helper.getRegionName());
        feature.setProperty("locationId", helper.getLocationId());
        return feature;
    }
}
