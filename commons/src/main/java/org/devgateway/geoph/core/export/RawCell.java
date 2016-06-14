package org.devgateway.geoph.core.export;

/**
 * Created by Sebastian Dimunzio on 6/10/2016.
 */
public interface RawCell<T> {
    T getValue();

    Stylist getStylist();

}
