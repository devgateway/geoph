package org.devgateway.geoph.dao;

import com.vividsolutions.jts.geom.Geometry;

import java.util.HashSet;
import java.util.Set;

public class GeoPhotoSummaryDao {

    private Long id;

    private String name;

    private String projectTitle;

    private Set<String> urls = new HashSet<>();

    private Long projectId;

    private Geometry geometry;

    public GeoPhotoSummaryDao(GeoPhotoDao geoPhotoDao) {
        this.id = geoPhotoDao.getId();
        this.name = geoPhotoDao.getName();
        this.projectTitle = geoPhotoDao.getProjectTitle();
        this.projectId = geoPhotoDao.getProjectId();
        this.geometry = geoPhotoDao.getGeometry();
        this.urls.add(geoPhotoDao.getUrl());
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

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
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

    public void addUrl(String url){
        this.urls.add(url);
    }
}
