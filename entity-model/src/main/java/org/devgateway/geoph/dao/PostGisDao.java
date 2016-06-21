package org.devgateway.geoph.dao;


/**
 * @author dbianco
 *         created on mar 22 2016.
 */
public class PostGisDao {

    private String type;

    private long locationId;

    private String name;

    private Double[][][][] coordinates;

    public PostGisDao(String type, Double[][][][] coordinates) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
