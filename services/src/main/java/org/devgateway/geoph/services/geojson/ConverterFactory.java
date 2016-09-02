package org.devgateway.geoph.services.geojson;

import org.devgateway.geoph.dao.LocationSummaryDao;

/**
 * Created by sebas on 9/2/2016.
 */
public class ConverterFactory {


    public static Converter<LocationSummaryDao> createLocationSummaryConverter(){
        return new LocationSummaryConverter();
    }
}
