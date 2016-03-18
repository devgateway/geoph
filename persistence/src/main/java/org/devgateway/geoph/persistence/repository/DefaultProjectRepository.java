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

        if(params!=null) {
            if(params.getProjects()!=null){
                predicates.add(projectRoot.get(Project_.id).in(params.getProjects()));
            }
            if(params.getSectors()!=null) {
                Join<Project, Sector> sectorJoin = projectRoot.join(Project_.sectors);
                predicates.add(sectorJoin.get(Sector_.id).in(params.getSectors()));
            }
            if(params.getStatuses()!=null) {
                Join<Project, Status> statusJoin = projectRoot.join(Project_.status);
                predicates.add(statusJoin.get(Status_.id).in(params.getStatuses()));
            }
            if(params.getLocations()!=null) {
                Join<Project, Location> locationJoin = projectRoot.join(Project_.locations);
                predicates.add(locationJoin.get(Location_.id).in(params.getLocations()));
            }
            if(params.getStartDate()!=null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectRoot.get(Project_.periodStart), params.getStartDate()));
            }
            if(params.getEndDate()!=null){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(projectRoot.get(Project_.periodEnd), params.getEndDate()));
            }
            if(params.getFundingAgencies()!=null){
                Join<Project, Agency> fundingAgencyJoin = projectRoot.join(Project_.fundingAgency);
                predicates.add(fundingAgencyJoin.get(FundingAgency_.id).in(params.getFundingAgencies()));
            }
            if(params.getImpAgencies()!=null){
                Join<Project, Agency> impAgencyJoin = projectRoot.join(Project_.implementingAgency);
                predicates.add(impAgencyJoin.get(ImplementingAgency_.id).in(params.getImpAgencies()));
            }
        }

        if(predicates.size()>0) {
            Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            criteriaQuery.where(other);
        }

        CriteriaQuery<Project> cq = criteriaQuery.select(projectRoot);
        TypedQuery<Project> query = em.createQuery(cq);

        List<Project> projectList = query
                .setFirstResult(params.getPageable().getOffset())
                .setMaxResults(params.getPageable().getPageNumber())
                .setHint(QUERY_HINT, em.getEntityGraph(GRAPH_PROJECT_ALL))
                .getResultList();

        return new PageImpl<Project>(projectList, params.getPageable(), projectList.size());
    }


}
