package org.devgateway.geoph.services.exporter;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
public class SimpleFormatter implements Formatter<String> {
    @Override
    public String format(String value) {
        return value;
    }
}
