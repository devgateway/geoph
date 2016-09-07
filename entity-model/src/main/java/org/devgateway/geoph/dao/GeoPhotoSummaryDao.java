package org.devgateway.geoph.dao;

import com.vividsolutions.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.List;

public class GeoPhotoSummaryDao {

    private Long id;

    private String name;

    private String projectTitle;

    private List<String> urls = new ArrayList<>();;

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

    public GeoPhotoSummaryDao(long id, String name, List<String> urls, long projectId, String projectTitle, Geometry geometry) {
        this.id = id;
        this.name = name;
        this.projectTitle = projectTitle;
        this.projectId = projectId;
        this.geometry = geometry;
        this.urls = urls;
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

    public void addUrl(String url){
        this.urls.add(url);
    }
}
