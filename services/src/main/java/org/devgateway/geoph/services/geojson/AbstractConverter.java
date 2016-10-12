package org.devgateway.geoph.services.geojson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sebas on 9/2/2016.
 */
public abstract class AbstractConverter<T> implements  Converter<T> {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractConverter.class);

}
