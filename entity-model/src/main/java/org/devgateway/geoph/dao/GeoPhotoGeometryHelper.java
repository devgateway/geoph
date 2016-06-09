package org.devgateway.geoph.dao;

/**
 * @author dbianco
 *         created on abr 29 2016.
 */
public class GeoPhotoGeometryHelper {


    private long gid;

    private long kmlId;

    private String name;

    private long symbolId;

    private String description;

    private String imagePath;

    private String type;

    private Double[] coordinates;

    public GeoPhotoGeometryHelper(String type, Double[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public long getGid() {
        return gid;
    }

    public void setGid(long gid) {
        this.gid = gid;
    }

    public long getKmlId() {
        return kmlId;
    }

    public void setKmlId(long kmlId) {
        this.kmlId = kmlId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSymbolId() {
        return symbolId;
    }

    public void setSymbolId(long symbolId) {
        this.symbolId = symbolId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }
}
