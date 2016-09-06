package org.devgateway.geoph.model;

import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by sebas on 8/30/2016.
 */
@Entity
public class GeoPhoto extends GenericPersistable implements Serializable {

    @Column(columnDefinition = "varchar(255)")
    String name;

    @Column(columnDefinition = "TEXT")
    String description;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "geoPhoto")
    private Set<GeoPhotoUrls> urls;

    @Type(type = "org.hibernate.spatial.GeometryType")
    com.vividsolutions.jts.geom.Point point;

    @ManyToOne(fetch = FetchType.LAZY)
    Project project;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Set<GeoPhotoUrls> getUrls() {
        return urls;
    }

    public void setUrls(Set<GeoPhotoUrls> urls) {
        this.urls = urls;
    }
}
