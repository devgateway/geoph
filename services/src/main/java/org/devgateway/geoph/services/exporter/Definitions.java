package org.devgateway.geoph.services.exporter;

import org.devgateway.geoph.core.export.ColumnDefinition;
import org.devgateway.geoph.core.export.DefinitionsProvider;
import org.devgateway.geoph.core.export.Stylist;
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
        Stylist numberStyleStylist = stylists.getNumberStylist();
        Stylist regularStylist = stylists.getRegularStylist();
        Stylist decimalStylist = stylists.getDecimalStylist();
        Stylist dateStylist = stylists.getDateStylist();
        Stylist boldStyle = stylists.getBoldStylist();


        columnsDef.add(new ColumnDefinitionImp<Long>("Location ID", numberStyleStylist, Formatters.longFormatter(), Extractors.longExtractor("location.getId")));
        columnsDef.add(new ColumnDefinitionImp<String>("UACS Code", numberStyleStylist, Formatters.longFormatter(), Extractors.stringExtractor("location.getCode")));
        columnsDef.add(new ColumnDefinitionImp<String>("ADM Level", numberStyleStylist, Formatters.longFormatter(), Extractors.stringExtractor("location.getLevel")));
        columnsDef.add(new ColumnDefinitionImp<String>("Name", regularStylist, Formatters.stringFormatter(), Extractors.stringExtractor("location.getName")));
        columnsDef.add(new ColumnDefinitionImp<Double>("Latitude", decimalStylist, Formatters.stringFormatter(), Extractors.doubleExtractor("location.getLatitude")));
        columnsDef.add(new ColumnDefinitionImp<String>("Longitude", dateStylist, Formatters.stringFormatter(), Extractors.stringExtractor("location.getName")));

        columnsDef.add(new ColumnDefinitionImp<String>("Region", numberStyleStylist, Formatters.stringFormatter(), Extractors.stringExtractor("location.getRegionId")));

        columnsDef.add(new ColumnDefinitionImp<String>("Province", numberStyleStylist, Formatters.stringFormatter(), Extractors.stringExtractor("location.getProvinceId")));

        columnsDef.add(new ColumnDefinitionImp<String>("Project ID", numberStyleStylist, Formatters.stringFormatter(), Extractors.stringExtractor("project.getPhId")));

        columnsDef.add(new ColumnDefinitionImp<String>("Title", boldStyle, Formatters.stringFormatter(), Extractors.stringExtractor("project.getTitle")));


        columnsDef.add(new ColumnDefinitionImp<List<String>>("Implementing Agency", regularStylist, Formatters.stringFormatter(), Extractors.implementingAgencyExtractor("project.getImplementingAgencies")));

        columnsDef.add(new ColumnDefinitionImp<String>("Executing Agency", regularStylist, Formatters.stringFormatter(), Extractors.agencyExtractor("project.getExecutingAgency")));
        columnsDef.add(new ColumnDefinitionImp<String>("Funding Institution", regularStylist, Formatters.stringFormatter(), Extractors.agencyExtractor("project.getFundingAgency")));
        columnsDef.add(new ColumnDefinitionImp<String>("Original Currency (OC)", regularStylist, Formatters.stringFormatter(), Extractors.currencyExtractor("project.getOriginalCurrency")));
        columnsDef.add(new ColumnDefinitionImp<String>("Amount in OC", decimalStylist, Formatters.stringFormatter(), Extractors.stringExtractor("project.getTotalProjectAmount")));


        columnsDef.add(new ColumnDefinitionImp<String>("Start Date", dateStylist, Formatters.stringFormatter(), Extractors.stringExtractor("project.getStartDate")));
        columnsDef.add(new ColumnDefinitionImp<String>("Closing Date", dateStylist, Formatters.stringFormatter(), Extractors.stringExtractor("project.getEndDate")));
        columnsDef.add(new ColumnDefinitionImp<String>("Revised Closing Date", dateStylist, Formatters.stringFormatter(), Extractors.stringExtractor("project.getRevisedClosingDate")));

        columnsDef.add(new ColumnDefinitionImp<List<String>>("Sectors", regularStylist, Formatters.stringFormatter(), Extractors.sectorExtractor("project.getSectors")));


        columnsDef.add(new ColumnDefinitionImp<String>("Period of Performance Start", dateStylist, Formatters.stringFormatter(), Extractors.stringExtractor("project.getPeriodPerformanceStart")));
        columnsDef.add(new ColumnDefinitionImp<String>("Period of Performance End", dateStylist, Formatters.stringFormatter(), Extractors.stringExtractor("project.getPeriodPerformanceEnd")));


        columnsDef.add(new ColumnDefinitionImp<String>("Status", regularStylist, Formatters.stringFormatter(), Extractors.getStatusExtractor("project.getStatus")));
        columnsDef.add(new ColumnDefinitionImp<String>("Physical Status", regularStylist, Formatters.stringFormatter(), Extractors.stringExtractor("project.getPhysicalStatus")));
        // columnsDef.add(new ColumnDefinitionImp<String>("Physical Progress(Actual)", regularStyle, Formatters.stringFormatter(), Extractors.stringExtractor("location.getName")));
        //  columnsDef.add(new ColumnDefinitionImp<String>("Physical Progress(Target)", regularStyle, Formatters.stringFormatter(), Extractors.stringExtractor("location.getName")));

        columnsDef.add(new ColumnDefinitionImp<String>("Grant Classification", regularStylist, Formatters.stringFormatter(), Extractors.stringExtractor("project.getGrantClassification")));
        //columnsDef.add(new ColumnDefinitionImp<String>("Total Disbursements", regularStyle, Formatters.stringFormatter(), Extractors.stringExtractor("location.getTransactions")));
        ///columnsDef.add(new ColumnDefinitionImp<String>("Total Commitments", regularStyle, Formatters.stringFormatter(), Extractors.stringExtractor("location.getTransactions")));


        return columnsDef;
    }
}
