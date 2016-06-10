package org.devgateway.geoph.services.exporter;

import java.util.Map;

/**
 * Created by Sebastian Dimunzio on 6/9/2016.
 */
public class Extractors {


    public static Extractor<String> stringExtractor(final String getter) {
        return new Extractor<String>() {
            @Override
            public String extract(Map<String, Object> properties) {
                return  properties.get(getter).toString();
            }
        };
    }

    public static Extractor<Long> longExtractor(final String getter) {
        return new Extractor<Long>() {
            @Override
            public Long extract(Map<String, Object> properties) {
                return (Long) properties.get(getter);
            }
        };
    }
}
