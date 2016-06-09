package org.devgateway.geoph.core.request;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.devgateway.geoph.enums.FlowTypeEnum;
import org.devgateway.geoph.enums.LocationAdmLevelEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.devgateway.geoph.core.constants.Constants.FLOW_TYPE_ID_SEPARATOR;
import static org.devgateway.geoph.core.constants.Constants.PARAM_SEPARATOR;

/**
 * @author dbianco
 *         created on mar 17 2016.
 */
public class Parameters {

    private static final Logger LOGGER = LoggerFactory.getLogger(Parameters.class);

    private Date startDateMax;

    private Date startDateMin;

    private Date endDateMax;

    private Date endDateMin;

    private Date periodPerformanceStartMax;

    private Date periodPerformanceStartMin;

    private Date periodPerformanceEndMax;

    private Date periodPerformanceEndMin;

    private Float reachedOwpaMax;

    private Float reachedOwpaMin;

    private List<Long> sectors;

    private List<Long> statuses;

    private List<Long> physicalStatuses;

    private List<Long> locations;

    private List<Long> projects;

    private List<Long> impAgencies;

    private List<Long> fundingAgencies;

    private List<String> flowTypes;

    private List<Integer> grantSubTypes;

    private List<Integer> locationLevels;

    private List<Long> climateChanges;

    private List<Long> genderResponsiveness;

    private String projectTitle;

    private Pageable pageable;

    private Double financialAmountMin;

    private Double financialAmountMax;

    private ProjectOrder projectOrder;

    private Parameters() {
    }

    private Parameters(AppRequestParams filters, Pageable pageable) {
        this(filters);
        this.pageable = pageable;
    }

    private Parameters(AppRequestParams filters) {
        this.setStartDateMax(filters.getDt_start_max());
        this.setStartDateMin(filters.getDt_start_min());
        this.setEndDateMax(filters.getDt_end_max());
        this.setEndDateMin(filters.getDt_end_min());
        this.setLocations(filters.getLo());
        this.setProjects(filters.getPr());
        this.setPeriodPerformanceStartMax(filters.getPp_start_max());
        this.setPeriodPerformanceStartMin(filters.getPp_start_min());
        this.setPeriodPerformanceEndMax(filters.getPp_end_max());
        this.setPeriodPerformanceEndMin(filters.getPp_end_min());
        this.setSectors(filters.getSt());
        this.setStatuses(filters.getSa());
        this.setLocations(filters.getLo());
        this.setImpAgencies(filters.getIa());
        this.setFundingAgencies(filters.getFa());
        this.setFlowTypes(filters.getFt());
        this.projectTitle = filters.getPt();
        this.setPhysicalStatuses(filters.getPh());
        this.setClimateChanges(filters.getCc());
        this.setGenderResponsiveness(filters.getGr());
        this.financialAmountMin = filters.getFin_amount_min();
        this.financialAmountMax = filters.getFin_amount_max();
        this.reachedOwpaMax = filters.getRo_max();
        this.reachedOwpaMin = filters.getRo_min();
    }

    public static Parameters getParameters(AppRequestParams filters) {
        return new Parameters(filters);
    }

    public Float getReachedOwpaMax() {
        return reachedOwpaMax;
    }

    public void setReachedOwpaMax(Float reachedOwpaMax) {
        this.reachedOwpaMax = reachedOwpaMax;
    }

    public Float getReachedOwpaMin() {
        return reachedOwpaMin;
    }

    public void setReachedOwpaMin(Float reachedOwpaMin) {
        this.reachedOwpaMin = reachedOwpaMin;
    }

    public Date getStartDateMax() {
        return startDateMax;
    }

    public void setStartDateMax(Date startDateMax) {
        this.startDateMax = startDateMax;
    }

    public void setStartDateMax(String startDateMax) {
        this.startDateMax = convertStringToDate(startDateMax);
    }

    public Date getStartDateMin() {
        return startDateMin;
    }

    public void setStartDateMin(Date startDateMin) {
        this.startDateMin = startDateMin;
    }

