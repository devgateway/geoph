package org.devgateway.geoph.persistence;

import org.devgateway.geoph.model.Location;
import org.devgateway.geoph.persistence.repository.LocationRepository;
import org.devgateway.geoph.services.GeoJsonService;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public FeatureCollection getLocationsByLevel(int level) {
        List<Location> locationList = locationRepository.findLocationsByLevel(level);

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

    public FeatureCollection getLocationsByParams(Map<String, String[]> params) {
        int level = Integer.parseInt(params.get(PROPERTY_LOC_LEVEL)[0]);
        List<Location> locationList = locationRepository.findLocationsByParams(params);

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
}
