package org.devgateway.geoph.persistence;

import org.devgateway.geoph.model.FundingAgency;
import org.devgateway.geoph.model.ImplementingAgency;
import org.devgateway.geoph.model.Sector;
import org.devgateway.geoph.persistence.repository.FundingAgencyRepository;
import org.devgateway.geoph.persistence.repository.ImplementingAgencyRepository;
import org.devgateway.geoph.persistence.repository.SectorRepository;
import org.devgateway.geoph.services.ChartService;
import org.devgateway.geoph.util.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


import static org.devgateway.geoph.util.Constants.DOUBLE_FORMAT;

/**
 * @author dbianco
 *         created on abr 04 2016.
 */
@Service
public class ChartServiceImpl implements ChartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChartServiceImpl.class);

    @Autowired
    FundingAgencyRepository fundingAgencyRepository;

    @Autowired
    ImplementingAgencyRepository implementingAgencyRepository;

    @Autowired
    SectorRepository sectorRepository;

    @Override
    public List<Map<String, String>> getFundingByFundingAgency(Parameters params) {
        List<Object> fundingAgenciesResults = fundingAgencyRepository.findFundingByFundingAgency(params);
        List<Map<String, String>> fundingAgenciesList = new ArrayList<>();
        for(Object o:fundingAgenciesResults) {
            Map<String, String> fundingAgencyMap = new HashMap<>();
            Object[] objectList = ((Object[]) o);
            FundingAgency fa = (FundingAgency)objectList[0];
            fundingAgencyMap.put("id", fa.getId().toString());
            fundingAgencyMap.put("name", fa.getName());
            fundingAgencyMap.put("code", fa.getCode());
            fundingAgencyMap.put("projectCount", objectList[1]!=null?objectList[1].toString():"");
            fundingAgencyMap.put("transactionCount", objectList[2]!=null?objectList[2].toString():"");
            fundingAgencyMap.put("loan", objectList[3] != null ? String.format(Locale.US, DOUBLE_FORMAT, ((Double) objectList[3])) : "");
            fundingAgencyMap.put("grant", objectList[4]!=null?String.format(Locale.US, DOUBLE_FORMAT, ((Double) objectList[4])):"");
            fundingAgencyMap.put("pmc", objectList[5]!=null?String.format(Locale.US, DOUBLE_FORMAT, ((Double) objectList[5])):"");
            fundingAgenciesList.add(fundingAgencyMap);
        }
        return fundingAgenciesList;
    }

    @Override
    public List<Map<String, String>> getFundingByImplementingAgency(Parameters params) {
        List<Object> impAgenciesResults = implementingAgencyRepository.findFundingByImplementingAgency(params);
        List<Map<String, String>> impAgenciesList = new ArrayList<>();
        for(Object o:impAgenciesResults) {
            Map<String, String> impAgencyMap = new HashMap<>();
            Object[] objectList = ((Object[]) o);
            ImplementingAgency ia = (ImplementingAgency)objectList[0];
            impAgencyMap.put("id", ia.getId().toString());
            impAgencyMap.put("name", ia.getName());
            impAgencyMap.put("code", ia.getCode());
            impAgencyMap.put("projectCount", objectList[1]!=null?objectList[1].toString():"");
            impAgencyMap.put("transactionCount", objectList[2]!=null?objectList[2].toString():"");
            impAgencyMap.put("loan", objectList[3] != null ? String.format(Locale.US, DOUBLE_FORMAT, ((Double) objectList[3])) : "");
            impAgencyMap.put("grant", objectList[4]!=null?String.format(Locale.US, DOUBLE_FORMAT, ((Double) objectList[4])):"");
            impAgencyMap.put("pmc", objectList[5]!=null?String.format(Locale.US, DOUBLE_FORMAT, ((Double) objectList[5])):"");
            impAgenciesList.add(impAgencyMap);
        }
        return impAgenciesList;
    }

    @Override
    public List<Map<String, String>> getFundingBySector(Parameters params) {
        List<Object> sectorResults = sectorRepository.findFundingBySector(params);
        List<Map<String, String>> sectorList = new ArrayList<>();
        for(Object o:sectorResults) {
            Map<String, String> sectorMap = new HashMap<>();
            Object[] objectList = ((Object[]) o);
            Sector sector = (Sector)objectList[0];
            sectorMap.put("id", sector.getId().toString());
            sectorMap.put("name", sector.getName());
            sectorMap.put("code", sector.getCode());
            sectorMap.put("projectCount", objectList[1]!=null?objectList[1].toString():"");
            sectorMap.put("transactionCount", objectList[2]!=null?objectList[2].toString():"");
            sectorMap.put("loan", objectList[3] != null ? String.format(Locale.US, DOUBLE_FORMAT, ((Double) objectList[3])) : "");
            sectorMap.put("grant", objectList[4]!=null?String.format(Locale.US, DOUBLE_FORMAT, ((Double) objectList[4])):"");
            sectorMap.put("pmc", objectList[5]!=null?String.format(Locale.US, DOUBLE_FORMAT, ((Double) objectList[5])):"");
            sectorList.add(sectorMap);
        }
        return sectorList;
    }
}
