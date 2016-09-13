package org.devgateway.geoph.dao;

import org.devgateway.geoph.enums.LocationAdmLevelEnum;
import org.devgateway.geoph.model.Project;
import org.devgateway.geoph.model.ProjectLocation;

import java.util.*;

/**
 * @author dbianco
 *         created on sep 12 2016.
 */
public class ProjectPageDao {

    private Long id;

    private String phId;

    private String title;

    private String fundingAgency;

    private List<String> implementingAgencies;

    private List<String> sectors;

    private Map<String, Map<String, Set<String>>> locations;

    private Date periodPerformanceStart;

    private Date periodPerformanceEnd;

    private String status;

    private String physicalStatus;

    public ProjectPageDao(Project project) {
        this.id = project.getId();
        this.phId = project.getPhId();
        this.title = project.getTitle();
        this.fundingAgency = project.getFundingAgency().getName();

        this.implementingAgencies = new ArrayList<>();
        project.getImplementingAgencies().stream().forEach(ia -> this.implementingAgencies.add(ia.getAgency().getName()));

        this.sectors = new ArrayList<>();
        project.getSectors().stream().forEach(ps -> this.sectors.add(ps.getSector().getName()));

        this.locations = new HashMap<>();
        for(ProjectLocation pl : project.getLocations()) {
            if (pl.getLocation().getLevel() == LocationAdmLevelEnum.MUNICIPALITY.getLevel()) {
                String regionName = pl.getLocation().getRegion().getName();
                String provinceName = pl.getLocation().getProvince().getName();
                String municipalityName = pl.getLocation().getName();
                createLocationTree(regionName, provinceName, municipalityName);
            } else if(pl.getLocation().getLevel() == LocationAdmLevelEnum.PROVINCE.getLevel()){
                String regionName = pl.getLocation().getRegion().getName();
                String provinceName = pl.getLocation().getName();
                createLocationTree(regionName, provinceName, null);
            } else if(pl.getLocation().getLevel() == LocationAdmLevelEnum.REGION.getLevel()){
                String regionName =  pl.getLocation().getName();
                createLocationTree(regionName, null, null);
            }
        }

        this.periodPerformanceStart = project.getPeriodPerformanceStart();
        this.periodPerformanceEnd = project.getPeriodPerformanceEnd();
        if(project.getStatus()!=null) {
            this.status = project.getStatus().getName();
        }
        if(project.getPhysicalStatus()!=null) {
            this.physicalStatus = project.getPhysicalStatus().getName();
        }

    }

    private void createLocationTree(String regionName, String provinceName, String municipalityName) {
        if (!locations.containsKey(regionName)) {
            Map<String, Set<String>> provinceMap = new HashMap<>();
            if(provinceName!=null){
                createProvinceMap(provinceName, municipalityName, provinceMap);
            }
            locations.put(regionName, provinceMap);
        } else {
            if (provinceName != null) {
                Map<String, Set<String>> provinceMap = locations.get(regionName);
                if (provinceMap == null) {
                    provinceMap = new HashMap<>();
                }
                if (!provinceMap.containsKey(provinceName)) {
                    createProvinceMap(provinceName, municipalityName, provinceMap);
                    locations.put(regionName, provinceMap);
                } else {
                    Set<String> muniSet = provinceMap.get(provinceName);
                    if (muniSet == null) {
                        createMunicipalitySet(municipalityName);
                    } else {
                        if(municipalityName!=null) {
                            muniSet.add(municipalityName);
                        }
                    }
                }
            }
        }
    }

    private void createProvinceMap(String provinceName, String municipalityName, Map<String, Set<String>> provinceMap) {
        Set<String> muniSet = new HashSet<>();
        if(municipalityName!=null) {
            muniSet = createMunicipalitySet(municipalityName);
        }
        provinceMap.put(provinceName, muniSet);
    }

    private Set<String> createMunicipalitySet(String municipalityName) {
        Set<String> muniSet = new HashSet<>();
        muniSet.add(municipalityName);
        return muniSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhId() {
        return phId;
    }

    public void setPhId(String phId) {
        this.phId = phId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFundingAgency() {
        return fundingAgency;
    }

    public void setFundingAgency(String fundingAgency) {
        this.fundingAgency = fundingAgency;
    }

    public List<String> getImplementingAgencies() {
        return implementingAgencies;
    }

    public void setImplementingAgencies(List<String> implementingAgencies) {
        this.implementingAgencies = implementingAgencies;
    }

    public List<String> getSectors() {
        return sectors;
    }

    public void setSectors(List<String> sectors) {
        this.sectors = sectors;
    }

    public Map<String, Map<String, Set<String>>> getLocations() {
        return locations;
    }

    public void setLocations(Map<String, Map<String, Set<String>>> locations) {
        this.locations = locations;
    }

    public Date getPeriodPerformanceStart() {
        return periodPerformanceStart;
    }

    public void setPeriodPerformanceStart(Date periodPerformanceStart) {
        this.periodPerformanceStart = periodPerformanceStart;
    }

    public Date getPeriodPerformanceEnd() {
        return periodPerformanceEnd;
    }

    public void setPeriodPerformanceEnd(Date periodPerformanceEnd) {
        this.periodPerformanceEnd = periodPerformanceEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhysicalStatus() {
        return physicalStatus;
    }

    public void setPhysicalStatus(String physicalStatus) {
        this.physicalStatus = physicalStatus;
    }
}
