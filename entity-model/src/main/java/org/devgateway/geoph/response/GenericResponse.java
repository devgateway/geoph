package org.devgateway.geoph.response;

import org.springframework.data.domain.Page;

import java.util.Set;

/**
 * @author dbianco
 *         created on mar 04 2016.
 */
public class GenericResponse {

    private String description;

    private String param;

    private String group;

    private String index;

    private Set itemsSet;

    private Page itemsPage;

    public GenericResponse() {
    }

    public GenericResponse(String description, String param, String group, String index) {
        this.description = description;
        this.param = param;
        this.group = group;
        this.index = index;
    }

    public GenericResponse(String description, String param, String group, String index, Set itemsSet) {
        this.description = description;
        this.param = param;
        this.group = group;
        this.index = index;
        this.itemsSet = itemsSet;
    }

    public GenericResponse(String description, String param, String group, String index, Page itemsPage) {
        this.description = description;
        this.param = param;
        this.group = group;
        this.index = index;
        this.itemsPage = itemsPage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Set getItemsSet() {
        return itemsSet;
    }

    public void setItemsSet(Set itemsSet) {
        this.itemsSet = itemsSet;
    }

    public Page getItemsPage() {
        return itemsPage;
    }

    public void setItemsPage(Page itemsPage) {
        this.itemsPage = itemsPage;
    }
}
