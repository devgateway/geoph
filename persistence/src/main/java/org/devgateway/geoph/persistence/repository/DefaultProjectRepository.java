package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.util.FilterHelper;
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
    public List<Project> findAll() {
        return em.createNamedQuery("findAllProjects", Project.class)
                .setHint(QUERY_HINT, em.getEntityGraph(GRAPH_PROJECT_ALL))
                .getResultList();
    }

    @Override
    public Project findById(long id) {
        return em.createNamedQuery("findProjectsById", Project.class)
                .setParameter(PROPERTY_PRJ_ID, id)
                .setHint(QUERY_HINT, em.getEntityGraph(GRAPH_PROJECT_ALL))
                .getSingleResult();
    }

    @Override
    public Page<Project> findProjectsByParams(Parameters params) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Project> criteriaQuery = criteriaBuilder
                .createQuery(Project.class);
        Root<Project> projectRoot = criteriaQuery.from(Project.class);
        List<Predicate> predicates = new ArrayList();

        FilterHelper.filterProjectQuery(params, criteriaBuilder, projectRoot, predicates);

        if(predicates.size()>0) {
            Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            criteriaQuery.where(other);
        }

        CriteriaQuery<Project> cq = criteriaQuery.select(projectRoot);
        TypedQuery<Project> query = em.createQuery(cq);


        int count = query.getResultList().size();

        List<Project> projectList = query
                .setFirstResult(params.getPageable().getOffset())
                .setMaxResults(params.getPageable().getPageSize())
                .setHint(QUERY_HINT, em.getEntityGraph(GRAPH_PROJECT_ALL))
                .getResultList();

        return new PageImpl<Project>(projectList, params.getPageable(), count);
    }


}
