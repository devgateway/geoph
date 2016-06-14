package org.devgateway.geoph.services;

import org.devgateway.geoph.core.export.ColumnDefinition;
import org.devgateway.geoph.core.export.Generator;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.services.ExportService;
import org.devgateway.geoph.core.services.FileService;
import org.devgateway.geoph.core.services.LocationService;
import org.devgateway.geoph.model.Location;
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


    private Map<String, Object> toKeyValuePairs(Object instance) {
        Map<String, Object> ret = new HashMap<>();
        ret.putAll(Arrays.stream(instance.getClass().getDeclaredMethods())
                .collect(Collectors.toMap(method -> {
                    return instance.getClass().getSimpleName().toLowerCase() + '.' + method.getName();
                }, m -> {
                    try {
                        Object result = m.invoke(instance);
                        return result != null ? result : "";
                    } catch (Exception e) {
                        return "";
                    }
                })));
        ret.putAll(Arrays.stream(instance.getClass().getSuperclass().getDeclaredMethods())
                .collect(Collectors.toMap(method -> {
                    return instance.getClass().getSimpleName().toLowerCase() + '.' + method.getName();
                }, m -> {
                    try {
                        Object result = m.invoke(instance);
                        return result != null ? result : "";
                    } catch (Exception e) {
                        return "";
                    }
                })));

        return ret;
    }


    private RawRowImpl getRow(List<ColumnDefinition> columnsDef, Map<String, Object> properties) {
        RawRowImpl row = new RawRowImpl();
        columnsDef.forEach(colDef -> {
            row.addCell(colDef.getCell(colDef.getExtractor().extract(properties))); //TODO:format?
        });
        return row;
    }

    public String export(List<ColumnDefinition> columnsDef, Generator generator, Parameters parameters) throws Exception {

        LOGGER.info("Querying Locations");
        List<Location> locationList = locationService.getLocationsForExport(parameters);

        LOGGER.info("Writing header");
        generator.writeHeaders(columnsDef);
        /*Collect data into a raw format*/
        locationList.forEach(location -> {
            Map<String, Object> properties = this.toKeyValuePairs(location);
            location.getProjects().forEach(project -> {
                properties.putAll(this.toKeyValuePairs(project));
                LOGGER.info("Writing row");
                generator.writeRow(getRow(columnsDef, properties));
            });
        });


        // String name = generator.getFileName();
        LOGGER.info("Writing file");
        String name = UUID.randomUUID() + ".xls";

        File file = fileService.createFile(name, true);
        generator.toFile(file);

        return name;

    }


}

