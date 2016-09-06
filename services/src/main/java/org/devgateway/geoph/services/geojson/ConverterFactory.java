package org.devgateway.geoph.services.geojson;

import org.devgateway.geoph.dao.LocationProjectStatsDao;
import org.devgateway.geoph.dao.LocationFundingStatsDao;

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
}
