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

/**
 * @author dbianco
 *         created on mar 11 2016.
 */
@Service
public class GeoJsonServiceImpl implements GeoJsonService {

    @Autowired
    LocationRepository locationRepository;

    public FeatureCollection getLocationsByType(String type) {
        List<Location> locationList = locationRepository.findLocationsByType(Integer.parseInt(type));

        FeatureCollection featureCollection = new FeatureCollection();
        for(Location location : locationList) {
            Feature feature = new Feature();
            feature.setGeometry(new Point(location.getLongitude(), location.getLatitude()));
            feature.setProperty("name", location.getName());
            feature.setProperty("code", location.getCode());
            feature.setProperty("projectCount", location.getProjects().size());
            featureCollection.add(feature);
        }
        return featureCollection;
    }
}
