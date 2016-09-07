package org.devgateway.geoph.services.geojson;

import org.geojson.Feature;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Created by sebas on 9/2/2016.
 */
public interface Converter<T> {


    Feature convert(T object);


}
