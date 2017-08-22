package org.devgateway.geoph.rest;

import org.devgateway.geoph.core.response.GenericResponse;
import org.devgateway.geoph.core.services.FilterService;
import org.devgateway.geoph.enums.LocationAdmLevelEnum;
import org.devgateway.geoph.model.Classification;
import org.devgateway.geoph.model.ClimateChange;
import org.devgateway.geoph.model.FundingAgency;
import org.devgateway.geoph.model.GenderResponsiveness;
import org.devgateway.geoph.model.ImplementingAgency;
import org.devgateway.geoph.model.Location;
import org.devgateway.geoph.model.PhysicalStatus;
import org.devgateway.geoph.model.Sector;
import org.devgateway.geoph.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


/**
 * @author dbianco
 *         created on feb 29 2016.
 */

@RestController
@RequestMapping(value = "/filters")
@CrossOrigin
@CacheConfig(keyGenerator = "genericFilterKeyGenerator", cacheNames = "filterControllerCache")
@Cacheable
public class FilterController extends BaseController {

    private static final int DEFAULT_INDEX = 1;

    private final FilterService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterController.class);

    @Autowired
    public FilterController(FilterService service) {
        this.service = service;
    }

    @RequestMapping(value = "/classification", method = GET)
    public GenericResponse findAllClassifications() {
        LOGGER.debug("findAllClassifications");
        List<Classification> classification = service.findAllClassifications();
        GenericResponse resp = new GenericResponse(
                classification,
                classification != null ? classification.size() : 0
        );

        return resp;
    }

    @RequestMapping(value = "/climateChange", method = GET)
    public GenericResponse findAllClimateChanges() {
        LOGGER.debug("findAllClimateChanges");
        List<ClimateChange> climateChanges = service.findAllClimateChanges();
        GenericResponse resp = new GenericResponse(
                climateChanges,
                climateChanges != null ? climateChanges.size() : 0
        );

        return resp;
    }

    @RequestMapping(value = "/physicalProgressPeriod", method = GET)
    public GenericResponse findPhysicalProgressPeriod() {
        LOGGER.debug("findPhysicalProgressPeriod");
        List<Double> physicalProgressPeriod = service.getPhysicalProgressPeriod();
        //Round max to the ceil number
        if(physicalProgressPeriod!=null){
            Double maxValue = Math.ceil(physicalProgressPeriod.get(0));
            physicalProgressPeriod.remove(0);
            physicalProgressPeriod.add(0, maxValue);
        }
        GenericResponse resp = new GenericResponse(
                physicalProgressPeriod,
                physicalProgressPeriod != null ? physicalProgressPeriod.size() : 0
        );

        return resp;
    }

    @RequestMapping(value = "/actualPhysicalProgressPeriod", method = GET)
    public GenericResponse findActualPhysicalProgressPeriod() {
        LOGGER.debug("findActualPhysicalProgressPeriod");
        List<Double> actualPhysicalProgressPeriod = service.getActualPhysicalProgressPeriod();
        GenericResponse resp = new GenericResponse(
                actualPhysicalProgressPeriod,
                actualPhysicalProgressPeriod != null ? actualPhysicalProgressPeriod.size() : 0
        );

        return resp;
    }

    @RequestMapping(value = "/targetPhysicalProgressPeriod", method = GET)
    public GenericResponse findTargetPhysicalProgressPeriod() {
        LOGGER.debug("findTargetPhysicalProgressPeriod");
        List<Double> targetPhysicalProgressPeriod = service.getTargetPhysicalProgressPeriod();
        GenericResponse resp = new GenericResponse(
                targetPhysicalProgressPeriod,
                targetPhysicalProgressPeriod != null ? targetPhysicalProgressPeriod.size() : 0
        );

        return resp;
    }

    @RequestMapping(value = "/genderResponsiveness", method = GET)
    public GenericResponse findAllGenderResponsiveness() {
        LOGGER.debug("findAllGenderResponsiveness");
        List<GenderResponsiveness> genderResponsiveness = service.findAllGenderResponsiveness();
        GenericResponse resp = new GenericResponse(
                genderResponsiveness,
                genderResponsiveness != null ? genderResponsiveness.size() : 0
        );

        return resp;
    }

    @RequestMapping(value = "/financialAmountPeriod", method = GET)
    public GenericResponse findFinancialAmountPeriod() {
        LOGGER.debug("findFinancialAmountPeriod");
        List<Double> financialAmountPeriod = service.findFinancialAmountPeriod();
        GenericResponse resp = new GenericResponse(
                financialAmountPeriod,
                financialAmountPeriod != null ? financialAmountPeriod.size() : 0
        );

        return resp;
    }

    @RequestMapping(value = "/flowType", method = GET)
    public GenericResponse findAllFlowTypes() {
        LOGGER.debug("findAllFlowTypes");
        List<Map<String, Object>> flowTypes = service.findAllFlowTypes();
        GenericResponse resp = new GenericResponse(
                flowTypes,
                flowTypes != null ? flowTypes.size() : 0
        );

        return resp;
    }

    @RequestMapping(value = "/fundingAgency", method = GET)
    public GenericResponse findAllFundingAgencies() {
        LOGGER.debug("findAllFundingAgencies");
        List<FundingAgency> fundingAgencies = service.findAllFundingAgencies();
        GenericResponse resp = new GenericResponse(
                fundingAgencies,
                fundingAgencies != null ? fundingAgencies.size() : 0
        );

        return resp;
    }

    @RequestMapping(value = "/impAgency", method = GET)
    public GenericResponse findAllImpAgencies() {
        LOGGER.debug("findAllImpAgencies");
        List<ImplementingAgency> impAgencies = service.findAllImpAgencies();
        GenericResponse resp = new GenericResponse(
                impAgencies,
                impAgencies != null ? impAgencies.size() : 0
        );

        return resp;
    }



    @RequestMapping(value = "/trxFunding", method = GET)
    public GenericResponse findTrxFunding() {
        LOGGER.debug("findTrxFunding");
        Map<String, Map<String, String>> fundingMap = service.findAllTrxFunding();
        List response = new ArrayList<>();
        response.add(fundingMap);
        GenericResponse resp = new GenericResponse(response, response.size());
        return resp;
    }

    @RequestMapping(value = "/physicalStatus", method = GET)
    public GenericResponse findAllPhysicalStatus() {
        LOGGER.debug("findAllPhysicalStatus");
        List<PhysicalStatus> physicalStatuses = service.findAllPhysicalStatus();
        GenericResponse resp = new GenericResponse(
                physicalStatuses,
                physicalStatuses != null ? physicalStatuses.size() : 0
        );

        return resp;
    }

    @RequestMapping(value = "/location", method = GET)
    public GenericResponse findAllLocations() {
        LOGGER.debug("findAllLocations");
        List<Location> locations = service.findLocationsByLevel(LocationAdmLevelEnum.REGION);
        GenericResponse resp = new GenericResponse(
                locations,
                locations != null ? locations.size() : 0
        );
        return resp;
    }

    @RequestMapping(value = "/location/{level}", method = GET)
    public GenericResponse findLocationsByLevel(@PathVariable final String level) {
        LOGGER.debug("findLocationsByLevel {}", level);
        List<Location> locations = service.findLocationsByLevel(
                LocationAdmLevelEnum.valueOf(level.toUpperCase())
        );
        GenericResponse resp = new GenericResponse(
                locations,
                locations != null ? locations.size() : 0
        );
        return resp;
    }

    @RequestMapping(value = "/location/parent/{parentId}", method = GET)
    public GenericResponse findLocationsByParentId(@PathVariable final long parentId) {
        LOGGER.debug("findLocationsByParentId {}", parentId);
        List<Location> locations = service.findLocationsByParentId(parentId);
        GenericResponse resp = new GenericResponse(
                locations,
                locations != null ? locations.size() : 0
        );
        return resp;
    }

    @RequestMapping(value = "/sector", method = GET)
    public GenericResponse findAllSectors() {
        LOGGER.debug("findAllSectors");
        List<Sector> sectors = service.findAllSectorByLevel(DEFAULT_INDEX);
        sortSectorTree(sectors);
        GenericResponse resp = new GenericResponse(
                sectors,
                sectors != null ? sectors.size() : 0
        );

        return resp;
    }

    @RequestMapping(value = "/status", method = GET)
    public GenericResponse findAllStatuses() {
        LOGGER.debug("findAllStatuses");
        List<Status> statuses = service.findAllStatuses();
        GenericResponse resp = new GenericResponse(
                statuses,
                statuses != null ? statuses.size() : 0
        );

        return resp;
    }

    @RequestMapping(value = "/impPeriod", method = GET)
    public GenericResponse findImpPeriod() {
        LOGGER.debug("findImpPeriod");
        List<String> impPeriodList = service.findImpPeriodBoundaries();
        GenericResponse resp = new GenericResponse(
                impPeriodList,
                impPeriodList != null ? impPeriodList.size() : 0
        );

        return resp;
    }

    @RequestMapping(value = "/grantPeriod", method = GET)
    public GenericResponse findGrantPeriod() {
        LOGGER.debug("findGrantPeriod");
        List<String> grantPeriodList = service.findGrantPeriodBoundaries();

        GenericResponse resp = new GenericResponse(
                grantPeriodList,
                grantPeriodList != null ? grantPeriodList.size() : 0
        );

        return resp;
    }

    private void sortSectorTree(List<Sector> sectors) {
        for (Sector sector : sectors) {
            sortSectorTree(sector.getItems());
        }
        Collections.sort(sectors);
    }
}
