package org.devgateway.geoph.core.export;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
public interface Formatter<T> {

    String format(T value);
}
