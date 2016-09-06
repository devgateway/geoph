package org.devgateway.geoph.dao;

import com.vividsolutions.jts.geom.Geometry;
import org.devgateway.geoph.model.GeoPhoto;

import java.util.ArrayList;
import java.util.List;

public class GeoPhotoDao {

    private Long id;

    private String name;

    private String projectTitle;

    private List<String> urls = new ArrayList<>();

    private Long projectId;

    private Geometry geometry;

    public GeoPhotoDao(GeoPhoto geoPhoto, long projectId, String projectTitle) {
        this.id = geoPhoto.getId();
        this.name = geoPhoto.getName();
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        geoPhoto.getUrls().stream().forEach(x->this.urls.add(x.getUrls()));
        this.geometry = geoPhoto.getPoint();
    }

    public GeoPhotoDao(Long id, String name, Long projectId, String projectTitle, Geometry geometry) {
        this.id = id;
        this.name = name;
        this.projectTitle = projectTitle;
        this.projectId = projectId;
        this.geometry = geometry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
