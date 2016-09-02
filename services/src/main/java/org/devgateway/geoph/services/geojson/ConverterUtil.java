package org.devgateway.geoph.services.geojson;

import org.geojson.Feature;

/**
 * Created by sebas on 9/2/2016.
 */
public class ConverterUtil {

    //add f2 properties to f1
    public static Feature merge(Feature f1, Feature f2){
        f1.getProperties().putAll(f2.getProperties());
        return f1;
    }
}
