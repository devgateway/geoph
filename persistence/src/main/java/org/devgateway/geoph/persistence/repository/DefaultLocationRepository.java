package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.util.FilterParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public List<Location> findLocationsByParams(Map<String, String[]> params) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Location> criteriaQuery = criteriaBuilder
                .createQuery(Location.class);
        Root<Location> locationRoot = criteriaQuery.from(Location.class);
        List<Predicate> predicates = new ArrayList();

        if(params!=null) {
            Join<Location, Project> projectJoin = locationRoot.join(Location_.projects);
            if(params.containsKey(PROPERTY_LOC_LEVEL)) {
                List<Long> values = FilterParser.stringArrayToLongList(params.get(PROPERTY_LOC_LEVEL));
                predicates.add(locationRoot.get(Location_.level).in(values));
            }
            if(params.containsKey(FILTER_SECTOR)) {
                Join<Project, Sector> sectorJoin = projectJoin.join(Project_.sectors);
                List<Long> values = FilterParser.stringArrayToLongList(params.get(FILTER_SECTOR));
                predicates.add(sectorJoin.get(Sector_.id).in(values));
            }
            if(params.containsKey(FILTER_STATUS)) {
                Join<Project, Status> statusJoin = projectJoin.join(Project_.status);
                List<Long> values = FilterParser.stringArrayToLongList(params.get(FILTER_STATUS));
                predicates.add(statusJoin.get(Status_.id).in(values));
            }
            if(params.containsKey(FILTER_DATE_START)){
                Date startDate = FilterParser.stringArrayToDate(params.get(FILTER_DATE_START));
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectJoin.get(Project_.periodStart), startDate));
            }

            if(params.containsKey(FILTER_DATE_END)){
                Date endDate = FilterParser.stringArrayToDate(params.get(FILTER_DATE_END));
                predicates.add(criteriaBuilder.lessThanOrEqualTo(projectJoin.get(Project_.periodEnd), endDate));
            }
        }

        Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(other);

        CriteriaQuery<Location> cq = criteriaQuery.select(locationRoot);
        TypedQuery<Location> query = em.createQuery(cq);

        return query.getResultList();
    }
}
