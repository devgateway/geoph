package org.devgateway.geoph.services;

import org.devgateway.geoph.core.export.ColumnDefinition;
import org.devgateway.geoph.core.export.Generator;
import org.devgateway.geoph.core.repositories.IndicatorDetailRepository;
import org.devgateway.geoph.core.repositories.LocationRepository;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.services.ExportService;
import org.devgateway.geoph.core.services.FileService;
import org.devgateway.geoph.dao.ProjectLocationDao;
import org.devgateway.geoph.model.IndicatorDetail;
import org.devgateway.geoph.model.Location;
import org.devgateway.geoph.model.Project;
import org.devgateway.geoph.services.exporter.RawRowImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
@Service
public class ExportServiceImpl implements ExportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportService.class);

    private Generator generator;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    IndicatorDetailRepository indicatorDetailRepository;

    @Autowired
    FileService fileService;


    private Map<String, Object> toKeyValuePairs(Object instance, Class className, String propertyName) {
        Map<String, Object> ret = new HashMap<>();
        ret.putAll(Arrays.stream(className.getDeclaredMethods())
                .collect(Collectors.toMap(method -> {
                    return propertyName + '.' + method.getName();
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

    public String exportLocationProject(List<ColumnDefinition> columnsDef, Generator generator, Parameters parameters) throws Exception {

        LOGGER.info("Querying Locations");
        List<ProjectLocationDao> projectLocationList = locationRepository.findProjectLocationsByParams(parameters);

        LOGGER.info("Writing header");
        generator.writeHeaders(columnsDef);

        /*Collect data into a raw format*/
        projectLocationList.forEach(projectLocation -> {
            Map<String, Object> properties = this.toKeyValuePairs(projectLocation.getLocation(), Location.class, Location.class.getSimpleName().toLowerCase());
            properties.putAll(this.toKeyValuePairs(projectLocation.getLocation(), AbstractPersistable.class, Location.class.getSimpleName().toLowerCase()));
            properties.putAll(this.toKeyValuePairs(projectLocation.getProject(), Project.class, Project.class.getSimpleName().toLowerCase()));
            properties.putAll(this.toKeyValuePairs(projectLocation.getProject(), AbstractPersistable.class, Project.class.getSimpleName().toLowerCase()));
            LOGGER.info("Writing row");
            generator.writeRow(getRow(columnsDef, properties));
        });

        LOGGER.info("Writing file");
        String name = generator.getFileName();

        File file = fileService.createFile(name, true);
        generator.toFile(file);

        return name;

    }

    @Override
    public String exportIndicator(List<ColumnDefinition> columnsDef, Generator generator, Long id) throws Exception {
        LOGGER.info("Querying Indicators");
        List<IndicatorDetail> indicatorDetails = indicatorDetailRepository.findByIndicatorId(id);

        LOGGER.info("Writing header");
        generator.writeHeaders(columnsDef);

        indicatorDetails.forEach(indicatorDetail -> {
            Location loc = locationRepository.findById(indicatorDetail.getLocationId());
            Map<String, Object> properties = this.toKeyValuePairs(indicatorDetail, IndicatorDetail.class, IndicatorDetail.class.getSimpleName().toLowerCase());
            properties.putAll(this.toKeyValuePairs(loc, Location.class, Location.class.getSimpleName().toLowerCase()));
            LOGGER.info("Writing row");
            generator.writeRow(getRow(columnsDef, properties));
        });

        LOGGER.info("Writing file");
        String name = generator.getFileName();

        File file = fileService.createFile(name, true);
        generator.toFile(file);
        return name;
    }


}

