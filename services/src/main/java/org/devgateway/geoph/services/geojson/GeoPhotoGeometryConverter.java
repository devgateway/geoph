package org.devgateway.geoph.services.geojson;

import org.devgateway.geoph.dao.GeoPhotoSummaryDao;
import org.geojson.Feature;

import static org.devgateway.geoph.core.constants.Constants.*;

/**
 * Created by sebas on 9/6/2016.
 */
public class GeoPhotoGeometryConverter extends AbstractConverter<GeoPhotoSummaryDao> {

    @Override
    public Feature convert(GeoPhotoSummaryDao dao) {
        Feature feature=new Feature();
        feature.setGeometry(ConverterUtil.convert(dao.getGeometry()));
        feature.setProperty(PROPERTY_GEO_PHOTO_NAME, dao.getName());
        feature.setProperty(PROPERTY_GEO_PHOTO_PROJECT_TITLE, dao.getProjectTitle());
        feature.setProperty(PROPERTY_GEO_PHOTO_PROJECT_ID, dao.getProjectId());
        feature.setProperty(PROPERTY_GEO_PHOTO_URLS, dao.getUrls());
        return feature;
    }
}
