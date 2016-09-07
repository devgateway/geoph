package org.devgateway.geoph.dao;

import com.vividsolutions.jts.geom.Geometry;

public class GeoPhotoDao {

    private Long id;

    private String name;

    private String projectTitle;

    private String url;

    private Long projectId;

    private Geometry geometry;

    public GeoPhotoDao(long id, String name, String url, long projectId, String projectTitle, Geometry geometry) {
        this.id = id;
        this.name = name;
        this.projectTitle = projectTitle;
        this.projectId = projectId;
        this.geometry = geometry;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
