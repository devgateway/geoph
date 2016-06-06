package org.devgateway.geoph.persistence.repository;

import com.google.gson.Gson;
import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.util.FilterHelper;
import org.devgateway.geoph.util.*;
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

import static org.devgateway.geoph.util.Constants.*;

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
                        "where li.location_id = :parentId",
                Location.class)
                .setParameter("parentId", parentId)
                .getResultList();
    }

    @Override
    public Location findParentLocation(long locationId) {
        return (Location) em.createNativeQuery("Select l.* from location l inner join location_items li " +
                        "on l.id=li.location_id where li.items_id = :locationId",
                Location.class)
                .setParameter("locationId", locationId)
                .getSingleResult();
    }

    @Override
    public Location findGrandParentLocation(long locationId) {
        return (Location) em.createNativeQuery("Select l.* from location l inner join location_items li " +
                        "on l.id=li.location_id " +
                        "inner join location_items li2 on li2.location_id = li.items_id " +
                        "where li2.items_id = :locationId",
                    Location.class)
                .setParameter("locationId", locationId)
                .getSingleResult();
    }

    @Override
    @Cacheable("countLocationProjectsByParams")
    public List<Object> countLocationProjectsByParams(Parameters params) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();

        Root<Location> locationRoot = criteriaQuery.from(Location.class);
        List<Selection<?>> multiSelect = new ArrayList<>();
        multiSelect.add(locationRoot);

        List<Predicate> predicates = new ArrayList();
        List<Expression<?>> groupByList = new ArrayList<>();
        groupByList.add(locationRoot);

        Join<Location, Project> projectJoin = locationRoot.join(Location_.projects, JoinType.LEFT);
        multiSelect.add(criteriaBuilder.countDistinct(projectJoin).alias("projectCount"));

        FilterHelper.filterLocationQuery(params, criteriaBuilder, locationRoot, predicates, projectJoin);

        Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(other);


        criteriaQuery.groupBy(groupByList);
        TypedQuery<Object> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

        return query.getResultList();
    }

    @Override
    @Cacheable("locationsByParams")
    public List<Object> findLocationsByParams(Parameters params, int trxTypeId, int trxStatusId) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();

        Root<Location> locationRoot = criteriaQuery.from(Location.class);
        List<Selection<?>> multiSelect = new ArrayList<>();
        multiSelect.add(locationRoot);

        List<Predicate> predicates = new ArrayList();
        List<Expression<?>> groupByList = new ArrayList<>();
        groupByList.add(locationRoot);

        Join<Location, Project> projectJoin = locationRoot.join(Location_.projects, JoinType.LEFT);

        if(trxTypeId!=0 && trxStatusId!=0) {
            addTransactionJoin(criteriaBuilder, multiSelect, projectJoin, trxTypeId, trxStatusId);
        }

        multiSelect.add(criteriaBuilder.sum(projectJoin.get(Project_.actualOwpa)));
        multiSelect.add(criteriaBuilder.countDistinct(projectJoin.get(Project_.actualOwpa)));
        multiSelect.add(criteriaBuilder.sum(projectJoin.get(Project_.targetOwpa)));
        multiSelect.add(criteriaBuilder.countDistinct(projectJoin.get(Project_.targetOwpa)));

        FilterHelper.filterLocationQuery(params, criteriaBuilder, locationRoot, predicates, projectJoin);

        Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(other);


        criteriaQuery.groupBy(groupByList);
        TypedQuery<Object> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

        return query.getResultList();
    }

    private void addTransactionJoin(CriteriaBuilder criteriaBuilder, List<Selection<?>> multiSelect,
                                    Join<Location, Project> projectJoin, int trxType, int trxStatus) {
        Join<Project, Transaction> transactionJoin = projectJoin.join(Project_.transactions, JoinType.LEFT);
        transactionJoin.on(transactionJoin.get(Transaction_.transactionTypeId).in(trxType),
                transactionJoin.get(Transaction_.transactionStatusId).in(trxStatus));
        multiSelect.add(criteriaBuilder.sum(transactionJoin.get(Transaction_.amount)));
        multiSelect.add(criteriaBuilder.countDistinct(transactionJoin));
    }


    @Override
    public List<PostGisHelper> getRegionShapesWithDetail(double detail) {
        return getShapesWithDetail(detail, "region_geometry");
    }

    @Override
    public List<PostGisHelper> getProvinceShapesWithDetail(double detail) {
        return getShapesWithDetail(detail, "province_geometry");
    }

    @Override
    public List<PostGisHelper> getMunicipalityShapesWithDetail(double detail) {
        return getShapesWithDetail(detail, "municipality_geometry");
    }

    @Cacheable("shapesWithDetail")
    private List<PostGisHelper> getShapesWithDetail(double detail, String admLevel) {
        Query q = em.createNativeQuery("SELECT locationId, name, ST_AsGeoJSON(ST_Simplify(geom, :detail)) " +
                "as geoJsonObject from " + admLevel)
                .setParameter("detail", detail);
        List<Object[]> resultList = q.getResultList();
        Gson g = new Gson();
        List<PostGisHelper> resp = new ArrayList<>();
        for(Object[] o:resultList){
            PostGisHelper helper = g.fromJson((String)o[2], PostGisHelper.class);
            helper.setLocationId(((BigDecimal) o[0]).longValue());
            helper.setName((String) o[1]);
            resp.add(helper);
        }
        return resp;
    }
}
