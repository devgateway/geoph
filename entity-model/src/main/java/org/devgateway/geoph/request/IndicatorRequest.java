package org.devgateway.geoph.request;

import org.devgateway.geoph.model.Indicator;

/**
 * @author dbianco
 *         created on may 24 2016.
 */
public class IndicatorRequest {

    private Long id;

    private String name;

    private String description;

    private String unit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Indicator getIndicator(){
        Indicator indicator = new Indicator();
        indicator.setId(this.id);
        indicator.setName(this.name);
        indicator.setDescription(this.description);
        indicator.setUnit(this.unit);
        return indicator;
    }
}
