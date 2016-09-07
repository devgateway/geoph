package org.devgateway.geoph.services.geojson;

import org.devgateway.geoph.dao.IndicatorGeometryDao;
import org.geojson.Feature;

import static org.devgateway.geoph.core.constants.Constants.*;
import static org.devgateway.geoph.core.constants.Constants.PROPERTY_INDICATOR_NAME;

/**
 * Created by sebas on 9/6/2016.
 */
public class IndicatorGeometryConverter extends AbstractConverter<IndicatorGeometryDao> {
    @Override
    public Feature convert(IndicatorGeometryDao dao) {
        Feature feature=new Feature();

        if (dao.getGeometry() != null) {
            feature.setGeometry(ConverterUtil.convert(dao.getGeometry()));
        } else {

            LOGGER.warn("Feature without geometry will be generated name is "+dao.getName());
        }
        feature.setProperty(PROPERTY_INDICATOR_VALUE, dao.getValue());
        feature.setProperty(PROPERTY_INDICATOR_ID, dao.getIndicatorId());
        feature.setProperty(PROPERTY_INDICATOR_COLOR_SCHEME, dao.getColorScheme());
        feature.setProperty(PROPERTY_LOC_ID, dao.getId()); //location id
        feature.setProperty(PROPERTY_LOC_NAME, dao.getName()); //location name;
        feature.setProperty(PROPERTY_INDICATOR_NAME, dao.getIndicatorName());
        return feature;
    }
}
