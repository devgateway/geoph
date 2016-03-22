package org.devgateway.geoph.util;


/**
 * @author dbianco
 *         created on mar 22 2016.
 */
public class PostGisHelper {

    private String type;

    private long locationId;

    private String regionName;

    private Double[][][][] coordinates;

    public PostGisHelper(String type, Double[][][][] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double[][][][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[][][][] coordinates) {
        this.coordinates = coordinates;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
