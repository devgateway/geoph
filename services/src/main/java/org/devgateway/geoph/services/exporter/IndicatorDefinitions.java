package org.devgateway.geoph.services.exporter;

import org.devgateway.geoph.core.export.ColumnDefinition;
import org.devgateway.geoph.core.export.DefinitionsProvider;
import org.devgateway.geoph.core.export.Stylist;
import org.devgateway.geoph.core.export.Stylists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.devgateway.geoph.core.constants.Constants.INDICATOR_DETAIL_CLASSNAME;
import static org.devgateway.geoph.core.constants.Constants.LOCATION_CLASSNAME;

/**
 * @author dbianco
 *         created on jun 16 2016.
 */
@Service("indicatorDefinitions")
public class IndicatorDefinitions implements DefinitionsProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationProjectDefinitions.class);

    private Stylists stylists;

    private String wbName;

    public IndicatorDefinitions() {
        this.wbName = UUID.randomUUID().toString();
    }

    public IndicatorDefinitions(Stylists stylists) {
        this.wbName = UUID.randomUUID().toString();
        this.stylists = stylists;
    }

    @Override
    public DefinitionsProvider getNewInstance(Stylists stylists) {
        return new IndicatorDefinitions(stylists);
    }

    @Override
    public List<ColumnDefinition> getColumnsDefinitions() {
        LOGGER.debug("getColumnsDefinitions for wb: " + wbName);

        List<ColumnDefinition> columnsDef = new ArrayList<>();
        Stylist numberStyleStylist = stylists.getNumberStylist(wbName);
        Stylist regularStylist = stylists.getRegularStylist(wbName);

        columnsDef.add(new ColumnDefinitionImp<>("Location ID", numberStyleStylist, Formatters.longFormatter(), Extractors.longExtractor("indicatordetail.getLocationId")));
        columnsDef.add(new ColumnDefinitionImp<>("UACS Code", regularStylist, Formatters.stringFormatter(), Extractors.stringExtractor("location.getCode")));
        columnsDef.add(new ColumnDefinitionImp<>("Indicator Value", regularStylist, Formatters.stringFormatter(), Extractors.stringExtractor("indicatordetail.getValue")));

        return columnsDef;
    }

    @Override
    public Map<String, List<String>> getMethodsToInvoke(){
        Map<String, List<String>> classMap = new HashMap<>();
        List<String> indicatorDetailList = new ArrayList<>();
        indicatorDetailList.add("getLocationId");
        indicatorDetailList.add("getValue");
        classMap.put(INDICATOR_DETAIL_CLASSNAME, indicatorDetailList);

        List<String> locationList = new ArrayList<>();
        locationList.add("getCode");
        classMap.put(LOCATION_CLASSNAME, locationList);

        return classMap;
    }
}
