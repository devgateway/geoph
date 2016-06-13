package org.devgateway.geoph.services;

import org.devgateway.geoph.core.repositories.FundingAgencyRepository;
import org.devgateway.geoph.core.repositories.ImplementingAgencyRepository;
import org.devgateway.geoph.core.repositories.PhysicalStatusRepository;
import org.devgateway.geoph.core.repositories.SectorRepository;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.response.ChartResponse;
import org.devgateway.geoph.core.services.ChartService;
import org.devgateway.geoph.dao.AgencyResultsDao;
import org.devgateway.geoph.dao.PhysicalStatusDao;
import org.devgateway.geoph.dao.SectorResultsDao;
import org.devgateway.geoph.enums.TransactionStatusEnum;
import org.devgateway.geoph.enums.TransactionTypeEnum;
import org.devgateway.geoph.model.Agency;
import org.devgateway.geoph.model.PhysicalStatus;
import org.devgateway.geoph.model.Sector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ChartServiceImpl implements ChartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChartServiceImpl.class);

    @Autowired
    FundingAgencyRepository fundingAgencyRepository;

    @Autowired
    ImplementingAgencyRepository implementingAgencyRepository;

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    PhysicalStatusRepository physicalStatusRepository;

    @Override
    public Collection<ChartResponse> getFundingByFundingAgency(Parameters params) {
        Map<Long, ChartResponse> faMap = new HashMap<>();
        for (TransactionTypeEnum trxTypeId : TransactionTypeEnum.values()) {
            for (TransactionStatusEnum trxStatusId : TransactionStatusEnum.values()) {
                List<AgencyResultsDao> fundingAgenciesResults = fundingAgencyRepository.findFundingByFundingAgency(params, trxTypeId.getId(), trxStatusId.getId());

                for (AgencyResultsDao faHelper : fundingAgenciesResults) {
                    Agency fa = faHelper.getAgency();
                    ChartResponse fundingAgencyResponse;
                    if (faMap.get(fa.getId()) != null) {
                        fundingAgencyResponse = faMap.get(fa.getId());
                    } else {
                        fundingAgencyResponse = new ChartResponse(fa, params.getTrxTypeSort(), params.getTrxStatusSort());
                        faMap.put(fa.getId(), fundingAgencyResponse);
                    }
                    fundingAgencyResponse.add(faHelper, trxTypeId, trxStatusId);
                }
            }
        }

        List ret = new ArrayList(faMap.values());
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
        Map<Long, ChartResponse> iaMap = new HashMap<>();
        for (TransactionTypeEnum trxTypeId : TransactionTypeEnum.values()) {
            for (TransactionStatusEnum trxStatusId : TransactionStatusEnum.values()) {
                List<AgencyResultsDao> impAgenciesResults = implementingAgencyRepository.findFundingByImplementingAgency(params, trxTypeId.getId(), trxStatusId.getId());

                for (AgencyResultsDao iaHelper : impAgenciesResults) {
                    Agency ia = iaHelper.getAgency();
                    if (showAll || iaParamsSet.contains(ia.getId())) {
                        ChartResponse iaResponse;
                        if (iaMap.get(ia.getId()) != null) {
                            iaResponse = iaMap.get(ia.getId());
                        } else {
                            iaResponse = new ChartResponse(ia, params.getTrxTypeSort(), params.getTrxStatusSort());
                            iaMap.put(ia.getId(), iaResponse);
                        }
                        iaResponse.add(iaHelper, trxTypeId, trxStatusId);
                    }
                }
            }
        }

        List ret = new ArrayList(iaMap.values());
        Collections.sort(ret);
        return ret;
    }

    @Override
    public Collection<ChartResponse> getFundingBySector(Parameters params) {
        Map<Long, ChartResponse> sectorMap = new HashMap<>();
        for (TransactionTypeEnum trxTypeId : TransactionTypeEnum.values()) {
            for (TransactionStatusEnum trxStatusId : TransactionStatusEnum.values()) {
                List<SectorResultsDao> sectorResults = sectorRepository.findFundingBySector(params, trxTypeId.getId(), trxStatusId.getId());

                for (SectorResultsDao sectorHelper : sectorResults) {
                    Sector sector = sectorHelper.getSector();
                    ChartResponse chartResponse;
                    if (sectorMap.get(sector.getId()) != null) {
                        chartResponse = sectorMap.get(sector.getId());
                    } else {
                        chartResponse = new ChartResponse(sector, params.getTrxTypeSort(), params.getTrxStatusSort());
                        sectorMap.put(sector.getId(), chartResponse);
                    }
                    chartResponse.add(sectorHelper, trxTypeId, trxStatusId);
                }
            }
        }
        List ret = new ArrayList(sectorMap.values());
        Collections.sort(ret);
        return ret;
    }


    @Override
    public Collection<ChartResponse> getFundingByPhysicalStatus(Parameters params) {
        Map<Long, ChartResponse> phyStatusMap = new HashMap<>();
        for (TransactionTypeEnum trxTypeId : TransactionTypeEnum.values()) {
            for (TransactionStatusEnum trxStatusId : TransactionStatusEnum.values()) {
                List<PhysicalStatusDao> phyStatusResults = physicalStatusRepository.findFundingByPhysicalStatus(params, trxTypeId.getId(), trxStatusId.getId());

                for (PhysicalStatusDao phyStatusHelper : phyStatusResults) {
                    PhysicalStatus physicalStatus = phyStatusHelper.getPhysicalStatus();
                    ChartResponse chartResponse;
                    if (phyStatusMap.get(physicalStatus.getId()) != null) {
                        chartResponse = phyStatusMap.get(physicalStatus.getId());
                    } else {
                        chartResponse = new ChartResponse(physicalStatus, params.getTrxTypeSort(), params.getTrxStatusSort());
                        phyStatusMap.put(physicalStatus.getId(), chartResponse);
                    }
                    chartResponse.add(phyStatusHelper, trxTypeId, trxStatusId);
                }
            }
        }

        List ret = new ArrayList(phyStatusMap.values());
        Collections.sort(ret);
        return ret;
    }
}
