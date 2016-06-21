package org.devgateway.geoph.core.response;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 04 2016.
 */
public class GenericResponse {

    private List items;

    private int count;

    public GenericResponse(List items, int count) {
        this.items = items;
        this.count = count;
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
