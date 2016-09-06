package org.devgateway.geoph.services.geojson;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.GeoJsonObject;

import java.util.List;

/**
 * Created by sebas on 9/2/2016.
 */
public class GeoJsonBuilder {

    private final FeatureCollection features;
    public GeoJsonBuilder() {
        this.features=new FeatureCollection();
    }

    public void addFeature(Feature feature){
        this.features.add(feature);
    }

    public void setFeatures(List<Feature> features){
        this.features.addAll(features);
    }

    public FeatureCollection getFeatures() {
        return features;
    }
}
