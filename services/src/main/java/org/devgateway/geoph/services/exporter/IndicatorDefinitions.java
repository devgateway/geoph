package org.devgateway.geoph.services.exporter;

import org.devgateway.geoph.core.export.ColumnDefinition;
import org.devgateway.geoph.core.export.DefinitionsProvider;
import org.devgateway.geoph.core.export.Stylist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dbianco
 *         created on jun 16 2016.
 */
@Service("indicatorDefinitions")
public class IndicatorDefinitions implements DefinitionsProvider {

    @Autowired
    Stylists stylists;

    @Override
    public List<ColumnDefinition> getColumnsDefinitions() {

        List<ColumnDefinition> columnsDef = new ArrayList<>();
        Stylist numberStyleStylist = stylists.getNumberStylist();
        Stylist regularStylist = stylists.getRegularStylist();

        columnsDef.add(new ColumnDefinitionImp<Long>("Location ID", numberStyleStylist, Formatters.longFormatter(), Extractors.longExtractor("indicatordetail.getLocationId")));
        columnsDef.add(new ColumnDefinitionImp<String>("UACS Code", regularStylist, Formatters.stringFormatter(), Extractors.stringExtractor("location.getCode")));
        columnsDef.add(new ColumnDefinitionImp<String>("Indicator Value", regularStylist, Formatters.stringFormatter(), Extractors.stringExtractor("indicatordetail.getValue")));

        return columnsDef;
    }
}
