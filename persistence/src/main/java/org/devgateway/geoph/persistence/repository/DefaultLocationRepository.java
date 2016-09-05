package org.devgateway.geoph.persistence.repository;

import com.vividsolutions.jts.geom.Geometry;
import org.devgateway.geoph.core.repositories.LocationRepository;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.GeometryDao;
import org.devgateway.geoph.dao.LocationResultsDao;
import org.devgateway.geoph.dao.ProjectLocationDao;
import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.util.FilterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import static org.devgateway.geoph.core.constants.Constants.*;

/**
 * @author dbianco
 *         created on mar 14 2016.
 */
@Service
public class DefaultLocationRepository implements LocationRepository {

    @Autowired
    EntityManager em;

    @Override
    public List<Location> findAll() {
        return em.createNamedQuery("findAllLocations", Location.class).getResultList();
    }

    @Override
    @Cacheable("locationsById")
    public Location findById(long id) {
        return em.createNamedQuery("findLocationsById", Location.class)
                .setParameter(PROPERTY_LOC_ID, id)
                .getSingleResult();
    }

    @Override
    @Cacheable("locationsByCode")
    public Location findByCode(String code) {
        return em.createNamedQuery("findLocationsByCode", Location.class)
                .setParameter(PROPERTY_LOC_CODE, code)
                .getSingleResult();
    }

    @Override
    @Cacheable("locationsByLevel")
    public List<Location> findLocationsByLevel(int level) {
        return em.createNamedQuery("findLocationsByLevel", Location.class)
                .setParameter(PROPERTY_LOC_LEVEL, level)
                .getResultList();
    }

    @Override
    @Cacheable("locationsByLevelUacsNotNull")
    public List<Location> findLocationsByLevelUacsNotNull(int level) {
        return em.createNamedQuery("findLocationsByLevelUacsNotNull", Location.class)
                .setParameter(PROPERTY_LOC_LEVEL, level)
                .getResultList();
    }

    @Override
    @Cacheable("findLocationsByParentId")
    public List<Location> findLocationsByParentId(long parentId) {
        return em.createNativeQuery("Select l.* from location l " +
                "inner join location_items li on l.id=li.items_id " +
                "where li.location_id = :parentId", Location.class)
                .setParameter("parentId", parentId)
                .getResultList();
    }

    @Override
    @Cacheable("countLocationProjectsByParams")
    public List<LocationResultsDao> countLocationProjectsByParams(Parameters params) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<LocationResultsDao> criteriaQuery = criteriaBuilder.createQuery(LocationResultsDao.class);

        Root<Location> locationRoot = criteriaQuery.from(Location.class);
        List<Selection<?>> multiSelect = new ArrayList<>();
        multiSelect.add(locationRoot);

        List<Predicate> predicates = new ArrayList<>();
        List<Expression<?>> groupByList = new ArrayList<>();
        groupByList.add(locationRoot);

        Join<Location, ProjectLocation> projectLocationJoin = locationRoot.join(Location_.projects, JoinType.LEFT);
        Join<ProjectLocation, ProjectLocationId> idJoin = projectLocationJoin.join(ProjectLocation_.pk, JoinType.LEFT);
        Join<ProjectLocationId, Project> projectJoin = idJoin.join(ProjectLocationId_.project, JoinType.LEFT);
        multiSelect.add(criteriaBuilder.countDistinct(projectJoin).alias("projectCount"));

        FilterHelper.filterLocationQuery(params, criteriaBuilder, locationRoot, predicates, projectJoin);

        Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(other);

        criteriaQuery.groupBy(groupByList);
        TypedQuery<LocationResultsDao> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

