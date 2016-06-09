package org.devgateway.geoph.services.exporter;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */

public class LongFormatter implements Formatter<Long> {
    @Override
    public String format(Long value) {
        return value.toString();
    }
}
