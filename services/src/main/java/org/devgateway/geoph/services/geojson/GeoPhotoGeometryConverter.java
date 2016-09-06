package org.devgateway.geoph.services.geojson;

import org.devgateway.geoph.dao.GeoPhotoDao;
import org.devgateway.geoph.model.GeoPhoto;
import org.geojson.Feature;
import org.geojson.Point;

import java.util.HashMap;
import java.util.Map;

import static org.devgateway.geoph.core.constants.Constants.*;

/**
 * Created by sebas on 9/6/2016.
 */
public class GeoPhotoGeometryConverter extends AbstractConverter<GeoPhotoDao> {

    @Override
    public Feature convert(GeoPhotoDao dao) {
        Feature feature=new Feature();
        feature.setGeometry(ConverterUtil.convert(dao.getGeometry()));
        feature.setProperty(PROPERTY_GEO_PHOTO_NAME, dao.getName());
        feature.setProperty(PROPERTY_GEO_PHOTO_URLS, dao.getUrls());
        feature.setProperty(PROPERTY_GEO_PHOTO_PROJECT_TITLE, dao.getProjectTitle());
        feature.setProperty(PROPERTY_GEO_PHOTO_PROJECT_ID, dao.getProjectId());
        return feature;
    }
}
