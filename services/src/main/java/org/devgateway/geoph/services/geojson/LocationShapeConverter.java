package org.devgateway.geoph.services.geojson;


import org.devgateway.geoph.dao.LocationFundingStatsDao;
import org.geojson.Feature;

import static org.devgateway.geoph.core.constants.Constants.*;

/**
 * Created by sebas on 9/2/2016.
 */
public class LocationShapeConverter extends AbstractConverter<LocationFundingStatsDao> {


    @Override
    public Feature convert(LocationFundingStatsDao dao) {
        Feature feature = new Feature();

        feature.setProperty(PROPERTY_LOC_NAME, dao.getName());

        feature.setProperty(PROPERTY_LOC_ID, dao.getId());
        feature.setProperty(PROPERTY_LOC_LEVEL,dao.getLevel());
        if (dao.getGeometry() != null) {
            feature.setGeometry(ConverterUtil.convert(dao.getGeometry()));
        } else {

            LOGGER.warn("Feature without geometry will be generated name is "+dao.getName());
        }
        feature.setProperty(PROPERTY_LOC_COMMITMENTS, dao.getCommitments());
        feature.setProperty(PROPERTY_LOC_DISBURSEMENTS, dao.getDisbursements());
        feature.setProperty(PROPERTY_LOC_EXPENDITURES, dao.getExpenditure());
        return feature;
    }
}
