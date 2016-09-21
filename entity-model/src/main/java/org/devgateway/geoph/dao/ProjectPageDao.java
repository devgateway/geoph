package org.devgateway.geoph.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.devgateway.geoph.enums.LocationAdmLevelEnum;
import org.devgateway.geoph.enums.TransactionStatusEnum;
import org.devgateway.geoph.enums.TransactionTypeEnum;
import org.devgateway.geoph.model.Location;
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

    private Double totalProjectAmount;

    private String fundingAgency;

    private List<Map<String, Object>> implementingAgencies;

    private List<Map<String, Object>> sectors;

    @JsonIgnore
    private Map<Long, LocationTree> locations;

    private Date periodPerformanceStart;

    private Date periodPerformanceEnd;

    private String status;

    private String physicalStatus;

    private Map<String, Map<String, Double>> trxAmounts = new HashMap<>();

    public ProjectPageDao(Project project) {
        this.id = project.getId();
        this.phId = project.getPhId();
        this.title = project.getTitle();
        this.totalProjectAmount = project.getTotalProjectAmount();
        this.fundingAgency = project.getFundingAgency().getName();

        this.implementingAgencies = new ArrayList<>();
        project.getImplementingAgencies().stream().forEach(ia -> {
            Map<String, Object> iaMap = new HashMap<>();
            iaMap.put("name", ia.getAgency().getName());
            iaMap.put("id", ia.getAgency().getId());
            this.implementingAgencies.add(iaMap);
        });

        this.sectors = new ArrayList<>();
        project.getSectors().stream().forEach(ps -> {
            Map<String, Object> sectorMap = new HashMap<>();
            sectorMap.put("name", ps.getSector().getName());
            sectorMap.put("id", ps.getSector().getId());
            this.sectors.add(sectorMap);
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

        for(TransactionTypeEnum typeEnum:TransactionTypeEnum.values()){
            Map<String, Double> statusMap = new HashMap<>();
            for(TransactionStatusEnum statusEnum:TransactionStatusEnum.values()){
                statusMap.put(statusEnum.getName(), 0D);
            }
            trxAmounts.put(typeEnum.getName(), statusMap);
        }

        project.getTransactions().stream().forEach(trx->{
            Double oldAmount = trxAmounts.get(trx.getTransactionType().getName().toLowerCase()).get(trx.getTransactionStatus().getName().toLowerCase());
            trxAmounts.get(trx.getTransactionType().getName().toLowerCase()).put(trx.getTransactionStatus().getName(), oldAmount + trx.getAmount());
        });
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

    public Double getTotalProjectAmount() {
        return totalProjectAmount;
    }

    public void setTotalProjectAmount(Double totalProjectAmount) {
        this.totalProjectAmount = totalProjectAmount;
    }

    public String getFundingAgency() {
        return fundingAgency;
    }

    public void setFundingAgency(String fundingAgency) {
        this.fundingAgency = fundingAgency;
    }

    public List<Map<String, Object>> getImplementingAgencies() {
        return implementingAgencies;
    }

    public void setImplementingAgencies(List<Map<String, Object>> implementingAgencies) {
        this.implementingAgencies = implementingAgencies;
    }

    public List<Map<String, Object>> getSectors() {
        return sectors;
    }

    public void setSectors(List<Map<String, Object>> sectors) {
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

    public Collection<LocationTree> getLocationTree() {
        return locations.values();
    }

    public Map<String, Map<String, Double>> getTrxAmounts() {
        return trxAmounts;
    }

    public void setTrxAmounts(Map<String, Map<String, Double>> trxAmounts) {
        this.trxAmounts = trxAmounts;
    }

    class LocationTree{

        private Long id;

        private String name;

        @JsonIgnore
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

        public Collection<LocationTree> getChildrens() {
            return childs.values();
        }
    }
}
