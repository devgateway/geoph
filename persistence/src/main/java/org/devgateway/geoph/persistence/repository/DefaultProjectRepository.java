package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.util.FilterParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.devgateway.geoph.util.Constants.*;

/**
 * @author dbianco
 *         created on mar 09 2016.
 */
@Service
public class DefaultProjectRepository implements ProjectRepository {

    @Autowired
    EntityManager em;

    @Override
    public Page<Project> findAll(Pageable pageable) {
        Query queryTotal = em.createQuery
                ("Select count(p.id) from Project p");
        long countResult = (long)queryTotal.getSingleResult();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Project> criteria = builder.createQuery(Project.class);
        Root<Project> projectRoot = criteria.from(Project.class);
        criteria.select(projectRoot);
        List<Project> projectList = em.createQuery(criteria)
                .setFirstResult(pageable.getOffset())
                .setMaxResults(pageable.getPageNumber())
                .getResultList();

        return new PageImpl<Project>(projectList, pageable, countResult);

    }

    @Override
    public Page<Project> findProjectsByParams(Map<String, String[]> params, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Project> criteriaQuery = criteriaBuilder
                .createQuery(Project.class);
        Root<Project> projectRoot = criteriaQuery.from(Project.class);
        List<Predicate> predicates = new ArrayList();

        if(params!=null) {
            if(params.containsKey(FILTER_SECTOR)) {
                Join<Project, Sector> sectorJoin = projectRoot.join(Project_.sectors);
                List<Long> values = FilterParser.stringArrayToLongList(params.get(FILTER_SECTOR));
                predicates.add(sectorJoin.get(Sector_.id).in(values));
            }
            if(params.containsKey(FILTER_LOCATION)) {
                Join<Project, Location> locationJoin = projectRoot.join(Project_.locations);
                List<Long> values = FilterParser.stringArrayToLongList(params.get(FILTER_LOCATION));
                predicates.add(locationJoin.get(Location_.id).in(values));
            }
        }

        Predicate other = criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(other);

        CriteriaQuery<Project> cq = criteriaQuery.select(projectRoot);
        TypedQuery<Project> query = em.createQuery(cq);

        List<Project> projectList = query.getResultList();

        /*List<Project> projectList = em.createQuery(criteria)
                .setFirstResult(pageable.getOffset())
                .setMaxResults(pageable.getPageNumber())
                .getResultList();*/

        return new PageImpl<Project>(projectList, pageable, 0);
    }


}
