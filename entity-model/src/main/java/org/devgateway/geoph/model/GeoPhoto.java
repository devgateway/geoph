package org.devgateway.geoph.model;

import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by sebas on 8/30/2016.
 */
@Entity
public class GeoPhoto extends GenericPersistable implements Serializable {

    @Column(columnDefinition = "varchar(255)")
    String name;

    @Column(columnDefinition = "TEXT")
    String description;

    @ElementCollection(fetch = FetchType.EAGER)
    private Collection<String> urls;

    public Collection<String> getUrls() {
        return urls;
    }

    public void setUrls(Collection<String> urls) {
        this.urls = urls;
    }

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
}
