package org.devgateway.geoph.services.print;

import org.devgateway.geoph.core.repositories.*;
import org.devgateway.geoph.core.services.PrintService;
import org.devgateway.geoph.enums.FlowTypeEnum;
import org.devgateway.geoph.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.devgateway.geoph.core.constants.Constants.FLOW_TYPE_ID_SEPARATOR;

/**
 * @author dbianco
 *         created on ago 08 2016.
 */
@Service
public class PrintServiceImpl implements PrintService {


    private static final Logger LOGGER = LoggerFactory.getLogger(PrintServiceImpl.class);

    @Autowired
    ImplementingAgencyRepository impAgencyRepository;

    @Autowired
    FundingAgencyRepository fundingAgencyRepository;

    @Autowired
    ExecutingAgencyRepository executingAgencyRepository;

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    GrantSubTypeRepository grantSubTypeRepository;

    @Autowired
    PhysicalStatusRepository physicalStatusRepository;

    @Override
    public Map<String, Set<String>> getFilterNamesFromJson(Map jsonFilters) {
        Map<String, Set<String>> ret = new HashMap<>();
        try {
            for(Object filterObj:jsonFilters.keySet()){
                String filterStr = (String) filterObj;
                if(filterStr.equals("ia")){
                    Set<String> inner = new HashSet<>();
                    List list = (List)jsonFilters.get(filterStr);
                    for(Object idObj:list) {
                        Integer id = (Integer) idObj;
                        ImplementingAgency ia = impAgencyRepository.findById(id.longValue());
                        inner.add(ia.getName());
                    }
                    ret.put("Implementing Agencies", inner);
                } else if(filterStr.equals("fa")){
                    Set<String> inner = new HashSet<>();
                    List list = (List)jsonFilters.get(filterStr);
                    for(Object idObj:list) {
                        Integer id = (Integer) idObj;
                        FundingAgency fa = fundingAgencyRepository.findById(id.longValue());
                        inner.add(fa.getName());
                    }
                    ret.put("Funding Agencies", inner);
                } else if(filterStr.equals("ea")){
                    Set<String> inner = new HashSet<>();
                    List list = (List)jsonFilters.get(filterStr);
                    for(Object idObj:list) {
                        Integer id = (Integer) idObj;
                        ExecutingAgency fa = executingAgencyRepository.findById(id.longValue());
                        inner.add(fa.getName());
                    }
                    ret.put("Executing Agencies", inner);
                } else if(filterStr.equals("st")){
                    Set<String> inner = new HashSet<>();
                    List list = (List)jsonFilters.get(filterStr);
                    for(Object idObj:list) {
                        Integer id = (Integer) idObj;
                        Sector fa = sectorRepository.findById(id.longValue());
                        inner.add(fa.getName());
                    }
                    ret.put("Sectors", inner);
                } else if(filterStr.equals("sa")){
                    Set<String> inner = new HashSet<>();
                    List list = (List)jsonFilters.get(filterStr);
                    for(Object idObj:list) {
                        Integer id = (Integer) idObj;
                        Status fa = statusRepository.findById(id.longValue());
                        inner.add(fa.getName());
                    }
                    ret.put("Statuses", inner);
                } else if(filterStr.equals("ft")){
                    List list = (List)jsonFilters.get(filterStr);
                    Set<String> inner = getFlowTypes(list);
                    ret.put("Funding Types", inner);
                } else if(filterStr.equals("ph")){
                    Set<String> inner = new HashSet<>();
                    List list = (List)jsonFilters.get(filterStr);
                    for(Object idObj:list) {
                        Integer id = (Integer) idObj;
                        PhysicalStatus fa = physicalStatusRepository.findById(id.longValue());
                        inner.add(fa.getName());
                    }
                    ret.put("Statuses", inner);
                } else if(filterStr.equals("gr")){
                    Set<String> inner = new HashSet<>();
                    List list = (List)jsonFilters.get(filterStr);
                    for(Object idObj:list) {
                        Integer id = (Integer) idObj;
                        PhysicalStatus fa = physicalStatusRepository.findById(id.longValue());
                        inner.add(fa.getName());
                    }
                    ret.put("Physical Statuses", inner);
                }
            }
        } catch (Exception e){
            LOGGER.error("Error reading filters " + e.getMessage());
        }
        return ret;
    }

    Set<String> getFlowTypes(List filters){
        Set<String> flowTypes = new HashSet<>();
        for (Object filterObj : filters) {
            String filter = (String) filterObj;
            if (filter.indexOf(FLOW_TYPE_ID_SEPARATOR) > 0) {
                GrantSubType st = grantSubTypeRepository.findOne(Long.parseLong(filter.trim().substring(filter.indexOf(FLOW_TYPE_ID_SEPARATOR) + 1)));
                flowTypes.add(st.getName());
            } else {
                flowTypes.add(FlowTypeEnum.getEnumById(Integer.parseInt(filter)).name().toLowerCase());
            }
        }
        return flowTypes;
    }
}
