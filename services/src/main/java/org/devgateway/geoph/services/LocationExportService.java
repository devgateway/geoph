package org.devgateway.geoph.services;

import org.devgateway.geoph.core.export.ColumnDefinition;
import org.devgateway.geoph.core.export.Generator;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.services.ExportService;
import org.devgateway.geoph.core.services.FileService;
import org.devgateway.geoph.core.services.LocationService;
import org.devgateway.geoph.model.Location;
import org.devgateway.geoph.services.exporter.ColumnDefinitionImp;
import org.devgateway.geoph.services.exporter.Extractors;
import org.devgateway.geoph.services.exporter.Formatters;
import org.devgateway.geoph.services.exporter.RawRowImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
@Service
public class LocationExportService implements ExportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportService.class);

    private Generator generator;

    @Autowired
    LocationService locationService;

    @Autowired
    FileService fileService;

    List<ColumnDefinition> columnsDef = new ArrayList<>();

    private Map<String, Object> toKeyValuePairs(Object instance) {
        return Arrays.stream(instance.getClass().getDeclaredMethods())
                .collect(Collectors.toMap(method -> {
                    return instance.getClass().getSimpleName().toLowerCase() + '.' + method.getName();
                }, m -> {
                    try {
                        Object result = m.invoke(instance);
                        return result != null ? result : "";
                    } catch (Exception e) {
                        return "";
                    }
                }));

        //TODO:add methods from base class
    }


    private RawRowImpl getRow(Map<String, Object> properties) {
        RawRowImpl row = new RawRowImpl();
        columnsDef.forEach(colDef -> {
            row.addCell(colDef.getCell(colDef.getExtractor().extract(properties))); //TODO:format?
        });
        return row;
    }

    public String export(Generator generator, Parameters parameters) throws Exception {
        LOGGER.info("Querying Locations");
        List<Location> locationList = locationService.getLocationsForExport(parameters);
        columnsDef.add(new ColumnDefinitionImp<Long>("Location ID", Formatters.longFormatter(), Extractors.longExtractor("location.getId")));
        columnsDef.add(new ColumnDefinitionImp<String>("UACS Code", Formatters.stringFormatter(), Extractors.stringExtractor("location.getCode")));
        columnsDef.add(new ColumnDefinitionImp<String>("ADM Level", Formatters.stringFormatter(), Extractors.stringExtractor("location.getLevel")));
        columnsDef.add(new ColumnDefinitionImp<String>("Name", Formatters.stringFormatter(), Extractors.stringExtractor("location.getName")));
        columnsDef.add(new ColumnDefinitionImp<String>("Latitude", Formatters.stringFormatter(), Extractors.stringExtractor("location.getLatitude")));



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
        LOGGER.info("Writing header");
        generator.writeHeaders(columnsDef);
        locationList.forEach(location -> {
            Map<String, Object> properties = this.toKeyValuePairs(location);
            location.getProjects().forEach(project -> {
                properties.putAll(this.toKeyValuePairs(project));

                LOGGER.info("Writing row");
                generator.writeRow(getRow(properties));
            });
        });


        LOGGER.info("Writing file");
        String name = UUID.randomUUID() + ".xls";

        File file = fileService.createFile(name, true);
        generator.toFile(file);

        return name;

    }


}

