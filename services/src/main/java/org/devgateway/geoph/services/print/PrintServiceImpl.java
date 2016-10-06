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
    private static final String NAME = "name";
    private static final String LEGENDS = "legends";
    private static final String LABEL = "label";
    private static final String COLOR = "color";

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

    @Autowired
    ClimateChangeRepository climateChangeRepository;

    @Autowired
    GenderResponsivenessRepository genderResponsivenessRepository;

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
                    ret.put("Physical Statuses", inner);

                } else if(filterStr.equals("gr")){
                    Set<String> inner = new HashSet<>();
                    List list = (List)jsonFilters.get(filterStr);
                    for(Object idObj:list) {
                        Integer id = (Integer) idObj;
                        GenderResponsiveness fa = genderResponsivenessRepository.findOne(id.longValue());
                        inner.add(fa.getName());
                    }
                    ret.put("Gender Responsiveness", inner);

                }  else if(filterStr.equals("cc")){
                    Set<String> inner = new HashSet<>();
                    List list = (List)jsonFilters.get(filterStr);
                    for(Object idObj:list) {
                        Integer id = (Integer) idObj;
                        ClimateChange fa = climateChangeRepository.findOne(id.longValue());
                        inner.add(fa.getName());
                    }
                    ret.put("Climate Change", inner);

                } else if(filterStr.equals("dt_start_min")){
                    Set<String> inner = new HashSet<>();
                    inner.add(jsonFilters.get("dt_start_min").toString() + "/" + jsonFilters.get("dt_start_max").toString());
                    ret.put("Implementation Period Start", inner);

                } else if(filterStr.equals("dt_end_min")){
                    Set<String> inner = new HashSet<>();
                    inner.add(jsonFilters.get("dt_end_min").toString() + "/" + jsonFilters.get("dt_end_max").toString());
                    ret.put("Implementation Period End", inner);

                }  else if(filterStr.equals("pp_start_min")){
                    Set<String> inner = new HashSet<>();
                    inner.add(jsonFilters.get("pp_start_min").toString() + "/" + jsonFilters.get("pp_start_max").toString());
                    ret.put("Loan-Grant Validity Period Start", inner);

                } else if(filterStr.equals("pp_end_min")){
                    Set<String> inner = new HashSet<>();
                    inner.add(jsonFilters.get("pp_end_min").toString() + "/" + jsonFilters.get("pp_end_max").toString());
                    ret.put("Loan-Grant Validity Period End", inner);

                } else if(filterStr.equals("fin_amount_min")){
                    Set<String> inner = new HashSet<>();
                    inner.add(jsonFilters.get("fin_amount_min").toString() + " - " + jsonFilters.get("fin_amount_max").toString());
                    ret.put("Financial Amount", inner);

                } else if(filterStr.equals("to_min")){
                    Set<String> inner = new HashSet<>();
                    inner.add(jsonFilters.get("to_min").toString() + "% - " + jsonFilters.get("to_max").toString() + "%");
                    ret.put("Physical Progress Target", inner);

                } else if(filterStr.equals("ao_min")){
                    Set<String> inner = new HashSet<>();
                    inner.add(jsonFilters.get("ao_min").toString() + "% - " + jsonFilters.get("ao_max").toString() + "%");
                    ret.put("Physical Progress Actual", inner);

                } else if(filterStr.equals("pt")){
                    Set<String> inner = new HashSet<>();
                    inner.add(jsonFilters.get("pt").toString());
                    ret.put("Project Title", inner);

                }
            }
        } catch (Exception e){
            LOGGER.error("Error reading filters " + e.getMessage());
        }
        return ret;
    }

    @Override
    public Map<String, List<Map <String, String>>> getLayerNamesFromJson(List jsonLayers) {
        Map ret = new HashMap<>();
        for(Object layerObj: jsonLayers){
            Map layerMap = (Map) layerObj;
            String name = (String)layerMap.get(NAME);
            List<Map <String, Map>> legendsList = new ArrayList<>();
            for(Object legendObj:(List)layerMap.get(LEGENDS)){
                Map legendMap = (Map) legendObj;
                Map legend = new HashMap<>();
                legend.put(LABEL, legendMap.get(LABEL));
                legend.put(COLOR, legendMap.get(COLOR));
                legendsList.add(legend);
            }
            ret.put(name, legendsList);
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
