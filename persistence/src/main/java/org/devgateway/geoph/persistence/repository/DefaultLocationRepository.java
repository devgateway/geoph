package org.devgateway.geoph.persistence.repository;

import com.google.gson.Gson;
import org.devgateway.geoph.core.repositories.LocationRepository;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.LocationResultsDao;
import org.devgateway.geoph.dao.PostGisDao;
import org.devgateway.geoph.dao.ProjectLocationDao;
import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.util.FilterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
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

        Join<Location, Project> projectJoin = locationRoot.join(Location_.projects, JoinType.LEFT);
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
        Join<Location, Project> projectJoin = locationRoot.join(Location_.projects, JoinType.LEFT);

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
    @Cacheable("findLocationsByParamsTypeStatus")
    public List<LocationResultsDao> findLocationsByParamsTypeStatus(Parameters params, int trxTypeId, int trxStatusId) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<LocationResultsDao> criteriaQuery = criteriaBuilder.createQuery(LocationResultsDao.class);

        Root<Location> locationRoot = criteriaQuery.from(Location.class);
        List<Selection<?>> multiSelect = new ArrayList<>();
        multiSelect.add(locationRoot);

        List<Predicate> predicates = new ArrayList<>();
        List<Expression<?>> groupByList = new ArrayList<>();
        groupByList.add(locationRoot);

        Join<Location, Project> projectJoin = locationRoot.join(Location_.projects, JoinType.LEFT);

        if (trxTypeId != 0 && trxStatusId != 0) {
            addTransactionJoin(criteriaBuilder, multiSelect, projectJoin, trxTypeId, trxStatusId);
        }

        multiSelect.add(criteriaBuilder.sum(projectJoin.get(Project_.actualOwpa)).alias("actualPhysicalProgressAmount"));
        multiSelect.add(criteriaBuilder.countDistinct(projectJoin.get(Project_.actualOwpa)).alias("actualPhysicalProgressCount"));
        multiSelect.add(criteriaBuilder.sum(projectJoin.get(Project_.targetOwpa)).alias("targetPhysicalProgressAmount"));
        multiSelect.add(criteriaBuilder.countDistinct(projectJoin.get(Project_.targetOwpa)).alias("targetPhysicalProgressCount"));

        FilterHelper.filterLocationQuery(params, criteriaBuilder, locationRoot, predicates, projectJoin);

        Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(other);


        criteriaQuery.groupBy(groupByList);
        TypedQuery<LocationResultsDao> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

        return query.getResultList();
    }

    private void addTransactionJoin(CriteriaBuilder criteriaBuilder, List<Selection<?>> multiSelect,
                                    Join<Location, Project> projectJoin, int trxType, int trxStatus) {
        Join<Project, Transaction> transactionJoin = projectJoin.join(Project_.transactions, JoinType.LEFT);
        transactionJoin.on(transactionJoin.get(Transaction_.transactionTypeId).in(trxType),
                transactionJoin.get(Transaction_.transactionStatusId).in(trxStatus));
        multiSelect.add(criteriaBuilder.sum(transactionJoin.get(Transaction_.amount)).alias("trxAmount"));
        multiSelect.add(criteriaBuilder.countDistinct(transactionJoin).alias("trxCount"));
    }


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
    }
}
