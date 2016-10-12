package org.devgateway.geoph.services.geojson;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import org.geojson.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by sebas on 9/2/2016.
 */
public class ConverterUtil {

    //add f2 properties to f1
    public static Feature merge(Feature f1, Feature f2,Boolean preserve){
        f1.getProperties().putAll(f2.getProperties());
        if(!preserve){
         f1.setGeometry(f2.getGeometry());
        }
        return f1;
    }


    public  static org.geojson.Point xyToPoint(Double latitude, Double longitude){
        return new org.geojson.Point(longitude,latitude);
    }



    public  static org.geojson.GeoJsonObject convert(com.vividsolutions.jts.geom.Geometry geometry) {
        Class<? extends Geometry> c = geometry.getClass();
        if (c.equals(Point.class)) {
            return convert((Point) geometry);
        } else if (c.equals(LineString.class)) {
            return convert((LineString) geometry);
        } else if (c.equals(Polygon.class)) {
            return convert((Polygon) geometry);
        } else if (c.equals(MultiPoint.class)) {
        //    return convert((MultiPoint) geometry);
        } else if (c.equals(MultiLineString.class)) {
          //  return convert((MultiLineString) geometry);
        } else if (c.equals(MultiPolygon.class)) {
            return convert((MultiPolygon) geometry);
        } else if (c.equals(GeometryCollection.class)) {
            //return convert((GeometryCollection) geometry);
        } else {
            throw new UnsupportedOperationException();
        }
        return null;
    }


    public  static    org.geojson.Point convert(com.vividsolutions.jts.geom.Point point) {
        org.geojson.Point json = new  org.geojson.Point();
        json.setCoordinates(convert(point.getCoordinate()));
        return json;
    }

    public  static org.geojson.LineString convert(LineString line) {
        org.geojson.LineString json = new org.geojson.LineString();
        json.setCoordinates(convert(line.getCoordinates()));
        return json;
    }


    public  static org.geojson.Polygon convert(Polygon polygon) {
        org.geojson.Polygon json = new org.geojson.Polygon();
        json.setExteriorRing(convert(polygon.getExteriorRing()).getCoordinates());
        return json;
    }


    public  static org.geojson.MultiPolygon convert(MultiPolygon multiPolygon) {
        org.geojson.MultiPolygon json = new org.geojson.MultiPolygon();
        /*
        *  int size = multiPolygon.getNumGeometries();
        double[][][][] polygons = new double[size][][][];

        for(int i = 0; i < size; ++i) {
            polygons[i] = this.convert((Polygon)multiPolygon.getGeometryN(i)).getCoordinates();
        }

        return new org.wololo.geojson.MultiPolygon(polygons);
        */
        int size = multiPolygon.getNumGeometries();
        for(int i = 0; i < size; ++i) {
            json.add(convert((Polygon) multiPolygon.getGeometryN(i)).getCoordinates());
        }
        return json;
    }



    public  static  LngLatAlt  convert(Coordinate coordinate) {
        return new LngLatAlt(coordinate.x, coordinate.y ,coordinate.z);
    }


    static List<LngLatAlt> convert(Coordinate[] coordinates) {
        return Arrays.asList(coordinates).stream().map(c -> new LngLatAlt(c.x, c.y ,c.z)).collect(Collectors.toList());
    }


}
