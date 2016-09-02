package org.devgateway.geoph.services.geojson;

import org.devgateway.geoph.model.Location;
import org.geojson.Feature;
import org.geojson.Point;

/**
 * Created by sebas on 9/2/2016.
 */
public class ConverterUtil {

    //add f2 properties to f1
    public static Feature merge(Feature f1, Feature f2,Boolean preserve){
        f1.getProperties().putAll(f2.getProperties());
        if(preserve==false){
         f1.setGeometry(f2.getGeometry());
        }
        return f1;
    }


    public  static Point xyToPoint(Double latitude, Double longitude){
        return new Point(longitude,latitude);
    }
}
