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
    private static final String FUNDING_CLASSIFICATION = "Funding Classification";
    private static final String IMPLEMENTING_AGENCIES = "Implementing Agencies";
    private static final String FUNDING_AGENCIES = "Funding Agencies";
    private static final String EXECUTING_AGENCIES = "Executing Agencies";
    private static final String SECTORS = "Sectors";
    private static final String STATUSES = "Statuses";
    private static final String PHYSICAL_STATUSES = "Physical Statuses";
    private static final String GENDER_RESPONSIVENESS = "Gender Responsiveness";
    private static final String CLIMATE_CHANGE = "Climate Change";
    private static final String IMPLEMENTATION_PERIOD_START = "Implementation Period Start";
    private static final String IMPLEMENTATION_PERIOD_END = "Implementation Period End";
    private static final String LOAN_GRANT_VALIDITY_PERIOD_START = "Loan-Grant Validity Period Start";
    private static final String LOAN_GRANT_VALIDITY_PERIOD_END = "Loan-Grant Validity Period End";
    private static final String FINANCIAL_AMOUNT = "Financial Amount";
    private static final String PHYSICAL_PROGRESS_TARGET = "Physical Progress Target";
    private static final String PHYSICAL_PROGRESS_ACTUAL = "Physical Progress Actual";
    private static final String PROJECT_TITLE = "Project Title";
    private static final String FUNDING_TYPES = "Funding Types";

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
    ClassificationRepository classificationRepository;

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
                    ret.put(IMPLEMENTING_AGENCIES, inner);

                } else if(filterStr.equals("cl")){
                    Set<String> inner = new HashSet<>();
                    List list = (List)jsonFilters.get(filterStr);
                    for(Object idObj:list) {
                        Integer id = (Integer) idObj;
                        Classification cl = classificationRepository.findOne(id.longValue());
                        inner.add(cl.getName());
                    }
                    ret.put(FUNDING_CLASSIFICATION, inner);

                } else if(filterStr.equals("fa")){
                    Set<String> inner = new HashSet<>();
                    List list = (List)jsonFilters.get(filterStr);
                    for(Object idObj:list) {
                        Integer id = (Integer) idObj;
                        FundingAgency fa = fundingAgencyRepository.findById(id.longValue());
                        inner.add(fa.getName());
                    }
                    ret.put(FUNDING_AGENCIES, inner);

                } else if(filterStr.equals("ea")){
                    Set<String> inner = new HashSet<>();
                    List list = (List)jsonFilters.get(filterStr);
                    for(Object idObj:list) {
                        Integer id = (Integer) idObj;
                        ExecutingAgency fa = executingAgencyRepository.findById(id.longValue());
                        inner.add(fa.getName());
                    }
                    ret.put(EXECUTING_AGENCIES, inner);

                } else if(filterStr.equals("st")){
                    Set<String> inner = new HashSet<>();
                    List list = (List)jsonFilters.get(filterStr);
                    for(Object idObj:list) {
                        Integer id = (Integer) idObj;
                        Sector fa = sectorRepository.findById(id.longValue());
                        inner.add(fa.getName());
                    }
                    ret.put(SECTORS, inner);

                } else if(filterStr.equals("sa")){
                    Set<String> inner = new HashSet<>();
                    List list = (List)jsonFilters.get(filterStr);
                    for(Object idObj:list) {
                        Integer id = (Integer) idObj;
                        Status fa = statusRepository.findById(id.longValue());
                        inner.add(fa.getName());
                    }
                    ret.put(STATUSES, inner);

                } else if(filterStr.equals("ft")){
                    List list = (List)jsonFilters.get(filterStr);
                    Set<String> inner = getFlowTypes(list);
                    ret.put(FUNDING_TYPES, inner);

                } else if(filterStr.equals("ph")){
                    Set<String> inner = new HashSet<>();
                    List list = (List)jsonFilters.get(filterStr);
                    for(Object idObj:list) {
                        Integer id = (Integer) idObj;
                        PhysicalStatus fa = physicalStatusRepository.findById(id.longValue());
                        inner.add(fa.getName());
                    }
                    ret.put(PHYSICAL_STATUSES, inner);

                } else if(filterStr.equals("gr")){
                    Set<String> inner = new HashSet<>();
                    List list = (List)jsonFilters.get(filterStr);
                    for(Object idObj:list) {
                        Integer id = (Integer) idObj;
                        GenderResponsiveness fa = genderResponsivenessRepository.findOne(id.longValue());
                        inner.add(fa.getName());
                    }
                    ret.put(GENDER_RESPONSIVENESS, inner);

                }  else if(filterStr.equals("cc")){
                    Set<String> inner = new HashSet<>();
                    List list = (List)jsonFilters.get(filterStr);
                    for(Object idObj:list) {
                        Integer id = (Integer) idObj;
                        ClimateChange fa = climateChangeRepository.findOne(id.longValue());
                        inner.add(fa.getName());
                    }
                    ret.put(CLIMATE_CHANGE, inner);

                } else if(filterStr.equals("dt_start_min")){
                    Set<String> inner = new HashSet<>();
                    inner.add(jsonFilters.get("dt_start_min").toString() + "/" + jsonFilters.get("dt_start_max").toString());
                    ret.put(IMPLEMENTATION_PERIOD_START, inner);

                } else if(filterStr.equals("dt_end_min")){
                    Set<String> inner = new HashSet<>();
                    inner.add(jsonFilters.get("dt_end_min").toString() + "/" + jsonFilters.get("dt_end_max").toString());
                    ret.put(IMPLEMENTATION_PERIOD_END, inner);

                }  else if(filterStr.equals("pp_start_min")){
                    Set<String> inner = new HashSet<>();
                    inner.add(jsonFilters.get("pp_start_min").toString() + "/" + jsonFilters.get("pp_start_max").toString());
                    ret.put(LOAN_GRANT_VALIDITY_PERIOD_START, inner);

                } else if(filterStr.equals("pp_end_min")){
                    Set<String> inner = new HashSet<>();
                    inner.add(jsonFilters.get("pp_end_min").toString() + "/" + jsonFilters.get("pp_end_max").toString());
                    ret.put(LOAN_GRANT_VALIDITY_PERIOD_END, inner);

                } else if(filterStr.equals("fin_amount_min")){
                    Set<String> inner = new HashSet<>();
                    inner.add(jsonFilters.get("fin_amount_min").toString() + " - " + jsonFilters.get("fin_amount_max").toString());
                    ret.put(FINANCIAL_AMOUNT, inner);

                } else if(filterStr.equals("to_min")){
                    Set<String> inner = new HashSet<>();
                    inner.add(jsonFilters.get("to_min").toString() + "% - " + jsonFilters.get("to_max").toString() + "%");
                    ret.put(PHYSICAL_PROGRESS_TARGET, inner);

                } else if(filterStr.equals("ao_min")){
                    Set<String> inner = new HashSet<>();
                    inner.add(jsonFilters.get("ao_min").toString() + "% - " + jsonFilters.get("ao_max").toString() + "%");
                    ret.put(PHYSICAL_PROGRESS_ACTUAL, inner);

                } else if(filterStr.equals("pt")){
                    Set<String> inner = new HashSet<>();
                    inner.add(jsonFilters.get("pt").toString());
                    ret.put(PROJECT_TITLE, inner);

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
