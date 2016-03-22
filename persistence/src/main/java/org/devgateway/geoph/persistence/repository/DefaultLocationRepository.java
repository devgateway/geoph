package org.devgateway.geoph.persistence.repository;

import com.google.gson.Gson;
import org.devgateway.geoph.model.*;
import org.devgateway.geoph.util.GeometryDetailLevel;
import org.devgateway.geoph.util.PostGisHelper;
import org.devgateway.geoph.util.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Location findByCode(String code) {
        return em.createNamedQuery("findLocationsByCode", Location.class)
                .setParameter(PROPERTY_LOC_CODE, code)
                .getSingleResult();
    }

    @Override
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
    public List<Location> findLocationsByParams(Parameters params) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Location> criteriaQuery = criteriaBuilder
                .createQuery(Location.class);
        Root<Location> locationRoot = criteriaQuery.from(Location.class);
        List<Predicate> predicates = new ArrayList();

        if(params!=null) {
            Join<Location, Project> projectJoin = locationRoot.join(Location_.projects, JoinType.LEFT);
            if(params.getLocationLevels()!=null) {
                predicates.add(locationRoot.get(Location_.level).in(params.getLocationLevels()));
            }
            if(params.getLocations()!=null) {
                predicates.add(locationRoot.get(Location_.id).in(params.getLocations()));
            }
            if(params.getProjects()!=null) {
                predicates.add(projectJoin.in(params.getProjects()));
            }
            if(params.getSectors()!=null) {
                Join<Project, Sector> sectorJoin = projectJoin.join(Project_.sectors, JoinType.LEFT);
                predicates.add(sectorJoin.get(Sector_.id).in(params.getSectors()));
            }
            if(params.getStatuses()!=null) {
                Join<Project, Status> statusJoin = projectJoin.join(Project_.status);
                predicates.add(statusJoin.get(Status_.id).in(params.getStatuses()));
            }
            if(params.getStartDate()!=null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectJoin.get(Project_.periodStart), params.getStartDate()));
            }
            if(params.getEndDate()!=null){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(projectJoin.get(Project_.periodEnd), params.getEndDate()));
            }
            if(params.getFundingAgencies()!=null) {
                Join<Project, Agency> fundingAgencyJoin = projectJoin.join(Project_.fundingAgency, JoinType.LEFT);
                predicates.add(fundingAgencyJoin.get(FundingAgency_.id).in(params.getFundingAgencies()));
            }
            if(params.getImpAgencies()!=null) {
                Join<Project, Agency> impAgencyJoin = projectJoin.join(Project_.implementingAgency, JoinType.LEFT);
                predicates.add(impAgencyJoin.get(ImplementingAgency_.id).in(params.getImpAgencies()));
            }
        }

        if(predicates.size()>0) {
            Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            criteriaQuery.where(other);
        }

        TypedQuery<Location> query = em.createQuery(criteriaQuery.select(locationRoot));
        return query.getResultList();
    }

    @Override
    public List<PostGisHelper> getRegionShapesWithDetail(GeometryDetailLevel detail) {
        Query q = em.createNativeQuery("SELECT locationId, region, ST_AsGeoJSON(ST_Simplify(geom, "
                + detail.getLevel() + ")) as geoJsonObject from region_geometry");
        List<Object[]> resultList = q.getResultList();
        Gson g = new Gson();
        List<PostGisHelper> resp = new ArrayList<>();
        for(Object[] o:resultList){
            PostGisHelper helper = g.fromJson((String)o[2], PostGisHelper.class);
            helper.setLocationId(((BigDecimal) o[0]).longValue());
            helper.setRegionName((String)o[1]);
            resp.add(helper);
        }
        return resp;
    }
}
