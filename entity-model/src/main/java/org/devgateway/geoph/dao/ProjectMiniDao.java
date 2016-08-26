package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.Project;

/**
 * @author dbianco
 *         created on ago 26 2016.
 */
public class ProjectMiniDao {

    private Long id;

    private String title;

    public ProjectMiniDao(){}

    public ProjectMiniDao(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public ProjectMiniDao(Project project) {
        this.id = project.getId();
        this.title = project.getTitle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
