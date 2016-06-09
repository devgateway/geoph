package org.devgateway.geoph.services;

import org.devgateway.geoph.model.FundingAgency;
import org.devgateway.geoph.model.ImplementingAgency;
import org.devgateway.geoph.model.PhysicalStatus;
import org.devgateway.geoph.model.Sector;
import org.devgateway.geoph.persistence.ChartService;
import org.devgateway.geoph.persistence.repository.FundingAgencyRepository;
import org.devgateway.geoph.persistence.repository.ImplementingAgencyRepository;
import org.devgateway.geoph.persistence.repository.PhysicalStatusRepository;
import org.devgateway.geoph.persistence.repository.SectorRepository;
import org.devgateway.geoph.util.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.devgateway.geoph.util.Constants.*;


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
    public List<Map<String, Object>> getFundingByFundingAgency(Parameters params) {
        List<Object> fundingAgenciesResults = fundingAgencyRepository.findFundingByFundingAgency(params);
        List<Map<String, Object>> fundingAgenciesList = new ArrayList<>();
        for(Object o:fundingAgenciesResults) {
            Map<String, Object> fundingAgencyMap = new HashMap<>();
            Object[] objectList = ((Object[]) o);
            FundingAgency fa = (FundingAgency)objectList[0];
            fundingAgencyMap.put("id", fa.getId().toString());
            fundingAgencyMap.put("name", fa.getName());
            fundingAgencyMap.put("code", fa.getCode());
            addFundingValues(fundingAgencyMap, objectList);
            fundingAgenciesList.add(fundingAgencyMap);
        }
        return fundingAgenciesList;
    }

    private void addFundingValues(Map<String, Object> chartMap, Object[] objectList) {
        chartMap.put("projectCount", objectList[1]!=null?objectList[1].toString():"");
        long[] transactions = new long[]{(Long)objectList[3], (Long)objectList[5], (Long)objectList[7],
                (Long)objectList[9], (Long)objectList[11], (Long)objectList[13],
                (Long)objectList[15], (Long)objectList[17], (Long)objectList[19],};
        long count = 0;
        for(long t:transactions){
            if(t>0){
                count+=t;
            }
        }
        Map<String, String> commitments = new HashMap<>();
        commitments.put(PROPERTY_LOC_TARGET, String.format(Locale.US, DOUBLE_FORMAT, transactions[0]>0?(Double)objectList[2]:0));
        commitments.put(PROPERTY_LOC_ACTUAL, String.format(Locale.US, DOUBLE_FORMAT, transactions[1]>0?(Double)objectList[4]:0));
        commitments.put(PROPERTY_LOC_CANCELLED, String.format(Locale.US, DOUBLE_FORMAT, transactions[2]>0?(Double)objectList[6]:0));
        chartMap.put(PROPERTY_LOC_COMMITMENTS, commitments);

        Map<String, String> disbursements = new HashMap<>();
        disbursements.put(PROPERTY_LOC_TARGET, String.format(Locale.US, DOUBLE_FORMAT, transactions[3]>0?(Double)objectList[8]:0));
        disbursements.put(PROPERTY_LOC_ACTUAL, String.format(Locale.US, DOUBLE_FORMAT, transactions[4]>0?(Double)objectList[10]:0));
        disbursements.put(PROPERTY_LOC_CANCELLED, String.format(Locale.US, DOUBLE_FORMAT, transactions[5]>0?(Double)objectList[12]:0));
        chartMap.put(PROPERTY_LOC_DISBURSEMENTS, disbursements);

        Map<String, String> expenditures = new HashMap<>();
        expenditures.put(PROPERTY_LOC_TARGET, String.format(Locale.US, DOUBLE_FORMAT, transactions[6] > 0 ? (Double) objectList[14] : 0));
        expenditures.put(PROPERTY_LOC_ACTUAL, String.format(Locale.US, DOUBLE_FORMAT, transactions[7] > 0 ? (Double) objectList[16] : 0));
        expenditures.put(PROPERTY_LOC_CANCELLED, String.format(Locale.US, DOUBLE_FORMAT, transactions[8] > 0 ? (Double) objectList[18] : 0));
        chartMap.put(PROPERTY_LOC_EXPENDITURES, expenditures);

        chartMap.put("transactionCount", Long.toString(count));
    }

    @Override
    public List<Map<String, Object>> getFundingByImplementingAgency(Parameters params) {
        List<Object> impAgenciesResults = implementingAgencyRepository.findFundingByImplementingAgency(params);
        List<Map<String, Object>> impAgenciesList = new ArrayList<>();
        boolean showAll = false;
        Set<Long> iaParamsSet = new HashSet<>();
        if(params==null || params.getImpAgencies()==null || params.getImpAgencies().size()==0) {
            showAll = true;
        } else {
            for(Long iaId : params.getImpAgencies()){
                iaParamsSet.add(iaId);
            }
        }
        for(Object o:impAgenciesResults) {
            Object[] objectList = ((Object[]) o);
            ImplementingAgency ia = (ImplementingAgency) objectList[0];
            if(showAll || iaParamsSet.contains(ia.getId())) {
                Map<String, Object> impAgencyMap = new HashMap<>();
                impAgencyMap.put("id", ia.getId().toString());
                impAgencyMap.put("name", ia.getName());
                impAgencyMap.put("code", ia.getCode());
                addFundingValues(impAgencyMap, objectList);
                impAgenciesList.add(impAgencyMap);
            }
        }
        return impAgenciesList;
    }

    @Override
    public List<Map<String, Object>> getFundingBySector(Parameters params) {
        List<Object> sectorResults = sectorRepository.findFundingBySector(params);
        List<Map<String, Object>> sectorList = new ArrayList<>();
        for(Object o:sectorResults) {
            Map<String, Object> sectorMap = new HashMap<>();
            Object[] objectList = ((Object[]) o);
            Sector sector = (Sector)objectList[0];
            sectorMap.put("id", sector.getId().toString());
            sectorMap.put("name", sector.getName());
            sectorMap.put("code", sector.getCode());
            addFundingValues(sectorMap, objectList);
            sectorList.add(sectorMap);
        }
        return sectorList;
    }

    @Override
    public List<Map<String, Object>> getFundingByPhysicalStatus(Parameters params) {
        List<Object> physicalStatusResults = physicalStatusRepository.findFundingByPhysicalStatus(params);
        List<Map<String, Object>> physicalStatusList = new ArrayList<>();
        for(Object o:physicalStatusResults) {
            Map<String, Object> physicalStatusMap = new HashMap<>();
            Object[] objectList = ((Object[]) o);
            PhysicalStatus physicalStatus = (PhysicalStatus)objectList[0];
            physicalStatusMap.put("id", physicalStatus.getId().toString());
            physicalStatusMap.put("name", physicalStatus.getName());
            physicalStatusMap.put("code", physicalStatus.getCode());
            addFundingValues(physicalStatusMap, objectList);
            physicalStatusList.add(physicalStatusMap);
        }
        return physicalStatusList;
    }
}
