package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.util.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author dbianco
 *         created on mar 09 2016.
 */
@Service
public class DefaultProjectRepository implements ProjectRepository {

    @Autowired
    EntityManager em;

    @Override
    public List<Project> findAll() {
        return em.createNamedQuery("findAllProjects", Project.class).getResultList();
    }

    @Override
    public Page<Project> findProjectsByParams(Parameters params) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Project> criteriaQuery = criteriaBuilder
                .createQuery(Project.class);
        Root<Project> projectRoot = criteriaQuery.from(Project.class);
        List<Predicate> predicates = new ArrayList();

        if(params!=null) {
            if(params.getSectors()!=null) {
                Join<Project, Sector> sectorJoin = projectRoot.join(Project_.sectors);
                predicates.add(sectorJoin.get(Sector_.id).in(params.getSectors()));
            }
            if(params.getLocations()!=null) {
                Join<Project, Location> locationJoin = projectRoot.join(Project_.locations);
                predicates.add(locationJoin.get(Location_.id).in(params.getLocations()));
            }
        }

        if(predicates.size()>0) {
            Predicate other = criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
            criteriaQuery.where(other);
        }

        CriteriaQuery<Project> cq = criteriaQuery.select(projectRoot);
        TypedQuery<Project> query = em.createQuery(cq);

        List<Project> projectList = query
                .setFirstResult(params.getPageable().getOffset())
                .setMaxResults(params.getPageable().getPageNumber())
                .getResultList();

        return new PageImpl<Project>(projectList, params.getPageable(), projectList.size());
    }


}
