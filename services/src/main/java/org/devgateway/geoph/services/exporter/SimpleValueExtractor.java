package org.devgateway.geoph.services.exporter;

import org.springframework.context.annotation.Bean;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
public class SimpleValueExtractor<T,O> {


    public Map<String, Object> toKeyValuePairs(Object instance) {
        return Arrays.stream(instance.getClass().getDeclaredMethods())
                .collect(Collectors.toMap(Method::getName, m -> {
                    try {
                        Object result = m.invoke(instance);
                        return result != null ? result : "";
                    } catch (Exception e) {
                        return "";
                    }
                }));
    }

    public Object extract(O source, String property){
        return toKeyValuePairs(source).get(property);
    }
}
