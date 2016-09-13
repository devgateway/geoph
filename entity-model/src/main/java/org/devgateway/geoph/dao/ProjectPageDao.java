package org.devgateway.geoph.dao;

import org.devgateway.geoph.enums.LocationAdmLevelEnum;
import org.devgateway.geoph.model.Location;
import org.devgateway.geoph.model.Project;
import org.devgateway.geoph.model.ProjectLocation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dbianco
 *         created on sep 12 2016.
 */
public class ProjectPageDao {

    private Long id;

    private String phId;

    private String title;

    private String fundingAgency;

    private Map<Long, Map<String, Object>> implementingAgencies;

    private Map<Long, Map<String, Object>> sectors;

    private Map<Long, LocationTree> locations;

    private Date periodPerformanceStart;

    private Date periodPerformanceEnd;

    private String status;

    private String physicalStatus;

    public ProjectPageDao(Project project) {
        this.id = project.getId();
        this.phId = project.getPhId();
        this.title = project.getTitle();
        this.fundingAgency = project.getFundingAgency().getName();

        this.implementingAgencies = new HashMap<>();
        project.getImplementingAgencies().stream().forEach(ia -> {
            Map<String, Object> iaMap = new HashMap<>();
            iaMap.put("name", ia.getAgency().getName());
            iaMap.put("id", ia.getAgency().getId());
            this.implementingAgencies.put(ia.getAgency().getId(), iaMap);
        });

        this.sectors = new HashMap<>();
        project.getSectors().stream().forEach(ps -> {
            Map<String, Object> sectorMap = new HashMap<>();
            sectorMap.put("name", ps.getSector().getName());
            sectorMap.put("id", ps.getSector().getId());
            this.sectors.put(ps.getSector().getId(), sectorMap);
        });

        this.locations = new HashMap<>();
        for(ProjectLocation pl : project.getLocations()) {
            Location region = null;
            Location province = null;
            Location municipality = null;
            if (pl.getLocation().getLevel() == LocationAdmLevelEnum.MUNICIPALITY.getLevel()) {
                region = pl.getLocation().getRegion();
                province = pl.getLocation().getProvince();
                municipality = pl.getLocation();
            } else if(pl.getLocation().getLevel() == LocationAdmLevelEnum.PROVINCE.getLevel()){
                region = pl.getLocation().getRegion();
                province = pl.getLocation();
            } else if(pl.getLocation().getLevel() == LocationAdmLevelEnum.REGION.getLevel()){
                region = pl.getLocation();
            }
            createLocationTree(region, province, municipality);
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

    private void createLocationTree(Location region, Location province, Location municipality) {
        if (!locations.containsKey(region.getId())) {
            LocationTree regionTree = new LocationTree(region);
            if(province!=null){
                createProvince(province, municipality, regionTree.getChilds());
            }
            locations.put(region.getId(), regionTree);
        } else {
            if (province != null) {
                Map<Long, LocationTree> provinceMap = locations.get(region.getId()).getChilds();
                if (!provinceMap.containsKey(province.getId())) {
                    createProvince(province, municipality, locations.get(region.getId()).getChilds());
                } else {
                    Map<Long, LocationTree> municipalityMap = provinceMap.get(province.getId()).getChilds();
                    if (municipalityMap == null) {
                        createMunicipality(municipality, provinceMap.get(province.getId()).getChilds());
                    } else {
                        if(municipality!=null) {
                            municipalityMap.put(municipality.getId(), new LocationTree(municipality));
                        }
                    }
                }
            }
        }
    }

    private void createProvince(Location province, Location municipality, Map<Long, LocationTree> regionChild) {
        LocationTree provinceTree = new LocationTree(province);
        if(municipality!=null) {
            createMunicipality(municipality, provinceTree.getChilds());
        }
        regionChild.put(province.getId(), provinceTree);
    }

    private void createMunicipality(Location municipality, Map<Long, LocationTree> provinceChild) {
        LocationTree municipalityTree = new LocationTree(municipality);
        provinceChild.put(municipality.getId(), municipalityTree);
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

    public Map<Long, Map<String, Object>> getImplementingAgencies() {
        return implementingAgencies;
    }

    public void setImplementingAgencies(Map<Long, Map<String, Object>> implementingAgencies) {
        this.implementingAgencies = implementingAgencies;
    }

    public Map<Long, Map<String, Object>> getSectors() {
        return sectors;
    }

    public void setSectors(Map<Long, Map<String, Object>> sectors) {
        this.sectors = sectors;
    }

    public Map<Long, LocationTree> getLocations() {
        return locations;
    }

    public void setLocations(Map<Long, LocationTree> locations) {
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

    class LocationTree{

        private Long id;

        private String name;

        private Map<Long, LocationTree> childs;

        public LocationTree (Location location){
            this.id = location.getId();
            this.name = location.getName();
            this.childs = new HashMap<>();
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

        public Map<Long, LocationTree> getChilds() {
            return childs;
        }

        public void setChilds(Map<Long, LocationTree> childs) {
            this.childs = childs;
        }
    }
}
