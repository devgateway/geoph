package org.devgateway.geoph.persistence.repository;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.devgateway.geoph.model.*;
import org.devgateway.geoph.util.FilterParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
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
        return (Location)em.createNamedQuery("findLocationsByCode").setParameter("code", code).getSingleResult();
    }

    @Override
    public List<Location> findLocationsByLevel(int level) {
        List locObject = em.createNamedQuery("findLocationsByLevel").setParameter("level", level).getResultList();
        List<Location> locationList = Lists.transform(locObject, new Function<Object, Location>() {
            @Nullable
            @Override
            public Location apply(@Nullable Object input) {
                return (Location) input;
            }
        });
        return locationList;
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
            if(params.containsKey(PROPERTY_LOC_TYPE)) {
                predicates.add(criteriaBuilder.equal(locationRoot.get(Location_.level), params.get(PROPERTY_LOC_TYPE)[0]));
            }
            if(params.containsKey(FILTER_SECTOR)) {
                Join<Location, Project> projectJoin = locationRoot.join(Location_.projects);
                Join<Project, Sector> sectorJoin = projectJoin.join(Project_.sectors);
                List<Long> values = FilterParser.stringArrayToLongList(params.get(FILTER_SECTOR));
                predicates.add(sectorJoin.get(Sector_.id).in(values));
            }
        }

        Predicate other = criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(other);

        CriteriaQuery<Location> cq = criteriaQuery.select(locationRoot);
        TypedQuery<Location> query = em.createQuery(cq);

        return query.getResultList();
    }
}
