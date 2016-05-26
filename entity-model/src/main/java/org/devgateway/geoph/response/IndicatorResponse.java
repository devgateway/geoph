package org.devgateway.geoph.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.devgateway.geoph.model.Indicator;
import org.devgateway.geoph.model.IndicatorDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dbianco
 *         created on may 24 2016.
 */
public class IndicatorResponse {

    private Long id;

    private String name;

    private String colorScheme;

    private String admLevel;

    private String description;

    private String unit;

    private String filename;

    @JsonIgnore
    private Map<Long, String> details = new HashMap<>();

    private List<String> errors = new ArrayList<>();

    public IndicatorResponse() {
    }

    public IndicatorResponse(Indicator indicator) {
        if(indicator!=null) {
            this.id = indicator.getId();
            this.name = indicator.getName();
            this.colorScheme = indicator.getColorScheme();
            this.description = indicator.getDescription();
            this.unit = indicator.getUnit();
            this.admLevel = indicator.getAdmLevel();
        }
    }


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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Map<Long, String> getDetails() {
        return details;
    }

    public void setDetails(Map<Long, String> details) {
        this.details = details;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String error) {
        this.errors.add(error);
    }

    public void addDetails(List<IndicatorDetail> detailsList) {
        for(IndicatorDetail detail:detailsList){
            details.put(detail.getLocationId(), detail.getValue());
        }
    }
}
