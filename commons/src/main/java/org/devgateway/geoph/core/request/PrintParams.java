package org.devgateway.geoph.core.request;

import org.devgateway.geoph.core.response.ChartResponse;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author dbianco
 *         created on ago 05 2016.
 */
public class PrintParams {

    private Integer width;

    private Integer height;

    private String html;

    private String name;

    private String description;

    private String url;

    private String trxType;

    private String trxStatus;

    private Object data;

    private Map<String, Set<String>> filters;

    private Map<String, List<Map <String, String>>> layers;

    Map<String, Collection<ChartResponse>> allChartsData;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Set<String>> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, Set<String>> filters) {
        this.filters = filters;
    }

    public  Map<String, List<Map <String, String>>> getLayers() {
        return layers;
    }

    public void setLayers(Map<String, List<Map <String, String>>> layers) {
        this.layers = layers;
    }

    public Map<String, Collection<ChartResponse>> getAllChartsData() {
        return allChartsData;
    }

    public void setAllChartsData( Map<String, Collection<ChartResponse>> allChartsData) {
        this.allChartsData = allChartsData;
    }

    public String getTrxType() {
        return trxType;
    }

    public void setTrxType(String trxType) {
        this.trxType = trxType;
    }

    public String getTrxStatus() {
        return trxStatus;
    }

    public void setTrxStatus(String trxStatus) {
        this.trxStatus = trxStatus;
    }
}
