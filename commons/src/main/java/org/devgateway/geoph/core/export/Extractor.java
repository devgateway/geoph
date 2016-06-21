package org.devgateway.geoph.core.export;

import java.util.Map;

/**
 * Created by Sebastian Dimunzio on 6/9/2016.
 */
public interface Extractor<T> {

    T extract(Map<String, Object> properties);
}
