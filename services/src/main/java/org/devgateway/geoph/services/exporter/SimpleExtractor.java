package org.devgateway.geoph.services.exporter;

import java.util.HashMap;

/**
 * Created by Sebastian Dimunzio on 6/9/2016.
 */
public class SimpleExtractor<T> {

    public T extract(HashMap<String,Object> properties, String getter){
        return (T) properties.get(getter);
    }
}
