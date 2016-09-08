package org.devgateway.geoph.services;

import javafx.scene.chart.Chart;
import org.devgateway.geoph.ChartProjectCountDao;
import org.devgateway.geoph.core.repositories.*;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.response.ChartResponse;
import org.devgateway.geoph.core.services.ChartService;
import org.devgateway.geoph.dao.*;
import org.devgateway.geoph.enums.TransactionStatusEnum;
import org.devgateway.geoph.enums.TransactionTypeEnum;
import org.devgateway.geoph.model.Agency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ChartServiceImpl implements ChartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChartServiceImpl.class);
    private static final double FULL_UTILIZATION = 1D;

    @Autowired
    FundingAgencyRepository fundingAgencyRepository;

    @Autowired
    ExecutingAgencyRepository executingAgencyRepository;

    @Autowired
    ImplementingAgencyRepository implementingAgencyRepository;

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    PhysicalStatusRepository physicalStatusRepository;

    @Autowired
    LocationRepository locationRepository;

    @Override
    public Collection<ChartResponse> getFundingByFundingAgency(Parameters params) {
        Map<Long, ChartResponse> respMap = new HashMap<>();
        List<AgencyResultsDao> agenciesResults = fundingAgencyRepository.findFundingByFundingAgencyWithTransactionStats(params);
        for (AgencyResultsDao helper : agenciesResults) {
            ChartResponse chartResponse;
            if (respMap.get(helper.getAgency().getId()) != null) {
                chartResponse = respMap.get(helper.getAgency().getId());
            } else {
                chartResponse = new ChartResponse(helper.getAgency(), params.getTrxTypeSort(), params.getTrxStatusSort());
                respMap.put(helper.getAgency().getId(), chartResponse);
            }
            chartResponse.addTrxAmount(helper.getTrxAmount(), TransactionTypeEnum.getEnumById(helper.getTransactionTypeId()).getName(), TransactionStatusEnum.getEnumById(helper.getTransactionStatusId()).getName());
        }

        List<ChartProjectCountDao> projectStats = fundingAgencyRepository.findFundingByFundingAgencyWithProjectStats(params);
        projectStats.stream().forEach(stats -> {
            ChartResponse response = respMap.get(stats.getId());
            if(response!=null){
                response.addProjects(stats.getProjectCount());
            }
        });

        List ret = new ArrayList(respMap.values());
        Collections.sort(ret);
        return ret;
    }

    @Override
    public Collection<ChartResponse> getFundingByExecutingAgency(Parameters params) {
        Map<Long, ChartResponse> respMap = new HashMap<>();
        List<AgencyResultsDao> agenciesResults = executingAgencyRepository.findFundingByExecutingAgencyWithTransactionStats(params);
        for (AgencyResultsDao helper : agenciesResults) {
            ChartResponse chartResponse;
            if (respMap.get(helper.getAgency().getId()) != null) {
                chartResponse = respMap.get(helper.getAgency().getId());
            } else {
                chartResponse = new ChartResponse(helper.getAgency(), params.getTrxTypeSort(), params.getTrxStatusSort());
                respMap.put(helper.getAgency().getId(), chartResponse);
            }
            chartResponse.addTrxAmount(helper.getTrxAmount(), TransactionTypeEnum.getEnumById(helper.getTransactionTypeId()).getName(), TransactionStatusEnum.getEnumById(helper.getTransactionStatusId()).getName());
        }

        List<ChartProjectCountDao> projectStats = executingAgencyRepository.findFundingByExecutingAgencyWithProjectStats(params);
        projectStats.stream().forEach(stats -> {
            respMap.get(stats.getId()).addProjects(stats.getProjectCount());
        });

        List ret = new ArrayList(respMap.values());
        Collections.sort(ret);
        return ret;
    }

    @Override
    public Collection<ChartResponse> getFundingByImplementingAgency(Parameters params) {
        boolean showAll = false;
        Set<Long> iaParamsSet = new HashSet<>();
        if (params == null || params.getImpAgencies() == null || params.getImpAgencies().size() == 0) {
            showAll = true;
        } else {
            for (Long iaId : params.getImpAgencies()) {
                iaParamsSet.add(iaId);
            }
        }

        Map<Long, ChartResponse> respMap = new HashMap<>();
        List<AgencyResultsDao> agenciesResults = implementingAgencyRepository.findFundingByImplementingAgencyWithTransactionStats(params);
        for (AgencyResultsDao helper : agenciesResults) {
            Agency ia = helper.getAgency();
            if (showAll || iaParamsSet.contains(ia.getId())) {
                ChartResponse chartResponse;
                if (respMap.get(helper.getAgency().getId()) != null) {
                    chartResponse = respMap.get(helper.getAgency().getId());
                } else {
                    chartResponse = new ChartResponse(helper.getAgency(), params.getTrxTypeSort(), params.getTrxStatusSort());
                    respMap.put(helper.getAgency().getId(), chartResponse);
                }
                chartResponse.addTrxAmount(helper.getTrxAmount(), TransactionTypeEnum.getEnumById(helper.getTransactionTypeId()).getName(), TransactionStatusEnum.getEnumById(helper.getTransactionStatusId()).getName());                    }
        }

        List<ChartProjectCountDao> projectStats = implementingAgencyRepository.findFundingByImplementingAgencyWithProjectStats(params);
        projectStats.stream().forEach(stats -> {
            respMap.get(stats.getId()).addProjects(stats.getProjectCount());
        });

        List ret = new ArrayList(respMap.values());
        Collections.sort(ret);
        return ret;
    }

    @Override
    public Collection<ChartResponse> getFundingBySector(Parameters params) {
        Map<Long, ChartResponse> respMap = new HashMap<>();
        List<SectorResultsDao> results = sectorRepository.findFundingBySectorWithTransactionStats(params);
        for (SectorResultsDao helper : results) {
            ChartResponse chartResponse;
            if (respMap.get(helper.getSector().getId()) != null) {
                chartResponse = respMap.get(helper.getSector().getId());
            } else {
                chartResponse = new ChartResponse(helper.getSector(), params.getTrxTypeSort(), params.getTrxStatusSort());
                respMap.put(helper.getSector().getId(), chartResponse);
            }
            chartResponse.addTrxAmount(helper.getTrxAmount(), TransactionTypeEnum.getEnumById(helper.getTransactionTypeId()).getName(), TransactionStatusEnum.getEnumById(helper.getTransactionStatusId()).getName());
        }

        List<ChartProjectCountDao> projectStats = sectorRepository.findFundingBySectorWithProjectStats(params);
        projectStats.stream().forEach(stats -> {
            respMap.get(stats.getId()).addProjects(stats.getProjectCount());
        });

        List ret = new ArrayList(respMap.values());
        Collections.sort(ret);
        return ret;
    }

    @Override
    public Collection<ChartResponse> getFundingByPhysicalStatus(Parameters params) {
        Map<Long, ChartResponse> respMap = new HashMap<>();
        List<PhysicalStatusDao> results = physicalStatusRepository.findFundingByPhysicalStatusWithTransactionStats(params);
        for (PhysicalStatusDao helper : results) {
            ChartResponse chartResponse;
            if (respMap.get(helper.getPhysicalStatus().getId()) != null) {
                chartResponse = respMap.get(helper.getPhysicalStatus().getId());
            } else {
                chartResponse = new ChartResponse(helper.getPhysicalStatus(), params.getTrxTypeSort(), params.getTrxStatusSort());
                respMap.put(helper.getPhysicalStatus().getId(), chartResponse);
            }
            chartResponse.addTrxAmount(helper.getTrxAmount(), TransactionTypeEnum.getEnumById(helper.getTransactionTypeId()).getName(), TransactionStatusEnum.getEnumById(helper.getTransactionStatusId()).getName());
        }

        List<ChartProjectCountDao> projectStats = physicalStatusRepository.findFundingByPhysicalStatusWithProjectStats(params);
        projectStats.stream().forEach(stats -> {
            respMap.get(stats.getId()).addProjects(stats.getProjectCount());
        });

        List ret = new ArrayList(respMap.values());
        Collections.sort(ret);
        return ret;
    }

    @Override
    public Collection<ChartResponse> getFundingByLocation(Parameters params) {
        Map<Long, ChartResponse> respMap = new HashMap<>();

        List<LocationResultsDao> results = locationRepository.getLocationWithTransactionStats(params);
        for (LocationResultsDao helper : results) {
            ChartResponse chartResponse;
            if (respMap.get(helper.getLocationId()) != null) {
                chartResponse = respMap.get(helper.getLocationId());
            } else {
                chartResponse = new ChartResponse(helper.getLocationId(), helper.getName(), params.getTrxTypeSort(), params.getTrxStatusSort());
                respMap.put(helper.getLocationId(), chartResponse);
            }
            chartResponse.addTrxAmount(helper.getTrxAmount(), TransactionTypeEnum.getEnumById(helper.getTransactionTypeId()).getName(), TransactionStatusEnum.getEnumById(helper.getTransactionStatusId()).getName());
        }

        List<LocationProjectStatsDao> projectStats = locationRepository.getLocationWithProjectStats(params);
        projectStats.stream().forEach(stats -> {
            ChartResponse response = respMap.get(stats.getId());
            if(response!=null){
                response.addProjects(stats.getProjectCount());
            }
        });

        List ret = new ArrayList(respMap.values());
        Collections.sort(ret);
        return ret;
    }
}
