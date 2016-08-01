package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.core.repositories.ProjectRepository;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.response.StatsResponse;
import org.devgateway.geoph.model.Project;
import org.devgateway.geoph.persistence.util.FilterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static org.devgateway.geoph.core.constants.Constants.*;



/**
 * @author dbianco
 *         created on mar 09 2016.
 */
@Service
@Transactional
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
        TypedQuery<Project> query = getProjectTypedQuery(params);
        int count = query.getResultList().size();

        List<Project> projectList;
        if(params.getPageable()!=null) {
            projectList = query
                    .setFirstResult(params.getPageable().getOffset())
                    .setMaxResults(params.getPageable().getPageSize())
                    .setHint(QUERY_HINT, em.getEntityGraph(GRAPH_PROJECT_ALL))
                    .getResultList();
        } else {
            projectList = query
                    .setHint(QUERY_HINT, em.getEntityGraph(GRAPH_PROJECT_ALL))
                    .getResultList();
        }

        return new PageImpl<>(projectList, params.getPageable(), count);
    }

    @Override
    public Project save(Project project) {
        Project p = em.createNamedQuery("findProjectsByPhId", Project.class)
                .setParameter(PROPERTY_PRJ_ID, project.getPhId())
                .getSingleResult();
        if(p!=null){
            p.updateFields(project);
            em.merge(p);
        } else {
            em.persist(project);
        }
        return project;
    }

    @Override
    public List<Double> getFinancialAmountBoundaries() {
        List<Double> ret = new ArrayList<>();
        Object[] o = (Object[]) em.createNativeQuery("select max(p.total_project_amount), min(p.total_project_amount) from Project p").getSingleResult();
        ret.add((Double)o[0]);
        ret.add((Double)o[1]);
        return ret;
    }

    @Override
    public StatsResponse countProjectsByParams(Parameters params) {
        TypedQuery<Project> query = getProjectTypedQuery(params);

        int count = query.getResultList().size();
        StatsResponse response = new StatsResponse();
        response.setProjectCount(count);
        return response;
    }

    @Override
    public List<String> getImpPeriodBoundaries() {
        List<String> ret = new ArrayList<>();
        Object[] o = (Object[]) em.createNativeQuery("select max(p.start_date) as max_start_date, min(p.start_date) as min_start_date, max(p.end_date) as max_end_date, min(p.end_date) as min_end_date from project p").getSingleResult();
        ret.add(o[0].toString());
        ret.add(o[1].toString());
        ret.add(o[2].toString());
        ret.add(o[3].toString());
        return ret;
    }

    @Override
    public List<String> getGrantPeriodBoundaries() {
        List<String> ret = new ArrayList<>();
        Object[] o = (Object[]) em.createNativeQuery("select max(p.period_performance_start) as max_start_period, min(p.period_performance_start) as min_start_period, max(p.period_performance_end) as max_end_period, min(p.period_performance_end) as min_end_period from project p").getSingleResult();
        ret.add(o[0].toString());
        ret.add(o[1].toString());
        ret.add(o[2].toString());
        ret.add(o[3].toString());
        return ret;
    }

    @Override
    public List<Double> getReachedPhysicalProgressPeriod() {
        List<Double> ret = new ArrayList<>();
        Object[] o = (Object[]) em.createNativeQuery("select max(p.reached_owpa) as max_reached_owpa, min(p.reached_owpa) as min_reached_owpa from project p").getSingleResult();
        ret.add((Double)o[0]);
        ret.add((Double)o[1]);
        return ret;
    }

    @Override
    public List<Double> getTargetPhysicalProgressPeriod() {
        List<Double> ret = new ArrayList<>();
        Object[] o = (Object[]) em.createNativeQuery("select max(p.target_owpa) as max_target_owpa, min(p.target_owpa) as min_target_owpa from project p").getSingleResult();
        ret.add((Double)o[0]);
        ret.add((Double)o[1]);
        return ret;
    }

    @Override
    public List<Double> getActualPhysicalProgressPeriod() {
        List<Double> ret = new ArrayList<>();
        Object[] o = (Object[]) em.createNativeQuery("select max(p.actual_owpa) as max_actual_owpa, min(p.actual_owpa) as min_actual_owpa from project p").getSingleResult();
        ret.add((Double)o[0]);
        ret.add((Double)o[1]);
        return ret;
    }

    private TypedQuery<Project> getProjectTypedQuery(Parameters params) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Project> criteriaQuery = criteriaBuilder
                .createQuery(Project.class);
        Root<Project> projectRoot = criteriaQuery.from(Project.class);
        List<Predicate> predicates = new ArrayList<>();

        FilterHelper.filterProjectQuery(params, criteriaBuilder, projectRoot, predicates);

        if(predicates.size()>0) {
            Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            criteriaQuery.where(other);
        }

        if(params!=null && params.getProjectOrder()!=null){
            if(params.getProjectOrder().getAscending()){
                criteriaQuery.orderBy(criteriaBuilder.asc(projectRoot.get(params.getProjectOrder().getAttribute())));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(projectRoot.get(params.getProjectOrder().getAttribute())));
            }
        }

        CriteriaQuery<Project> cq = criteriaQuery.select(projectRoot).distinct(true);
        return em.createQuery(cq);
    }


}
