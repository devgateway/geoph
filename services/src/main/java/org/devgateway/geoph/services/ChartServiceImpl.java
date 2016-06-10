package org.devgateway.geoph.services;

import org.devgateway.geoph.core.repositories.FundingAgencyRepository;
import org.devgateway.geoph.core.repositories.ImplementingAgencyRepository;
import org.devgateway.geoph.core.repositories.PhysicalStatusRepository;
import org.devgateway.geoph.core.repositories.SectorRepository;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.response.ChartResponse;
import org.devgateway.geoph.core.services.ChartService;
import org.devgateway.geoph.dao.AgencyResultsQueryHelper;
import org.devgateway.geoph.dao.PhysicalStatusQueryHelper;
import org.devgateway.geoph.dao.SectorResultsQueryHelper;
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
                List<AgencyResultsQueryHelper> fundingAgenciesResults = fundingAgencyRepository.findFundingByFundingAgency(params, trxTypeId.getId(), trxStatusId.getId());

                for (AgencyResultsQueryHelper faHelper : fundingAgenciesResults) {
                    Agency fa = faHelper.getAgency();
                    ChartResponse fundingAgencyResponse;
                    if (faMap.get(fa.getId()) != null) {
                        fundingAgencyResponse = faMap.get(fa.getId());
                    } else {
                        fundingAgencyResponse = new ChartResponse(fa);
                        faMap.put(fa.getId(), fundingAgencyResponse);
                    }
                    fundingAgencyResponse.add(faHelper, trxTypeId, trxStatusId);
                }
            }
        }
        return faMap.values();
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
                List<AgencyResultsQueryHelper> impAgenciesResults = implementingAgencyRepository.findFundingByImplementingAgency(params, trxTypeId.getId(), trxStatusId.getId());

                for (AgencyResultsQueryHelper iaHelper : impAgenciesResults) {
                    Agency ia = iaHelper.getAgency();
                    if (showAll || iaParamsSet.contains(ia.getId())) {
                        ChartResponse iaResponse;
                        if (iaMap.get(ia.getId()) != null) {
                            iaResponse = iaMap.get(ia.getId());
                        } else {
                            iaResponse = new ChartResponse(ia);
                            iaMap.put(ia.getId(), iaResponse);
                        }
                        iaResponse.add(iaHelper, trxTypeId, trxStatusId);
                    }
                }
            }
        }
        return iaMap.values();
    }

    @Override
    public Collection<ChartResponse> getFundingBySector(Parameters params) {
        Map<Long, ChartResponse> sectorMap = new HashMap<>();
        for (TransactionTypeEnum trxTypeId : TransactionTypeEnum.values()) {
            for (TransactionStatusEnum trxStatusId : TransactionStatusEnum.values()) {
                List<SectorResultsQueryHelper> sectorResults = sectorRepository.findFundingBySector(params, trxTypeId.getId(), trxStatusId.getId());

                for (SectorResultsQueryHelper sectorHelper : sectorResults) {
                    Sector sector = sectorHelper.getSector();
                    ChartResponse fundingAgencyResponse;
                    if (sectorMap.get(sector.getId()) != null) {
                        fundingAgencyResponse = sectorMap.get(sector.getId());
                    } else {
                        fundingAgencyResponse = new ChartResponse(sector);
                        sectorMap.put(sector.getId(), fundingAgencyResponse);
                    }
                    fundingAgencyResponse.add(sectorHelper, trxTypeId, trxStatusId);
                }
            }
        }
        return sectorMap.values();
    }

    @Override
    public Collection<ChartResponse> getFundingByPhysicalStatus(Parameters params) {
        Map<Long, ChartResponse> phyStatusMap = new HashMap<>();

        for (TransactionTypeEnum trxTypeId : TransactionTypeEnum.values()) {
            for (TransactionStatusEnum trxStatusId : TransactionStatusEnum.values()) {
                List<PhysicalStatusQueryHelper> phyStatusResults = physicalStatusRepository
                        .findFundingByPhysicalStatus(params, trxTypeId.getId(), trxStatusId.getId());

                for (PhysicalStatusQueryHelper phyStatusHelper : phyStatusResults) {
                    PhysicalStatus physicalStatus = phyStatusHelper.getPhysicalStatus();
                    ChartResponse fundingAgencyResponse;
                    if (phyStatusMap.get(physicalStatus.getId()) != null) {
                        fundingAgencyResponse = phyStatusMap.get(physicalStatus.getId());
                    } else {
                        fundingAgencyResponse = new ChartResponse(physicalStatus);
                        phyStatusMap.put(physicalStatus.getId(), fundingAgencyResponse);
                    }
                    fundingAgencyResponse.add(phyStatusHelper, trxTypeId, trxStatusId);
                }
            }
        }


        return phyStatusMap.values();
    }
}
