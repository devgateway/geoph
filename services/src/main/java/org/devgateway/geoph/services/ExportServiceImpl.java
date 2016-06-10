package org.devgateway.geoph.services;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.services.ExportService;
import org.devgateway.geoph.core.services.LocationService;
import org.devgateway.geoph.model.Location;
import org.devgateway.geoph.services.exporter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
@Service
public class ExportServiceImpl implements ExportService {

    private XLSGenerator generator;

    @Autowired
    LocationService locationService;

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


    private void writeHeaders(RawRow row) {
        generator.writeHeaders(this.columnsDef);
    }

    private void writeRow(RawRow row) {
        generator.writeRow(row);
    }

    private RawRow getRow(Map<String, Object> properties) {
        RawRow row = new RawRow();
        columnsDef.forEach(colDef -> {
            row.addCell(colDef.getCell(colDef.getExtractor().extract(properties))); //TODO:format?
        });
        return row;
    }

    public File export(Parameters parameters) throws IOException {
        generator = new XLSGenerator();

        List<Location> locationList = locationService.getLocationsForExport(parameters);
        columnsDef.add(new ColumnDefinition<Long>("Location ID", Formatters.longFormatter(), Extractors.longExtractor("location.getId")));
        columnsDef.add(new ColumnDefinition<String>("UACS Code", Formatters.stringFormatter(), Extractors.stringExtractor("location.getCode")));
        columnsDef.add(new ColumnDefinition<String>("ADM Level", Formatters.stringFormatter(), Extractors.stringExtractor("location.getLevel")));
        columnsDef.add(new ColumnDefinition<String>("Name", Formatters.stringFormatter(), Extractors.stringExtractor("location.getName")));
        columnsDef.add(new ColumnDefinition<String>("Latitude", Formatters.stringFormatter(), Extractors.stringExtractor("location.getLatitude")));

        generator.writeHeaders(columnsDef);

    /*      columnsDef.add(new ColumnDefinition<Double>("Latitude"));
            columnsDef.add(new ColumnDefinition<Double>("Longitude"));
            columnsDef.add(new ColumnDefinition<String>("Region"));
            columnsDef.add(new ColumnDefinition<String>("Province"));
            columnsDef.add(new ColumnDefinition<Long>("Project ID"));
            columnsDef.add(new ColumnDefinition<String>("Title"));
            columnsDef.add(new ColumnDefinition<String>("Implementing Agency"));
            columnsDef.add(new ColumnDefinition<String>("Executing Agency"));
            columnsDef.add(new ColumnDefinition<String>("Funding Institution"));
            columnsDef.add(new ColumnDefinition<String>("Original Currency (OC)"));
            columnsDef.add(new ColumnDefinition<Double>("Amount in OC"));
            columnsDef.add(new ColumnDefinition<Date>("Start Date"));
            columnsDef.add(new ColumnDefinition<Date>("Closing Date"));
            columnsDef.add(new ColumnDefinition<Date>("Revised Closing Date"));
            columnsDef.add(new ColumnDefinition<String>("Sectors"));
            columnsDef.add(new ColumnDefinition<Date>("Period of Performance Start"));
            columnsDef.add(new ColumnDefinition<Date>("Period of Performance End"));
            columnsDef.add(new ColumnDefinition<String>("Status"));
            columnsDef.add(new ColumnDefinition<String>("Physical Status"));
            columnsDef.add(new ColumnDefinition<String>("Physical Progress(Actual)"));
            columnsDef.add(new ColumnDefinition<String>("Physical Progress(Target)"));
            columnsDef.add(new ColumnDefinition<String>("Grant Classification"));
            columnsDef.add(new ColumnDefinition<Double>("Total Disbursements"));
            columnsDef.add(new ColumnDefinition<Double>("Total Commitments"));
    */
        locationList.forEach(location -> {
            Map<String, Object> properties = this.toKeyValuePairs(location);
            location.getProjects().forEach(project -> {
                properties.putAll(this.toKeyValuePairs(project));
                writeRow(getRow(properties));
            });
        });

        return generator.writeFile();


    }
}

