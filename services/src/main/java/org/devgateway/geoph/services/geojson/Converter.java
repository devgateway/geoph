package org.devgateway.geoph.services.geojson;

import org.geojson.Feature;


/**
 * Created by sebas on 9/2/2016.
 */
public interface Converter<T> {


    Feature convert(T object);


}
