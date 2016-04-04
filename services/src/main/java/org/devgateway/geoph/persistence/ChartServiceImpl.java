package org.devgateway.geoph.persistence;

import org.devgateway.geoph.model.FundingAgency;
import org.devgateway.geoph.persistence.repository.FundingAgencyRepository;
import org.devgateway.geoph.services.ChartService;
import org.devgateway.geoph.util.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
            fundingAgencyMap.put("loan", objectList[3] != null ? String.format(DOUBLE_FORMAT, ((Double) objectList[3])) : "");
            fundingAgencyMap.put("grant", objectList[4]!=null?String.format(DOUBLE_FORMAT, ((Double) objectList[4])):"");
            fundingAgencyMap.put("pmc", objectList[5]!=null?String.format(DOUBLE_FORMAT, ((Double) objectList[5])):"");
            fundingAgenciesList.add(fundingAgencyMap);
        }
        return fundingAgenciesList;
    }
}
