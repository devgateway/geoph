package org.devgateway.geoph.services.util;

import org.devgateway.geoph.dao.GeoPhotoGeometryDao;
import org.devgateway.geoph.dao.LocationProperty;
import org.devgateway.geoph.model.IndicatorDetail;
import org.devgateway.geoph.model.Location;
import org.geojson.Feature;

import static org.devgateway.geoph.core.constants.Constants.*;

/**
 * @author dbianco
 *         created on ago 30 2016.
 */
public class FeatureHelper {

    public synchronized static Feature setLocationFeature(final Feature feature, final Location location){
        feature.setProperty(PROPERTY_LOC_NAME, location.getName());
        feature.setProperty(PROPERTY_LOC_CODE, location.getCode());
        feature.setProperty(PROPERTY_LOC_PROJ_COUNT, location.getProjects().size());
        return feature;
    }

    public synchronized static Feature setLocationPropertyFeature(final Feature feature, final LocationProperty location){
        feature.setProperty(PROPERTY_LOC_ID, location.getId());
        feature.setProperty(PROPERTY_LOC_NAME, location.getName());
        feature.setProperty(PROPERTY_LOC_CODE, location.getCode());
        feature.setProperty(PROPERTY_LOC_PROJ_COUNT, location.getProjectCount());
        feature.setProperty(PROPERTY_LOC_TRX_COUNT, location.getTransactionCount());
        feature.setProperty(PROPERTY_LOC_COMMITMENTS, location.getCommitments());
        feature.setProperty(PROPERTY_LOC_DISBURSEMENTS, location.getDisbursements());
        feature.setProperty(PROPERTY_LOC_EXPENDITURES, location.getExpenditures());
        feature.setProperty(PROPERTY_LOC_PHYSICAL_PROGRESS, location.getPhysicalProgressAverage());
        return feature;
    }

    public synchronized static Feature setGeoPhotoGeometryFeature(final Feature feature, final GeoPhotoGeometryDao geometryHelper){
        feature.setProperty(PROPERTY_LAYER_GID, geometryHelper.getGid());
        feature.setProperty(PROPERTY_LAYER_KML_ID, geometryHelper.getKmlId());
        feature.setProperty(PROPERTY_LAYER_NAME, geometryHelper.getName());
        feature.setProperty(PROPERTY_LAYER_SYMBOL_ID, geometryHelper.getSymbolId());
        feature.setProperty(PROPERTY_LAYER_TYPE, geometryHelper.getType());
        feature.setProperty(PROPERTY_LAYER_DESCRIPTION, geometryHelper.getDescription());
        feature.setProperty(PROPERTY_LAYER_IMAGE_PATH, geometryHelper.getImagePath());
        return feature;
    }


    public synchronized static Feature setIndicatorDetailFeature(final Feature feature, final IndicatorDetail indicatorDetail, String name, String colorScheme){
        feature.setProperty(PROPERTY_INDICATOR_VALUE, indicatorDetail.getValue());
        feature.setProperty(PROPERTY_INDICATOR_ID, indicatorDetail.getIndicatorId());
        feature.setProperty(PROPERTY_INDICATOR_COLOR_SCHEME, colorScheme);
        feature.setProperty(PROPERTY_INDICATOR_LOC_ID, indicatorDetail.getLocationId());
        feature.setProperty(PROPERTY_INDICATOR_NAME, name);
        return feature;
    }


}
