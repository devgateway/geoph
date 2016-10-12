package org.devgateway.geoph.services;

import org.devgateway.geoph.core.export.ColumnDefinition;
import org.devgateway.geoph.core.export.DefinitionsProvider;
import org.devgateway.geoph.core.export.Generator;
import org.devgateway.geoph.core.repositories.IndicatorDetailRepository;
import org.devgateway.geoph.core.repositories.LocationRepository;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.services.ExportService;
import org.devgateway.geoph.core.services.FileService;
import org.devgateway.geoph.dao.ProjectLocationDao;
import org.devgateway.geoph.enums.LocationAdmLevelEnum;
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

import static org.devgateway.geoph.core.constants.Constants.*;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
@Service
public class ExportServiceImpl implements ExportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportService.class);

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    IndicatorDetailRepository indicatorDetailRepository;

    @Autowired
    FileService fileService;

    private Map<String, Object> toKeyValuePairs(Object instance, Class aClass, List methodsToInvoke) {
        return toKeyValuePairs(instance, aClass, aClass.getSimpleName().toLowerCase(), methodsToInvoke);
    }

    private Map<String, Object> toKeyValuePairs(Object instance, Class aClass, String className, List methodsToInvoke) {
        Map<String, Object> ret = new HashMap<>();
        if(methodsToInvoke!=null) {
            ret.putAll(Arrays.stream(aClass.getDeclaredMethods())
                    .filter(method -> methodsToInvoke.contains(method.getName()))
                    .collect(Collectors.toMap(method -> className + '.' + method.getName(), m -> {
                        try {
                            Object result = m.invoke(instance);
                            return result != null ? result : "";
                        } catch (Exception e) {
                            return "";
                        }
                    })));
        }
        return ret;
    }


    private RawRowImpl getRow(List<ColumnDefinition> columnsDef, Map<String, Object> properties) {
        RawRowImpl row = new RawRowImpl();
        columnsDef.forEach(colDef -> {
            if(colDef.getExtractor()!=null) {
                row.addCell(colDef.getCell(colDef.getExtractor().extract(properties)));
            }
        });
        return row;
    }

    public File exportLocationProject(DefinitionsProvider provider, Generator generator, Parameters parameters) throws Exception {

        LOGGER.info("Querying Locations");
        List<ProjectLocationDao> projectLocationList = locationRepository.findProjectLocationsByParams(parameters);

        LOGGER.info("Writing header");
        generator.writeHeaders(provider.getColumnsDefinitions());

        /*Collect data into a raw format*/
        projectLocationList.forEach(projectLocation -> {
            Map<String, Object> properties = this.toKeyValuePairs(projectLocation.getLocation(), Location.class, provider.getMethodsToInvoke().get(LOCATION_CLASSNAME));
            properties.putAll(this.toKeyValuePairs(projectLocation.getLocation(), AbstractPersistable.class, LOCATION_CLASSNAME, provider.getMethodsToInvoke().get(ABSTRACT_PERSISTABLE_CLASSNAME)));
            properties.putAll(this.toKeyValuePairs(projectLocation.getProject(), Project.class, provider.getMethodsToInvoke().get(PROJECT_CLASSNAME)));
            LOGGER.info("Writing row");
            generator.writeRow(getRow(provider.getColumnsDefinitions(), properties));
        });

        LOGGER.info("Writing file");
        String name = generator.getFileName();

        File file = fileService.createFile(name, true);

        return generator.toFile(file);

    }

    @Override
    public File exportIndicator(DefinitionsProvider provider, Generator generator, Long id) throws Exception {
        LOGGER.info("Querying Indicators");
        List<IndicatorDetail> indicatorDetails = indicatorDetailRepository.findByIndicatorId(id);

        LOGGER.info("Writing header");
        generator.writeHeaders(provider.getColumnsDefinitions());

        indicatorDetails.forEach(indicatorDetail -> {
            Location loc = locationRepository.findById(indicatorDetail.getLocationId());
            Map<String, Object> properties = this.toKeyValuePairs(indicatorDetail, IndicatorDetail.class, provider.getMethodsToInvoke().get(INDICATOR_DETAIL_CLASSNAME));
            properties.putAll(this.toKeyValuePairs(loc, Location.class, provider.getMethodsToInvoke().get(LOCATION_CLASSNAME)));
            LOGGER.info("Writing row");
            generator.writeRow(getRow(provider.getColumnsDefinitions(), properties));
        });

        LOGGER.info("Writing file");
        String name = generator.getFileName();

        File file = fileService.createFile(name, true);
        return generator.toFile(file);
    }

    @Override
    public File exportIndicatorTemplate(DefinitionsProvider provider, Generator generator, String levelStr) throws Exception {
        LOGGER.info("Querying Locations By Level: " + levelStr);
        int level =  LocationAdmLevelEnum.valueOf(levelStr.toUpperCase()).getLevel();

        List<Location> locationList = locationRepository.findLocationsByLevelUacsNotNull(level);

        LOGGER.info("Writing header");
        generator.writeHeaders(provider.getColumnsDefinitions());

        locationList.forEach(location -> {
            Map<String, Object> properties = this.toKeyValuePairs(location, Location.class, provider.getMethodsToInvoke().get(LOCATION_CLASSNAME));
            properties.putAll(this.toKeyValuePairs(location, AbstractPersistable.class, LOCATION_CLASSNAME, provider.getMethodsToInvoke().get(ABSTRACT_PERSISTABLE_CLASSNAME)));
            LOGGER.info("Writing row");
            generator.writeRow(getRow(provider.getColumnsDefinitions(), properties));
        });

        LOGGER.info("Writing file");
        String name = generator.getFileName();

        File file = fileService.createFile(name, true);
        return generator.toFile(file);
    }


}