    public void setStartDateMin(String startDateMin) {
        this.startDateMin = convertStringToDate(startDateMin);
    }

    public Date getEndDateMax() {
        return endDateMax;
    }

    public void setEndDateMax(Date endDateMax) {
        this.endDateMax = endDateMax;
    }

    public void setEndDateMax(String endDateMax) {
        this.endDateMax = convertStringToDate(endDateMax);
    }

    public Date getEndDateMin() {
        return endDateMin;
    }

    public void setEndDateMin(Date endDateMin) {
        this.endDateMin = endDateMin;
    }

    public void setEndDateMin(String endDateMin) {
        this.endDateMin = convertStringToDate(endDateMin);
    }

    public Date getPeriodPerformanceStartMax() {
        return periodPerformanceStartMax;
    }

    public void setPeriodPerformanceStartMax(Date periodPerformanceStartMax) {
        this.periodPerformanceStartMax = periodPerformanceStartMax;
    }

    public void setPeriodPerformanceStartMax(String periodPerformanceStartMax) {
        this.periodPerformanceStartMax = convertStringToDate(periodPerformanceStartMax);
    }

    public Date getPeriodPerformanceStartMin() {
        return periodPerformanceStartMin;
    }

    public void setPeriodPerformanceStartMin(Date periodPerformanceStartMin) {
        this.periodPerformanceStartMin = periodPerformanceStartMin;
    }

    public void setPeriodPerformanceStartMin(String periodPerformanceStartMin) {
        this.periodPerformanceStartMin = convertStringToDate(periodPerformanceStartMin);
    }


    public Date getPeriodPerformanceEndMax() {
        return periodPerformanceEndMax;
    }

    public void setPeriodPerformanceEndMax(Date periodPerformanceEndMax) {
        this.periodPerformanceEndMax = periodPerformanceEndMax;
    }

    public void setPeriodPerformanceEndMax(String periodPerformanceEndMax) {
        this.periodPerformanceEndMax = convertStringToDate(periodPerformanceEndMax);
    }

    public Date getPeriodPerformanceEndMin() {
        return periodPerformanceEndMin;
    }

    public void setPeriodPerformanceEndMin(Date periodPerformanceEndMin) {
        this.periodPerformanceEndMin = periodPerformanceEndMin;
    }

    public void setPeriodPerformanceEndMin(String periodPerformanceEndMin) {
        this.periodPerformanceEndMin = convertStringToDate(periodPerformanceEndMin);
    }

    public List<Long> getSectors() {
        return sectors;
    }

    public void setSectors(List<Long> sectors) {
        this.sectors = sectors;
    }

    public void setSectors(String sectors) {
        this.sectors = sectors != null ? convertStringToLongList(sectors) : null;
    }

