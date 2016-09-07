package org.devgateway.geoph.dao;

import com.vividsolutions.jts.geom.Geometry;

/**
 * Created by sebas on 9/6/2016.
 */
public class IndicatorGeometryDao extends LocationDao {

    String indicatorName;
    Long indicatorId;
    String colorScheme;
    String description;
    String value;
    Integer level;
    String unit;

    public String getValue() {
        return value;
    }

    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    public Long getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(Long indicatorId) {
        this.indicatorId = indicatorId;
    }

    public String getColorScheme() {
        return colorScheme;
    }

    public void setColorScheme(String colorScheme) {
        this.colorScheme = colorScheme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public IndicatorGeometryDao(Long id, String name, Geometry geometry,  Long indicatorId,String indicatorName, String colorScheme, String description,
                                String value, Integer level,String unit) {
        this.indicatorName = indicatorName;
        this.indicatorId = indicatorId;
        this.colorScheme = colorScheme;
        this.description = description;
        this.value = value;
        this.level = level;
        this.id = id;
        this.name = name;
        this.geometry = geometry;
        this.unit=unit;
    }
}
