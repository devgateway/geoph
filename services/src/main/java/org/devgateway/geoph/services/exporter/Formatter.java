package org.devgateway.geoph.services.exporter;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
public interface Formatter<T> {

    public String format(T value);
}
