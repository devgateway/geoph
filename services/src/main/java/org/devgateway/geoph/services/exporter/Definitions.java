package org.devgateway.geoph.services.exporter;

import org.devgateway.geoph.core.export.ColumnDefinition;
import org.devgateway.geoph.core.export.DefinitionsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian Dimunzio on 6/13/2016.
 */
@Service
public class Definitions implements DefinitionsProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(Definitions.class);

    @Autowired
    Stylists stylists;
    public List<ColumnDefinition> getColumnsDefinitions() {
        List<ColumnDefinition> columnsDef = new ArrayList<>();

        columnsDef.add(new ColumnDefinitionImp<Long>("Location ID", stylists.getNumberStyle(), Formatters.longFormatter(), Extractors.longExtractor("location.getId")));
        columnsDef.add(new ColumnDefinitionImp<String>("UACS Code", stylists.getNumberStyle(), Formatters.longFormatter(), Extractors.stringExtractor("location.getCode")));
        columnsDef.add(new ColumnDefinitionImp<String>("ADM Level", stylists.getNumberStyle(), Formatters.longFormatter(), Extractors.stringExtractor("location.getLevel")));
        columnsDef.add(new ColumnDefinitionImp<String>("Name", stylists.getRegularStyle(), Formatters.stringFormatter(), Extractors.stringExtractor("location.getName")));
        columnsDef.add(new ColumnDefinitionImp<Double>("Latitude", stylists.getDecimalStyle(), Formatters.stringFormatter(), Extractors.doubleExtractor("location.getLatitude")));

        columnsDef.add(new ColumnDefinitionImp<Long>("Project ID", stylists.getNumberStyle(), Formatters.longFormatter(), Extractors.longExtractor("project.getId")));
        columnsDef.add(new ColumnDefinitionImp<String>("Project Title", stylists.getRegularStyle(), Formatters.stringFormatter(), Extractors.stringExtractor("project.getTitle")));



    /*      columnsDef.add(new ColumnDefinitionImp<Double>("Latitude"));
            columnsDef.add(new ColumnDefinitionImp<Double>("Longitude"));
            columnsDef.add(new ColumnDefinitionImp<String>("Region"));
            columnsDef.add(new ColumnDefinitionImp<String>("Province"));
            columnsDef.add(new ColumnDefinitionImp<Long>("Project ID"));
            columnsDef.add(new ColumnDefinitionImp<String>("Title"));
            columnsDef.add(new ColumnDefinitionImp<String>("Implementing Agency"));
            columnsDef.add(new ColumnDefinitionImp<String>("Executing Agency"));
            columnsDef.add(new ColumnDefinitionImp<String>("Funding Institution"));
            columnsDef.add(new ColumnDefinitionImp<String>("Original Currency (OC)"));
            columnsDef.add(new ColumnDefinitionImp<Double>("Amount in OC"));
            columnsDef.add(new ColumnDefinitionImp<Date>("Start Date"));
            columnsDef.add(new ColumnDefinitionImp<Date>("Closing Date"));
            columnsDef.add(new ColumnDefinitionImp<Date>("Revised Closing Date"));
            columnsDef.add(new ColumnDefinitionImp<String>("Sectors"));
            columnsDef.add(new ColumnDefinitionImp<Date>("Period of Performance Start"));
            columnsDef.add(new ColumnDefinitionImp<Date>("Period of Performance End"));
            columnsDef.add(new ColumnDefinitionImp<String>("Status"));
            columnsDef.add(new ColumnDefinitionImp<String>("Physical Status"));
            columnsDef.add(new ColumnDefinitionImp<String>("Physical Progress(Actual)"));
            columnsDef.add(new ColumnDefinitionImp<String>("Physical Progress(Target)"));
            columnsDef.add(new ColumnDefinitionImp<String>("Grant Classification"));
            columnsDef.add(new ColumnDefinitionImp<Double>("Total Disbursements"));
            columnsDef.add(new ColumnDefinitionImp<Double>("Total Commitments"));
    */

        return columnsDef;
    }
}
