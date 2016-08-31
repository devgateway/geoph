package org.devgateway.geoph.services;

import org.devgateway.geoph.core.repositories.GeoPhotoRepository1;
import org.devgateway.geoph.core.services.GeoPhotosService;
import org.devgateway.geoph.model.GeoPhoto;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by sebas on 8/30/2016.
 */

@Service
public class GeoPhotosServiceImpl implements GeoPhotosService {

    @Autowired
    GeoPhotoRepository1 geoPhotoRepository1;

   public FeatureCollection getGeoPhotoData(){

     List<Feature> features=geoPhotoRepository1.findAll().stream().map(geoPhoto -> toFeature(geoPhoto)).collect(Collectors.toList());
       FeatureCollection featureCollection =new FeatureCollection();
       featureCollection.addAll(features);
       return  featureCollection;
    }

    private Feature toFeature(GeoPhoto geoPhoto){
        Feature f=new Feature();
        f.setGeometry(new Point(geoPhoto.getPoint().getCoordinate().y, geoPhoto.getPoint().getCoordinate().x));
        Map<String, Object> properties=new HashMap<>();
        properties.put("name",geoPhoto.getName());
        //properties.put("description",geoPhoto.getDescription());
        properties.put("urls",geoPhoto.getUrls());
        f.setProperties(properties);
        return  f;
    }
}
