package org.devgateway.geoph.dao;

import com.vividsolutions.jts.geom.Geometry;
import org.devgateway.geoph.model.GeoPhoto;

import java.util.List;

public class GeoPhotoDao {


    Long id;
    String name;
    String projectTitle;
    List<String> urls;
    Long projectId;
    Geometry geometry;

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

    public GeoPhotoDao(Long id, String name, String projectTitle, List<String> urls, Long projectId, Geometry geometry) {

        this.id = id;
        this.name = name;
        this.projectTitle = projectTitle;
        this.urls = urls;
        this.projectId = projectId;
        this.geometry = geometry;
    }
}
