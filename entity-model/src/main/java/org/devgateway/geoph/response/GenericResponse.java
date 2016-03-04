package org.devgateway.geoph.response;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

/**
 * @author dbianco
 *         created on mar 04 2016.
 */
public class GenericResponse {

    private String description;

    private String param;

    private String group;

    private int index;

    private List items;

    private int count;

    public GenericResponse() {
    }

    public GenericResponse(String description, String param, String group, int index) {
        this.description = description;
        this.param = param;
        this.group = group;
        this.index = index;
    }

    public GenericResponse(String description, String param, String group, int index, List items, int count) {
        this.description = description;
        this.param = param;
        this.group = group;
        this.index = index;
        this.items = items;
        this.count = count;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