    public List<Long> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Long> statuses) {
        this.statuses = statuses;
    }

    public void setStatuses(String statuses) {
        this.statuses = statuses != null ? convertStringToLongList(statuses) : null;
    }

    public List<Long> getPhysicalStatuses() {
        return physicalStatuses;
    }

    public void setPhysicalStatuses(List<Long> physicalStatuses) {
        this.physicalStatuses = physicalStatuses;
    }

    public void setPhysicalStatuses(String physicalStatuses) {
        this.physicalStatuses = physicalStatuses != null ? convertStringToLongList(physicalStatuses) : null;
    }

    public List<Long> getLocations() {
        return locations;
    }

    public void setLocations(List<Long> locations) {
        this.locations = locations;
    }

    public void setLocations(String locations) {
        this.locations = locations != null ? convertStringToLongList(locations) : null;
    }

    public List<Long> getProjects() {
        return projects;
    }

    public void setProjects(List<Long> projects) {
        this.projects = projects;
    }

    public void setProjects(String projects) {
        this.projects = projects != null ? convertStringToLongList(projects) : null;
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
        this.impAgencies = impAgencies != null ? convertStringToLongList(impAgencies) : null;
    }

    public List<Long> getFundingAgencies() {
        return fundingAgencies;
    }

    public void setFundingAgencies(List<Long> fundingAgencies) {
        this.fundingAgencies = fundingAgencies;
    }

    public void setFundingAgencies(String fundingAgencies) {
        this.fundingAgencies = fundingAgencies != null ? convertStringToLongList(fundingAgencies) : null;
    }

    public List<String> getFlowTypes() {
        return flowTypes;
    }

    public void setFlowTypes(List<String> flowTypes) {
        this.flowTypes = flowTypes;
    }

    public void setFlowTypes(String filters) {
        if (StringUtils.isNotBlank(filters)) {
            for (String filter : filters.split(PARAM_SEPARATOR)) {
                if (filter.indexOf(FLOW_TYPE_ID_SEPARATOR) > 0) {
                    if (grantSubTypes == null) {
                        grantSubTypes = new ArrayList<>();
                    }
                    grantSubTypes.add(Integer.parseInt(filter.substring(filter.indexOf(FLOW_TYPE_ID_SEPARATOR) + 1)));
                } else {
                    if (flowTypes == null) {
                        flowTypes = new ArrayList<>();
                    }
                    flowTypes.add(FlowTypeEnum.getEnumById(Integer.parseInt(filter)).name().toLowerCase());
                }
            }
        }
    }

    public List<Integer> getGrantSubTypes() {
        return grantSubTypes;
    }

    public void setGrantSubTypes(List<Integer> grantSubTypes) {
        this.grantSubTypes = grantSubTypes;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public List<Long> getClimateChanges() {
        return climateChanges;
    }

    public void setClimateChanges(List<Long> climateChanges) {
        this.climateChanges = climateChanges;
    }

    public void setClimateChanges(String climateChanges) {
        this.climateChanges = climateChanges != null ? convertStringToLongList(climateChanges) : null;
    }

    public List<Long> getGenderResponsiveness() {
        return genderResponsiveness;
    }

    public void setGenderResponsiveness(List<Long> genderResponsiveness) {
        this.genderResponsiveness = genderResponsiveness;
    }

    public void setGenderResponsiveness(String genderResponsiveness) {
        this.genderResponsiveness = genderResponsiveness != null ? convertStringToLongList(genderResponsiveness) : null;
    }

    public Double getFinancialAmountMin() {
        return financialAmountMin;
    }

    public void setFinancialAmountMin(Double financialAmountMin) {
        this.financialAmountMin = financialAmountMin;
    }

    public Double getFinancialAmountMax() {
        return financialAmountMax;
    }

    public void setFinancialAmountMax(Double financialAmountMax) {
        this.financialAmountMax = financialAmountMax;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    private static List<Integer> convertAdmStringToIntegerList(final String values) {
        List<Integer> ret = null;
        if (StringUtils.isNotBlank(values)) {
            ret = Lists.transform(Arrays.asList(values.split(PARAM_SEPARATOR)), new Function<String, Integer>() {
                @Override
                public Integer apply(String level) {
                    return LocationAdmLevelEnum.valueOf(level.toUpperCase()).getLevel();
                }
            });
        }
        return ret;
    }

    private static List<Long> convertStringToLongList(final String values) {
        List<Long> ret = null;
        if (StringUtils.isNotBlank(values)) {
            ret = Lists.transform(Arrays.asList(values.split(PARAM_SEPARATOR)), new Function<String, Long>() {
                @Override
                public Long apply(String o) {
                    return Long.parseLong(o);
                }
            });
        }
        return ret;
    }

    private static Date convertStringToDate(String value) {
        Date ret = null;
        if (StringUtils.isNotBlank(value)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                ret = formatter.parse(value);
            } catch (ParseException e) {
                LOGGER.error("Exception trying to parse the date:" + value + " - " + e.getMessage());
            }
        }
        return ret;
    }

    public ProjectOrder getProjectOrder() {
        return projectOrder;
    }

    public void setProjectOrder(ProjectOrder projectOrder) {
        this.projectOrder = projectOrder;
    }
}
