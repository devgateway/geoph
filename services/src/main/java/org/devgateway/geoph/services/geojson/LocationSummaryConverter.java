package org.devgateway.geoph.services.geojson;


import org.devgateway.geoph.dao.LocationSummaryDao;
import org.geojson.Feature;
import org.geojson.Geometry;
import org.wololo.jts2geojson.GeoJSONReader;
import org.wololo.jts2geojson.GeoJSONWriter;

import static org.devgateway.geoph.core.constants.Constants.*;

/**
 * Created by sebas on 9/2/2016.
 */
public class LocationSummaryConverter extends AbstractConverter<LocationSummaryDao> {


    @Override
    public Feature convert(LocationSummaryDao dao) {
        Feature feature = new Feature();

        feature.setProperty(PROPERTY_LOC_NAME, dao.getName());

        feature.setProperty(PROPERTY_LOC_ID, dao.getId());
        if (dao.getGeometry()!=null){
            feature.setGeometry(ConverterUtil.convert(dao.getGeometry()));
        }else if (dao.getCentroid()!=null){
        feature.setGeometry(ConverterUtil.convert(dao.getCentroid()));
        }

        feature.setProperty(PROPERTY_LOC_PROJ_COUNT, dao.getProjectCount());
        feature.setProperty(PROPERTY_LOC_COMMITMENTS, dao.getCommitments());
        feature.setProperty(PROPERTY_LOC_DISBURSEMENTS, dao.getDisbursements());
        feature.setProperty(PROPERTY_LOC_EXPENDITURES, dao.getExpenditure());
        return feature;
    }
}
