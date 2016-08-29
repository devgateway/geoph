package org.devgateway.geoph.services.exporter;

import org.devgateway.geoph.core.export.ColumnDefinition;
import org.devgateway.geoph.core.export.DefinitionsProvider;
import org.devgateway.geoph.core.export.Stylist;
import org.devgateway.geoph.core.export.Stylists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.devgateway.geoph.core.constants.Constants.ABSTRACT_PERSISTABLE_CLASSNAME;
import static org.devgateway.geoph.core.constants.Constants.LOCATION_CLASSNAME;

/**
 * @author dbianco
 *         created on jun 16 2016.
 */
@Service("indicatorTemplateDefinitions")
public class IndicatorTemplateDefinitions implements DefinitionsProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationProjectDefinitions.class);

    private String wbName;

    private Stylists stylists;

    public IndicatorTemplateDefinitions() {
        this.wbName = UUID.randomUUID().toString();
    }

    public IndicatorTemplateDefinitions(Stylists stylists) {
        this.stylists = stylists;
        this.wbName = UUID.randomUUID().toString();
    }

    @Override
    public DefinitionsProvider getNewInstance(Stylists stylists) {
        return new IndicatorTemplateDefinitions(stylists);
    }

    @Override
    public List<ColumnDefinition> getColumnsDefinitions() {
        LOGGER.debug("getColumnsDefinitions for wb: " + wbName);

        List<ColumnDefinition> columnsDef = new ArrayList<>();
        Stylist numberStyleStylist = stylists.getNumberStylist(wbName);
        Stylist regularStylist = stylists.getRegularStylist(wbName);

        columnsDef.add(new ColumnDefinitionImp<Long>("Location ID", numberStyleStylist, Formatters.longFormatter(), Extractors.longExtractor("location.getId")));
        columnsDef.add(new ColumnDefinitionImp<String>("Location Name", regularStylist, Formatters.stringFormatter(), Extractors.stringExtractor("location.getName")));
        columnsDef.add(new ColumnDefinitionImp<String>("UACS Code", regularStylist, Formatters.stringFormatter(), Extractors.stringExtractor("location.getCode")));
        columnsDef.add(new ColumnDefinitionImp<String>("Indicator Value", regularStylist, Formatters.stringFormatter(), null));

        return columnsDef;
    }

    @Override
    public Map<String, List<String>> getMethodsToInvoke(){
        Map<String, List<String>> classMap = new HashMap<>();

        List<String> abstractPersistableList = new ArrayList<>();
        abstractPersistableList.add("getId");
        classMap.put(ABSTRACT_PERSISTABLE_CLASSNAME, abstractPersistableList);

        List<String> locationList = new ArrayList<>();
        locationList.add("getCode");
        locationList.add("getName");
        classMap.put(LOCATION_CLASSNAME, locationList);

        return classMap;
    }
}
