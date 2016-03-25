package org.devgateway.geoph.util;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.devgateway.geoph.util.Constants.PARAM_SEPARATOR;

/**
 * @author dbianco
 *         created on mar 17 2016.
 */
public class Parameters {

    private static final Logger LOGGER = LoggerFactory.getLogger(Parameters.class);

    private Date startDate;

    private Date endDate;

    private List<Long> sectors;

    private List<Long> statuses;

    private List<Long> locations;

    private List<Long> projects;

    private List<Long> impAgencies;

    private List<Long> fundingAgencies;

    private List<Long> flowTypes;

    private List<Integer> locationLevels;

    private Pageable pageable;

    public Parameters() {
    }

    public Parameters(String startDate, String endDate, String sectors, String statuses,
                      String locations, String projects, String impAgencies, String fundingAgencies,
                      String flowTypes, Pageable pageable) {
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setSectors(sectors);
        this.setStatuses(statuses);
        this.setLocations(locations);
        this.setProjects(projects);
        this.setImpAgencies(impAgencies);
        this.setFundingAgencies(fundingAgencies);
        this.setFlowTypes(flowTypes);
        this.setPageable(pageable);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = convertStringToDate(startDate);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = convertStringToDate(endDate);
    }

    public List<Long> getSectors() {
        return sectors;
    }

    public void setSectors(List<Long> sectors) {
        this.sectors = sectors;
    }

    public void setSectors(String sectors) {
        this.sectors = sectors!=null? convertStringToLongList(sectors):null;
    }

    public List<Long> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Long> statuses) {
        this.statuses = statuses;
    }

    public void setStatuses(String statuses) {
        this.statuses = statuses!=null? convertStringToLongList(statuses):null;
    }

    public List<Long> getLocations() {
        return locations;
    }

    public void setLocations(List<Long> locations) {
        this.locations = locations;
    }

    public void setLocations(String locations) {
        this.locations = locations!=null? convertStringToLongList(locations):null;
    }

    public List<Long> getProjects() {
        return projects;
    }

    public void setProjects(List<Long> projects) {
        this.projects = projects;
    }

    public void setProjects(String projects) {
        this.projects = projects!=null? convertStringToLongList(projects):null;
    }

    public List<Integer> getLocationLevels() {
        return locationLevels;
    }

    public void setLocationLevels(List<Integer> locationLevels) {
        this.locationLevels = locationLevels;
    }

    public void setLocationLevel(String locationLevel) {
        this.locationLevels = convertAdmStringToIntegerList(locationLevel);
    }

    public List<Long> getImpAgencies() {
        return impAgencies;
    }

    public void setImpAgencies(List<Long> impAgencies) {
        this.impAgencies = impAgencies;
    }

    public void setImpAgencies(String impAgencies) {
        this.impAgencies = impAgencies!=null? convertStringToLongList(impAgencies):null;
    }

    public List<Long> getFundingAgencies() {
        return fundingAgencies;
    }

    public void setFundingAgencies(List<Long> fundingAgencies) {
        this.fundingAgencies = fundingAgencies;
    }

    public void setFundingAgencies(String fundingAgencies) {
        this.fundingAgencies = fundingAgencies!=null? convertStringToLongList(fundingAgencies):null;
    }

    public List<Long> getFlowTypes() {
        return flowTypes;
    }

    public void setFlowTypes(List<Long> flowTypes) {
        this.flowTypes = flowTypes;
    }

    public void setFlowTypes(String flowTypes) {
        this.flowTypes = flowTypes!=null? convertStringToLongList(flowTypes):null;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }



    private static List<Integer> convertAdmStringToIntegerList(final String values){
        List<Integer> ret = null;
        if(StringUtils.isNotBlank(values)) {
            ret = Lists.transform(Arrays.asList(values.split(PARAM_SEPARATOR)), new Function<String, Integer>() {
                @Override
                public Integer apply(String level) {
                    return LocationAdmLevel.valueOf(level.toUpperCase()).getLevel();
                }
            });
        }
        return ret;
    }

    private static List<Long> convertStringToLongList(final String values){
        List<Long> ret = null;
        if(StringUtils.isNotBlank(values)) {
            ret = Lists.transform(Arrays.asList(values.split(PARAM_SEPARATOR)), new Function<String, Long>() {
                @Override
                public Long apply(String o) {
                    return Long.parseLong(o);
                }
            });
        }
        return ret;
    }

    private static Date convertStringToDate(String value){
        Date ret = null;
        if(StringUtils.isNotBlank(value)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                ret = formatter.parse(value);
            } catch (ParseException e) {
                LOGGER.error("Exception trying to parse the date:" + value + " - " + e.getMessage());
            }
        }
        return ret;
    }
}
