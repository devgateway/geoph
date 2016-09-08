package org.devgateway.geoph.dao;

/**
 * @author dbianco
 *         created on sep 08 2016.
 */
public class ChartProjectCountDao {

    Long id;

    Long projectCount;

    public ChartProjectCountDao(Long id, Long projectCount) {
        this.id = id;
        this.projectCount = projectCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Long projectCount) {
        this.projectCount = projectCount;
    }
}
