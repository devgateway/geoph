package org.devgateway.geoph.util;

import org.devgateway.geoph.model.Project;
import org.devgateway.geoph.model.Project_;

import javax.persistence.metamodel.SingularAttribute;

/**
 * @author dbianco
 *         created on jun 06 2016.
 */
public class ProjectOrder {

    private boolean ascending = true;

    private String column;

    public boolean getAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public SingularAttribute<Project, String> getAttribute(){
        if(column!=null && column.trim().equalsIgnoreCase("title")){
            return Project_.title;
        } else if(column!=null && column.trim().equalsIgnoreCase("phid")){
            return Project_.phId;
        }
        return Project_.title;
    }

}
