package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.util.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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
        }

        if(predicates.size()>0) {
            Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            criteriaQuery.where(other);
        }

        CriteriaQuery<Location> cq = criteriaQuery.select(locationRoot);
        TypedQuery<Location> query = em.createQuery(cq);

        return query.getResultList();
    }
}
