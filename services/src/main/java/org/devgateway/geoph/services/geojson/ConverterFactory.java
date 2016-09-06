package org.devgateway.geoph.services.geojson;

import org.devgateway.geoph.dao.GeoPhotoSummaryDao;
import org.devgateway.geoph.dao.IndicatorGeometryDao;
import org.devgateway.geoph.dao.LocationFundingStatsDao;
import org.devgateway.geoph.dao.LocationProjectStatsDao;

/**
 * Created by sebas on 9/2/2016.
 */
public class ConverterFactory {


    public static Converter<LocationFundingStatsDao> locationShapeConverter(){
        return new LocationShapeConverter();
    }

    public static Converter<LocationProjectStatsDao> locationPointConverter(){
        return new LocationPointConverter();
    }


    public static Converter<IndicatorGeometryDao> indicatorGeometryConverter(){
        return new IndicatorGeometryConverter();
    }

    public static Converter<GeoPhotoSummaryDao> geoPhotoConverter(){
        return new GeoPhotoGeometryConverter();
    }
}
