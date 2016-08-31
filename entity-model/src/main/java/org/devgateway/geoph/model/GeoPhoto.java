package org.devgateway.geoph.model;

import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by sebas on 8/30/2016.
 */
@Entity
public class GeoPhoto extends GenericPersistable implements Serializable {

    @Column(columnDefinition = "varchar(255)")
    String name;


    @Column(columnDefinition = "varchar(5000)")
    String description;


    @ElementCollection
    private Collection<String> url;

    public Collection<String> getUrl() {
        return url;
    }

    public void setUrl(Collection<String> url) {
        this.url = url;
    }

    @Type(type = "org.hibernate.spatial.GeometryType")
    com.vividsolutions.jts.geom.Point point;

    @ManyToOne(targetEntity = Project.class,optional = true)


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
