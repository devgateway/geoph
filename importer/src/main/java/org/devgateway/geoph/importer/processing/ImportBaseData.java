package org.devgateway.geoph.importer.processing;

import org.devgateway.geoph.core.services.FilterService;
import org.devgateway.geoph.core.services.ProjectService;
import org.devgateway.geoph.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dbianco
 *         created on jul 04 2016.
 */
@Service
public class ImportBaseData {

    protected final ProjectService projectService;

    protected final Map<String, Currency> currencies;

    protected final Map<String, GrantSubType> grantSubTypes;

    protected final Map<String, Sector> sectors;

    protected final Map<String, Location> locations;

    protected final Map<String, Status> statuses;

    protected final Map<String, PhysicalStatus> physicalStatuses;

    protected final Map<String, FundingAgency> fundingAgencies;

    protected final Map<String, ImplementingAgency> implementingAgencies;

    protected final Map<String, Classification> classifications;

    protected final Map<String, ExecutingAgency> executingAgencies;

    @Autowired
    public ImportBaseData(FilterService filterService, ProjectService projectService){
        this.projectService = projectService;
        this.currencies = filterService.findAllCurrencies().stream().collect(Collectors.toMap(x -> x.getCode().toLowerCase(), x -> x));
        this.grantSubTypes = filterService.findAllGrantSubTypes().stream().collect(Collectors.toMap(x -> x.getName().toLowerCase(), x -> x));
        this.sectors = filterService.findAllSectors().stream().collect(Collectors.toMap(x -> x.getName().toLowerCase(), x -> x));
        this.locations = filterService.findAllLocations().stream().collect(Collectors.toMap(x -> x.getCode(), x -> x));
        this.statuses = filterService.findAllStatuses().stream().collect(Collectors.toMap(x -> x.getName().toLowerCase(), x -> x));
        this.physicalStatuses = filterService.findAllPhysicalStatus().stream().collect(Collectors.toMap(x -> x.getName().toLowerCase(), x -> x));
        this.fundingAgencies = filterService.findAllFundingAgencies().stream().collect(Collectors.toMap(x -> x.getCode().toLowerCase(), x -> x));
        this.implementingAgencies = filterService.findAllImpAgencies().stream().collect(Collectors.toMap(x -> x.getCode().toLowerCase(), x -> x));
        this.classifications = filterService.findAllClassifications().stream().collect(Collectors.toMap(x -> x.getName().toLowerCase(), x -> x));
        this.executingAgencies = filterService.findAllExecutingAgencies().stream().collect(Collectors.toMap(x -> x.getName().toLowerCase(), x -> x));
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public Map<String, Currency> getCurrencies() {
        return currencies;
    }

    public Map<String, GrantSubType> getGrantSubTypes() {
        return grantSubTypes;
    }

    public Map<String, Sector> getSectors() {
        return sectors;
    }

    public Map<String, Location> getLocations() {
        return locations;
    }

    public Map<String, Status> getStatuses() {
        return statuses;
    }

    public Map<String, PhysicalStatus> getPhysicalStatuses() {
        return physicalStatuses;
    }

    public Map<String, FundingAgency> getFundingAgencies() {
        return fundingAgencies;
    }

    public Map<String, ImplementingAgency> getImplementingAgencies() {
        return implementingAgencies;
    }

    public Map<String, Classification> getClassifications() {
        return classifications;
    }

    public Map<String, ExecutingAgency> getExecutingAgencies() {
        return executingAgencies;
    }
}
