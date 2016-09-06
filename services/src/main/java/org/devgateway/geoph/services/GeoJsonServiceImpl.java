package org.devgateway.geoph.services;

import org.devgateway.geoph.core.repositories.*;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.services.GeoJsonService;
import org.devgateway.geoph.dao.*;
import org.devgateway.geoph.enums.GeometryDetail;
import org.devgateway.geoph.enums.LocationAdmLevelEnum;
import org.devgateway.geoph.enums.TransactionStatusEnum;
import org.devgateway.geoph.enums.TransactionTypeEnum;
import org.devgateway.geoph.model.*;
import org.devgateway.geoph.services.geojson.Converter;
import org.devgateway.geoph.services.geojson.ConverterFactory;
import org.devgateway.geoph.services.geojson.GeoJsonBuilder;
import org.geojson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dbianco
 *         created on mar 11 2016.
 */
@Service
public class GeoJsonServiceImpl implements GeoJsonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeoJsonServiceImpl.class);

    private static final int LONG = 0;
    private static final int LAT = 1;


    @Autowired
    LocationRepository locationRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    IndicatorDetailRepository indicatorDetailRepository;

    @Autowired
    IndicatorRepository indicatorRepository;


    @Autowired
    GeoPhotoRepositoryCustom geoPhotoRepository;


    /*Helper to create LocationFundingStatsDao, can be moved to a different class*/
    private LocationFundingStatsDao createSummary(LocationResultsDao result, Map<Long, GeometryDao> geometries, LocationAdmLevelEnum level) {
        LocationFundingStatsDao summary = new LocationFundingStatsDao();
        summary.setName(result.getName());
        summary.setLevel(level.getLevel());
        summary.setId(result.getLocationId());
        if (geometries.get(result.getLocationId()) != null) {
            summary.setGeometry(geometries.get(result.getLocationId()).getGeometry());
        }
        return summary;
    }


    /*
    * Returns map of <Location_id, Geometry >
    * */
    Map<Long, GeometryDao> getShapesMap(List<GeometryDao> geometryDaos) {
        return geometryDaos.stream().collect(Collectors.toMap(GeometryDao::getLocationId, (GeometryDao) -> GeometryDao));
    }


    Map<Long, LocationResultsDao> resultsDaoMap(List<LocationResultsDao> locationResultsDaos) {
        return locationResultsDaos.stream().collect(Collectors.toMap(LocationResultsDao::getLocationId, LocationResultsDao -> LocationResultsDao));
    }


    Map<Long, LocationProjectStatsDao> resultProjectsMap(List<LocationProjectStatsDao> locationProjectStatsDaos) {
        return locationProjectStatsDaos.stream().collect(Collectors.toMap(LocationProjectStatsDao::getId, LocationProjectStatsDao -> LocationProjectStatsDao));
    }

    @Override
    /**
     * Returns locations with transaction aggregated data group by transaction type and transactions status with MultiPolygon as location geometry
     */
    public FeatureCollection getFundingShapes(LocationAdmLevelEnum level, GeometryDetail detail, Parameters params) {
        long start_time = System.currentTimeMillis();
        //results should be ordered by ID!;

        List<LocationResultsDao> locationResultsDaos = locationRepository.getLocationWithTransactionStats(params); //records should be order by location id
        List<GeometryDao> geometriesList = locationRepository.getShapesByLevelAndDetail(level.getLevel(), detail.getValue());

        GeoJsonBuilder builder = new GeoJsonBuilder();

        Map<Long, GeometryDao> geometries = getShapesMap(geometriesList);
        if (geometries.size() == 0) {
            LOGGER.warn("Shapes map is empty!!!!");
        }


        if (locationResultsDaos.iterator().hasNext()) {
            LocationFundingStatsDao current = createSummary(locationResultsDaos.iterator().next(), geometries, level);

            for (LocationResultsDao result : locationResultsDaos) {
                {
                    if (current.getId() != result.getLocationId()) {
                        builder.addFeature(ConverterFactory.locationShapeConverter().convert(current));
                        current = createSummary(result, geometries, level);

                    }
                    if (result.getTransactionTypeId() == TransactionTypeEnum.COMMITMENTS.getId()) {
                        current.getCommitments().put(TransactionStatusEnum.getEnumById(result.getTransactionStatusId()).getName(), result.getAmount());
                    }
                    if (result.getTransactionTypeId() == TransactionTypeEnum.EXPENDITURES.getId()) {
                        current.getExpenditure().put(TransactionStatusEnum.getEnumById(result.getTransactionStatusId()).getName(), result.getAmount());

                    }
                    if (result.getTransactionTypeId() == TransactionTypeEnum.DISBURSEMENTS.getId()) {
                        current.getDisbursements().put(TransactionStatusEnum.getEnumById(result.getTransactionStatusId()).getName(), result.getAmount());

                    }

                }
            }

            LOGGER.info("---returning features " + (System.currentTimeMillis() - start_time) + "---");
        }
        return builder.getFeatures();
    }

    @Override
    public FeatureCollection getIndicatorShapes(Long indicatorId, GeometryDetail detail) {
        Indicator indicator = indicatorRepository.findOne(indicatorId);
        List<IndicatorDetail> details = indicatorDetailRepository.findByIndicatorId(indicatorId);

        Map<Long, IndicatorDetail> detailsMap = details.stream().collect(Collectors.toMap(IndicatorDetail::getLocationId, IndicatorDetail -> IndicatorDetail));
        LocationAdmLevelEnum level = LocationAdmLevelEnum.getEnumByName(indicator.getAdmLevel());
        GeoJsonBuilder builder = new GeoJsonBuilder();


        List<GeometryDao> geometriesList = locationRepository.getShapesByLevelAndDetail(level.getLevel(), detail.getValue());

        builder.setFeatures(
                geometriesList.stream().map(geometryDao -> {
                    IndicatorDetail indicatorDetail = detailsMap.get(geometryDao.getLocationId());//get indicator detail for this location
                    IndicatorGeometryDao dao = new IndicatorGeometryDao(geometryDao.getLocationId(),
                            geometryDao.getName(), geometryDao.getGeometry(), indicator.getId(), indicator.getName(), indicator.getDescription(),
                            indicator.getColorScheme(), indicatorDetail.getValue(), level.getLevel(),indicator.getUnit());
                    return ConverterFactory.indicatorGeometryConverter().convert(dao);

                }).collect(Collectors.toList()));

        return builder.getFeatures();
    }


    @Override
    /**
     * Returns project aggregated data (count and  average of physical progress) by location with Point as geometry only location with values are returned //TODO:check
     */
    public FeatureCollection getProjectPoints(LocationAdmLevelEnum level, Parameters params) {
        long start_time = System.currentTimeMillis();

        List<LocationProjectStatsDao> locationProjectStatDaos = locationRepository.getLocationWithProjectStats(params); //return project count + physicalProgress group by location filtered by params

        GeoJsonBuilder builder = new GeoJsonBuilder();

        builder.setFeatures(locationProjectStatDaos.stream().map(locationStatsDao ->
                {
                    locationStatsDao.setLevel(level.getLevel()); //The ui needs level on each feature
                    return ConverterFactory.locationPointConverter().convert(locationStatsDao);
                }
        ).collect(Collectors.toList()));

        LOGGER.info("---returning features " + (System.currentTimeMillis() - start_time) + "---");

        return builder.getFeatures();
    }


    @Override
    /**
     * Returns project aggregated data (count and average of physical progress) by location with MultiPolygon as geometry
     * all geometries ara always returned
     */
    public FeatureCollection getProjectShapes(LocationAdmLevelEnum level, GeometryDetail detail, Parameters params) {
        long start_time = System.currentTimeMillis();

        List<LocationProjectStatsDao> locationProjectStatDaos = locationRepository.getLocationWithProjectStats(params); //return project count + physicalProgress group by location filtered by params

        GeoJsonBuilder builder = new GeoJsonBuilder();

        List<GeometryDao> geometries = locationRepository.getShapesByLevelAndDetail(level.getLevel(), detail.getValue());

        Map<Long, LocationProjectStatsDao> resultMap = resultProjectsMap(locationProjectStatDaos);//get result as Map<location_id,values) format

        builder.setFeatures(
                geometries.stream().map(geometryDao -> {//to ensure we return all shapes this method iterates the geometries list , previously data was converted to  map

                    LocationProjectStatsDao locationProjectStatsDao;
                    if (resultMap.get(geometryDao.getLocationId()) != null) {
                        locationProjectStatsDao = resultMap.get(geometryDao.getLocationId());
                    } else {
                        locationProjectStatsDao = new LocationProjectStatsDao();
                    }
                    locationProjectStatsDao.setGeometry(geometryDao.getGeometry());
                    locationProjectStatsDao.setName(geometryDao.getName());
                    return ConverterFactory.locationPointConverter().convert(locationProjectStatsDao);
                }).collect(Collectors.toList()));


        LOGGER.info("---returning features " + (System.currentTimeMillis() - start_time) + "---");

        return builder.getFeatures();
    }

    /**
     *
     * @param parameters
     * @return
     */
    public FeatureCollection getPhotoPoints(Parameters parameters) {
        GeoJsonBuilder geoJsonBuilder = new GeoJsonBuilder();
        List<GeoPhotoDao> daos = geoPhotoRepository.findGeoPhotosByParams(parameters);
        geoJsonBuilder.setFeatures(daos.stream().map(geoPhotoDao -> ConverterFactory.geoPhotoConverter().convert(geoPhotoDao)).collect(Collectors.toList()));

        return geoJsonBuilder.getFeatures();
    }


}
