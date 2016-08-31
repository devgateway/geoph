package org.devgateway.geoph.services;

import org.devgateway.geoph.core.repositories.*;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.response.ChartResponse;
import org.devgateway.geoph.core.services.ChartService;
import org.devgateway.geoph.dao.AgencyResultsDao;
import org.devgateway.geoph.dao.PhysicalStatusDao;
import org.devgateway.geoph.model.Agency;
import org.devgateway.geoph.model.ProjectAgency;
import org.devgateway.geoph.model.ProjectSector;
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

    @Override
    public Collection<ChartResponse> getFundingByFundingAgency(Parameters params) {
        Map<Long, ChartResponse> respMap = new HashMap<>();

        List<AgencyResultsDao> agenciesResults = fundingAgencyRepository.findFundingByFundingAgency(params);
        for (AgencyResultsDao helper : agenciesResults) {
            ChartResponse chartResponse;
            if (respMap.get(helper.getAgency().getId()) != null) {
                chartResponse = respMap.get(helper.getAgency().getId());
            } else {
                chartResponse = new ChartResponse(helper.getAgency(), params.getTrxTypeSort(), params.getTrxStatusSort());
                respMap.put(helper.getAgency().getId(), chartResponse);
            }
            chartResponse.add(helper.getProject(), FULL_UTILIZATION);
        }

        List ret = new ArrayList(respMap.values());
        Collections.sort(ret);
        return ret;
    }

    @Override
    public Collection<ChartResponse> getFundingByExecutingAgency(Parameters params) {
        Map<Long, ChartResponse> respMap = new HashMap<>();

        List<AgencyResultsDao> agenciesResults = executingAgencyRepository.findFundingByExecutingAgency(params);
        for (AgencyResultsDao helper : agenciesResults) {
            ChartResponse chartResponse;
            if (respMap.get(helper.getAgency().getId()) != null) {
                chartResponse = respMap.get(helper.getAgency().getId());
            } else {
                chartResponse = new ChartResponse(helper.getAgency(), params.getTrxTypeSort(), params.getTrxStatusSort());
                respMap.put(helper.getAgency().getId(), chartResponse);
            }
            chartResponse.add(helper.getProject(), FULL_UTILIZATION);
        }

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

        List<ProjectAgency> agenciesResults = implementingAgencyRepository.findFundingByImplementingAgency(params);
        for (ProjectAgency helper : agenciesResults) {
            Agency ia = helper.getAgency();
            if (showAll || iaParamsSet.contains(ia.getId())) {
                ChartResponse chartResponse;
                if (respMap.get(helper.getAgency().getId()) != null) {
                    chartResponse = respMap.get(helper.getAgency().getId());
                } else {
                    chartResponse = new ChartResponse(helper.getAgency(), params.getTrxTypeSort(), params.getTrxStatusSort());
                    respMap.put(helper.getAgency().getId(), chartResponse);
                }
                chartResponse.add(helper.getProject(), helper.getUtilization());
            }
        }

        List ret = new ArrayList(respMap.values());
        Collections.sort(ret);
        return ret;
    }

    @Override
    public Collection<ChartResponse> getFundingBySector(Parameters params) {
        Map<Long, ChartResponse> respMap = new HashMap<>();

        List<ProjectSector> results = sectorRepository.findFundingBySector(params);
        for (ProjectSector helper : results) {
            ChartResponse chartResponse;
            if (respMap.get(helper.getSector().getId()) != null) {
                chartResponse = respMap.get(helper.getSector().getId());
            } else {
                chartResponse = new ChartResponse(helper.getSector(), params.getTrxTypeSort(), params.getTrxStatusSort());
                respMap.put(helper.getSector().getId(), chartResponse);
            }
            chartResponse.add(helper.getProject(), helper.getUtilization());
        }

        List ret = new ArrayList(respMap.values());
        Collections.sort(ret);
        return ret;
    }


    @Override
    public Collection<ChartResponse> getFundingByPhysicalStatus(Parameters params) {
        Map<Long, ChartResponse> respMap = new HashMap<>();

        List<PhysicalStatusDao> results = physicalStatusRepository.findFundingByPhysicalStatus(params);
        for (PhysicalStatusDao helper : results) {
            ChartResponse chartResponse;
            if (respMap.get(helper.getPhysicalStatus().getId()) != null) {
                chartResponse = respMap.get(helper.getPhysicalStatus().getId());
            } else {
                chartResponse = new ChartResponse(helper.getPhysicalStatus(), params.getTrxTypeSort(), params.getTrxStatusSort());
                respMap.put(helper.getPhysicalStatus().getId(), chartResponse);
            }
            chartResponse.add(helper.getProject(), FULL_UTILIZATION);
        }

        List ret = new ArrayList(respMap.values());
        Collections.sort(ret);
        return ret;
    }
}