        return query.getResultList();
    }

    @Override
    @Cacheable("locationsByParams")
    public List<ProjectLocationDao> findProjectLocationsByParams(Parameters params) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ProjectLocationDao> criteriaQuery = criteriaBuilder.createQuery(ProjectLocationDao.class);

        Root<Location> locationRoot = criteriaQuery.from(Location.class);
        Join<Location, ProjectLocation> projectLocationJoin = locationRoot.join(Location_.projects, JoinType.LEFT);
        Join<ProjectLocation, ProjectLocationId> idJoin = projectLocationJoin.join(ProjectLocation_.pk, JoinType.LEFT);
        Join<ProjectLocationId, Project> projectJoin = idJoin.join(ProjectLocationId_.project, JoinType.LEFT);

        List<Selection<?>> multiSelect = new ArrayList<>();
        multiSelect.add(locationRoot);
        multiSelect.add(projectJoin);

        List<Expression<?>> groupByList = new ArrayList<>();
        groupByList.add(locationRoot);
        groupByList.add(projectJoin);

        List<Predicate> predicates = new ArrayList<>();
        FilterHelper.filterLocationQuery(params, criteriaBuilder, locationRoot, predicates, projectJoin);

        Predicate predicate = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(predicate);

        criteriaQuery.groupBy(groupByList);
        TypedQuery<ProjectLocationDao> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

        return query.getResultList();
    }



    @Override
    public List<LocationResultsDao> findLocationsByParams(Parameters params) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery  criteriaQuery = criteriaBuilder.createQuery(LocationResultsDao.class);
        Root<Location> locationRoot = criteriaQuery.from(Location.class);

        List<Selection<?>> multiSelect = new ArrayList<>();
        List<Predicate> predicates = new ArrayList<>();
        List<Expression<?>> groupByList = new ArrayList<>();


        multiSelect.add(locationRoot.get(Location_.id));
        groupByList.add(locationRoot.get(Location_.id));
        multiSelect.add(locationRoot.get(Location_.name));
        groupByList.add(locationRoot.get(Location_.id));

          multiSelect.add(locationRoot.get(Location_.centroid));
        groupByList.add(locationRoot.get(Location_.centroid));


        groupByList.add(locationRoot);

        Join<Location, ProjectLocation> projectLocationJoin = locationRoot.join(Location_.projects, JoinType.INNER); //location -> project_location

       // Join<Location, LocationGeometry> geometryJoin = locationRoot.join(Location_.locationGeometry, JoinType.LEFT); //project to transaction (left in order to be able to count projects without transactions)


        //ParameterExpression<Double> level = criteriaBuilder.parameter(Double.class, "level");

        //Expression function=criteriaBuilder.function("ST_Simplify", Geometry.class, geometryJoin.get(LocationGeometry_.geometry),level);

        //multiSelect.add(function);
        //groupByList.add(geometryJoin.get(LocationGeometry_.geometry));


        Join<ProjectLocation, ProjectLocationId> idJoin = projectLocationJoin.join(ProjectLocation_.pk, JoinType.INNER); //

        Join<ProjectLocationId, Project> projectJoin = idJoin.join(ProjectLocationId_.project, JoinType.INNER); //project_location to project

        Join<Project, Transaction> transactionJoin = projectJoin.join(Project_.transactions, JoinType.LEFT); //project to transaction (left in order to be able to count projects without transactions)

        multiSelect.add(transactionJoin.get(Transaction_.transactionStatusId));
        groupByList.add(transactionJoin.get(Transaction_.transactionStatusId));

        multiSelect.add(transactionJoin.get(Transaction_.transactionTypeId));
        groupByList.add(transactionJoin.get(Transaction_.transactionTypeId));

        multiSelect.add(criteriaBuilder.sum(criteriaBuilder.prod(transactionJoin.get(Transaction_.amount), projectLocationJoin.get(ProjectLocation_.utilization))).alias("amount")); //amount * % of utilization
        multiSelect.add(criteriaBuilder.countDistinct(projectJoin).alias("count"));

        //add params filters

        FilterHelper.filterLocationQuery(params, criteriaBuilder, locationRoot, predicates, projectJoin);

        Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(other);
        criteriaQuery.orderBy(criteriaBuilder.asc(locationRoot.get(Location_.id)));

        criteriaQuery.groupBy(groupByList);
        TypedQuery<LocationResultsDao> query = em.createQuery(criteriaQuery.multiselect(multiSelect));
        //query.setParameter("level",GeometryDetail.MEDIUM.getValue());

        return query.getResultList();
    }


    @Cacheable(value = "shapesWithDetail" , unless = "#result.size() == 0" )
    public List<GeometryDao> getShapesByLevelAndDetail(int level,double detail) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery  criteriaQuery = criteriaBuilder.createQuery(GeometryDao.class);
        Root<Location> locationRoot = criteriaQuery.from(Location.class);

        List<Selection<?>> multiSelect = new ArrayList<>();
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(locationRoot.get(Location_.level).in(level));

        Join<Location, LocationGeometry> geometryJoin = locationRoot.join(Location_.locationGeometry, JoinType.LEFT); //project to transaction (left in order to be able to count projects without transactions)

        predicates.add(geometryJoin.get(LocationGeometry_.geometry).isNotNull());

        //multiSelect.add(geometryJoin.get(LocationGeometry_.geometry));
        ParameterExpression<Double> detailparam = criteriaBuilder.parameter(Double.class, "detail");
        Expression function=criteriaBuilder.function("ST_Simplify", Geometry.class, geometryJoin.get(LocationGeometry_.geometry), detailparam);

        multiSelect.add(locationRoot.get(Location_.id));
        multiSelect.add(function);

        TypedQuery<GeometryDao> query = em.createQuery(criteriaQuery.multiselect(multiSelect));
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        query.setParameter("detail", detail);
        return query.getResultList();
    }




/*
    @Override
    public List<PostGisDao> getRegionShapesWithDetail(double detail) {
        return getShapesWithDetail(detail, "region_geometry");
    }

    @Override
    public List<PostGisDao> getProvinceShapesWithDetail(double detail) {
        return getShapesWithDetail(detail, "province_geometry");
    }

    @Override
    public List<PostGisDao> getMunicipalityShapesWithDetail(double detail) {
        return getShapesWithDetail(detail, "municipality_geometry");
    }

    @Cacheable("shapesWithDetail")
    private List<PostGisDao> getShapesWithDetail(double detail, String admLevel) {
        Query q = em.createNativeQuery("SELECT locationId, name, ST_AsGeoJSON(ST_Simplify(geom, :detail)) " +
                "as geoJsonObject from " + admLevel)
                .setParameter("detail", detail);
        List<Object[]> resultList = q.getResultList();
        Gson g = new Gson();
        List<PostGisDao> resp = new ArrayList<>();
        //Native queries cant be mapped to a class, only to an entity, that is why we create the DAO from objects
        for (Object[] o : resultList) {
            PostGisDao helper = g.fromJson((String) o[2], PostGisDao.class);
            helper.setLocationId(((BigDecimal) o[0]).longValue());
            helper.setName((String) o[1]);
            resp.add(helper);
        }
        return resp;
    }*/
}
