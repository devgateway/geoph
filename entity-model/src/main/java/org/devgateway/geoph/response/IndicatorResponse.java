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
            this.description = indicator.getDescription();
            this.unit = indicator.getUnit();
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
