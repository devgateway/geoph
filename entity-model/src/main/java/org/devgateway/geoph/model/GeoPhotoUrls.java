package org.devgateway.geoph.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author dbianco
 *         created on sep 06 2016.
 */
@Entity
public class GeoPhotoUrls implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private GeoPhoto geoPhoto;

    @Id
    @Column(name = "urls")
    private String urls;

    public GeoPhotoUrls() {
    }

    public GeoPhotoUrls(GeoPhoto geoPhoto, String urls) {
        this.geoPhoto = geoPhoto;
        this.urls = urls;
    }

    public GeoPhoto getGeoPhoto() {
        return geoPhoto;
    }

    public void setGeoPhotoId(GeoPhoto geoPhoto) {
        this.geoPhoto = geoPhoto;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }
}
