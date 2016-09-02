package org.devgateway.geoph.services;

import org.devgateway.geoph.core.repositories.GeoPhotoRepositoryCustom;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.services.GeoPhotosService;
import org.devgateway.geoph.model.GeoPhoto;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    GeoPhotoRepositoryCustom geoPhotoRepository;

   public FeatureCollection getGeoPhotoData(Parameters parameters){

     List<Feature> features=geoPhotoRepository.findGeoPhotosByParams(parameters).stream().map(dao -> toFeature(dao.getGeoPhoto())).collect(Collectors.toList());
       FeatureCollection featureCollection =new FeatureCollection();
       featureCollection.addAll(features);
       return  featureCollection;
    }


    /*TODO: move properties to constant*/
    private Feature toFeature(GeoPhoto geoPhoto){
        Feature f=new Feature();
        f.setGeometry(new Point(geoPhoto.getPoint().getCoordinate().y, geoPhoto.getPoint().getCoordinate().x));
        Map<String, Object> properties=new HashMap<>();
        properties.put("name",geoPhoto.getName());
        //properties.put("description",geoPhoto.getDescription());
        properties.put("urls",geoPhoto.getUrls());
        properties.put("project",geoPhoto.getProject());
        f.setProperties(properties);
        return  f;
    }
}
