package org.devgateway.geoph.services.geojson;

import org.devgateway.geoph.model.Location;
import org.geojson.Feature;
import static org.devgateway.geoph.core.constants.Constants.*;
import static org.devgateway.geoph.core.constants.Constants.PROPERTY_LOC_CODE;
import static org.devgateway.geoph.core.constants.Constants.PROPERTY_LOC_ID;
import static org.devgateway.geoph.core.constants.Constants.PROPERTY_LOC_NAME;

/**
 * Created by sebas on 9/2/2016.
 */
public class LocationConverter extends AbstractConverter<Location> {

    @Override
    public Feature convert(Location location) {
        Feature feature=new Feature();
        feature.setProperty(PROPERTY_LOC_ID, location.getId());
        feature.setProperty(PROPERTY_LOC_NAME, location.getName());
        feature.setProperty(PROPERTY_LOC_CODE, location.getCode());

        return feature;
    }
}
