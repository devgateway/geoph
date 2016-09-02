package org.devgateway.geoph.services.geojson;


import org.devgateway.geoph.dao.LocationSummaryDao;
import org.devgateway.geoph.model.Location;
import org.geojson.Feature;

import java.util.HashMap;
import java.util.List;

import static org.devgateway.geoph.core.constants.Constants.*;
import static org.devgateway.geoph.core.constants.Constants.PROPERTY_LOC_EXPENDITURES;
import static org.devgateway.geoph.core.constants.Constants.PROPERTY_LOC_PHYSICAL_PROGRESS;

/**
 * Created by sebas on 9/2/2016.
 */
public class LocationSummaryConverter extends AbstractConverter<LocationSummaryDao> {


    @Override
    public Feature convert(LocationSummaryDao dao) {
        Feature feature = new Feature();

        feature.setProperty(PROPERTY_LOC_NAME, dao.getName());

        feature.setProperty(PROPERTY_LOC_ID, dao.getId());
        feature.setGeometry(ConverterUtil.xyToPoint(dao.getLatitude(),dao.getLongitude()));


        feature.setProperty(PROPERTY_LOC_PROJ_COUNT, dao.getProjectCount());
        feature.setProperty(PROPERTY_LOC_COMMITMENTS, dao.getCommitments());
        feature.setProperty(PROPERTY_LOC_DISBURSEMENTS, dao.getDisbursements());
        feature.setProperty(PROPERTY_LOC_EXPENDITURES, dao.getExpenditure());
        return feature;
    }
}
