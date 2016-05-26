package org.devgateway.geoph.request;

import org.devgateway.geoph.model.Indicator;

/**
 * @author dbianco
 *         created on may 24 2016.
 */
public class IndicatorRequest {

    private Long id;

    private String name;

    private String colorScheme;

    private String admLevel;

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAdmLevel() {
        return admLevel;
    }

    public void setAdmLevel(String admLevel) {
        this.admLevel = admLevel;
    }

    public Indicator getIndicator(){
        Indicator indicator = new Indicator();
        indicator.setId(this.id);
        indicator.setName(this.name);
        indicator.setColorScheme(this.getColorScheme());
        indicator.setDescription(this.description);
        indicator.setUnit(this.unit);
        indicator.setAdmLevel(this.admLevel);
        return indicator;
    }
}
