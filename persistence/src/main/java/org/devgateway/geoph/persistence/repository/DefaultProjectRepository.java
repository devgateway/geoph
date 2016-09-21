package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.core.repositories.ProjectRepository;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.response.StatsResponse;
import org.devgateway.geoph.dao.ProjectMiniDao;
import org.devgateway.geoph.dao.ProjectStatsResultsDao;
import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.util.FilterHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.devgateway.geoph.core.constants.Constants.*;



/**
 * @author dbianco
 *         created on mar 09 2016.
 */
@Service
@Transactional
public class DefaultProjectRepository implements ProjectRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultProjectRepository.class);

    @Autowired
    EntityManager em;

    @Override
    public List<Project> findAll() {
        return em.createNamedQuery("findAllProjects", Project.class)
                .setHint(QUERY_HINT, em.getEntityGraph(GRAPH_PROJECT_ALL))
                .getResultList();
    }

    @Override
    @Cacheable("findProjectsById")
    public Project findById(long id) {
        return em.createNamedQuery("findProjectsById", Project.class)
                .setParameter(PROPERTY_PRJ_ID, id)
                .setHint(QUERY_HINT, em.getEntityGraph(GRAPH_PROJECT_ALL))
                .getSingleResult();
    }

    @Override
    public Project findByPhId(String phid) {
           List<Project> projects=em.createNamedQuery("findProjectsByPhId", Project.class)
                .setParameter(PROPERTY_PRJ_PH_ID, phid).getResultList();
        if (projects.isEmpty()){
            return null;
        }else{
            if(projects.size() > 1){
                LOGGER.warn("..... Warning PH_ID "+phid+" used in "+projects.size()+ " projects this function wil return first result .... ");
            }
            return  projects.iterator().next();
        }
    }

    @Override
    @Cacheable("findProjectMiniByParams")
    public Page<ProjectMiniDao> findProjectMiniByParams(Parameters params) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ProjectMiniDao> criteriaQuery = criteriaBuilder.createQuery(ProjectMiniDao.class);
        Root<Project> projectRoot = criteriaQuery.from(Project.class);
        List<Selection<?>> multiSelect = new ArrayList<>();
        List<Expression<?>> groupByList = new ArrayList<>();

        multiSelect.add(projectRoot.get(Project_.id));
        groupByList.add(projectRoot.get(Project_.id));
        multiSelect.add(projectRoot.get(Project_.title));
        groupByList.add(projectRoot.get(Project_.title));

        List<Predicate> predicates = new ArrayList<>();
        FilterHelper.filterProjectQuery(params, criteriaBuilder, projectRoot, predicates, null, null);

        if(predicates.size()>0) {
            Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            criteriaQuery.where(other);
        }
        criteriaQuery.groupBy(groupByList);
        criteriaQuery.orderBy(criteriaBuilder.asc(projectRoot.get(Project_.title)));
        TypedQuery<ProjectMiniDao> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

        List<ProjectMiniDao> projectList = query.getResultList();
        int count = query.getResultList().size();
        return new PageImpl<>(projectList, params.getPageable(), count);
    }

    @Override
    @Cacheable("findProjectsByParams")
    public List<ProjectMiniDao> findProjectsByParams(Parameters params) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ProjectMiniDao> criteriaQuery = criteriaBuilder.createQuery(ProjectMiniDao.class);
        Root<Project> projectRoot = criteriaQuery.from(Project.class);

        List<Selection<?>> multiSelect = new ArrayList<>();
        List<Predicate> predicates = new ArrayList<>();
        List<Expression<?>> groupByList = new ArrayList<>();

        Join<Project, Agency> agencyJoin = projectRoot.join(Project_.fundingAgency, JoinType.LEFT);
        Join<Project, Transaction> transactionJoin = projectRoot.join(Project_.transactions, JoinType.LEFT);

        Expression<Double> expression = transactionJoin.get(Transaction_.amount);
        expression = FilterHelper.filterProjectQuery(params, criteriaBuilder, projectRoot, predicates, expression, transactionJoin);

        multiSelect.add(projectRoot.get(Project_.id));
        groupByList.add(projectRoot.get(Project_.id));

        multiSelect.add(projectRoot.get(Project_.title));
        groupByList.add(projectRoot.get(Project_.title));

        multiSelect.add(agencyJoin.get(Agency_.id));
        groupByList.add(agencyJoin.get(Agency_.id));

        multiSelect.add(agencyJoin.get(Agency_.name));
        groupByList.add(agencyJoin.get(Agency_.name));

        multiSelect.add(agencyJoin.get(Agency_.code));
        groupByList.add(agencyJoin.get(Agency_.code));

        multiSelect.add(criteriaBuilder.sum(expression));

        multiSelect.add(transactionJoin.get(Transaction_.transactionStatusId));
        groupByList.add(transactionJoin.get(Transaction_.transactionStatusId));

        multiSelect.add(transactionJoin.get(Transaction_.transactionTypeId));
        groupByList.add(transactionJoin.get(Transaction_.transactionTypeId));

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
        criteriaQuery.groupBy(groupByList);
        TypedQuery<ProjectMiniDao> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

        return  query.getResultList();
    }

    @Override
    @Cacheable("getProjectStats")
    public Map<String, List<ProjectStatsResultsDao>> getStats(Parameters params) {
        Map<String, List<ProjectStatsResultsDao>> ret = new HashMap<>();
        List<ProjectStatsResultsDao> national = getStatsByAdmLevel(params, true);
        List<ProjectStatsResultsDao> regional = getStatsByAdmLevel(params, false);
        //Fix for UI, it needs at least one result per ADM level
        if(national.size()==0){
            national.add(new ProjectStatsResultsDao(0D, 1L, 1L));
        }
        if(regional.size()==0){
            regional.add(new ProjectStatsResultsDao(0D, 1L, 1L));
        }

        final Long nationalCount = getStatsCountByAdmLevel(params, true);
        final Long regionalCount = getStatsCountByAdmLevel(params, false);

        national.stream().forEach(loc -> loc.setProjectCount(nationalCount));
        regional.stream().forEach(loc -> loc.setProjectCount(regionalCount));

        ret.put(NATIONAL, national);
        ret.put(REGIONAL, regional);
        return ret;
    }

    private Long getStatsCountByAdmLevel(Parameters params, boolean isNationalLevel) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Project> projectRoot = criteriaQuery.from(Project.class);
        List<Selection<?>> multiSelect = new ArrayList<>();

        Join<Project, ProjectLocation> locationJoin = projectRoot.join(Project_.locations, JoinType.LEFT);
        Join<ProjectLocation, ProjectLocationId> pk = locationJoin.join(ProjectLocation_.pk, JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();
        FilterHelper.filterProjectQuery(params, criteriaBuilder, projectRoot, predicates, null, null);

        multiSelect.add(criteriaBuilder.countDistinct(projectRoot.get(Project_.id)).alias("projectCount"));

        if(isNationalLevel) {
            predicates.add(pk.get(ProjectLocationId_.location).isNull());
        } else {
            predicates.add(pk.get(ProjectLocationId_.location).isNotNull());
        }

        if(predicates.size()>0) {
            Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            criteriaQuery.where(other);
        }

        List<Expression<?>> groupByList = new ArrayList<>();
        criteriaQuery.groupBy(groupByList);

        TypedQuery<Long> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

        return query.getSingleResult();
    }

    private List<ProjectStatsResultsDao> getStatsByAdmLevel(Parameters params, boolean isNationalLevel) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ProjectStatsResultsDao> criteriaQuery = criteriaBuilder.createQuery(ProjectStatsResultsDao.class);
        Root<Project> projectRoot = criteriaQuery.from(Project.class);
        List<Selection<?>> multiSelect = new ArrayList<>();

        Join<Project, Transaction> transactionJoin = projectRoot.join(Project_.transactions, JoinType.LEFT);
        Join<Project, ProjectLocation> locationJoin = projectRoot.join(Project_.locations, JoinType.LEFT);
        Join<ProjectLocation, ProjectLocationId> pk = locationJoin.join(ProjectLocation_.pk, JoinType.LEFT);
        Expression<Double> expression = null;
        if(isNationalLevel) {
            expression = transactionJoin.get(Transaction_.amount);
        } else {
            expression = criteriaBuilder.prod(transactionJoin.get(Transaction_.amount), locationJoin.get(ProjectLocation_.utilization));
        }
        List<Predicate> predicates = new ArrayList<>();
        expression = FilterHelper.filterProjectQuery(params, criteriaBuilder, projectRoot, predicates, expression, transactionJoin);

        multiSelect.add(criteriaBuilder.sum(expression));
        multiSelect.add(transactionJoin.get(Transaction_.transactionStatusId).alias("statusId"));
        multiSelect.add(transactionJoin.get(Transaction_.transactionTypeId).alias("typeId"));

        if(isNationalLevel) {
            predicates.add(pk.get(ProjectLocationId_.location).isNull());
        } else {
            predicates.add(pk.get(ProjectLocationId_.location).isNotNull());
        }

        if(predicates.size()>0) {
            Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            criteriaQuery.where(other);
        }

        List<Expression<?>> groupByList = new ArrayList<>();
        groupByList.add(transactionJoin.get(Transaction_.transactionStatusId));
        groupByList.add(transactionJoin.get(Transaction_.transactionTypeId));
        criteriaQuery.groupBy(groupByList);

        TypedQuery<ProjectStatsResultsDao> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

        return query.getResultList();
    }

    @Override
    public Project save(Project project) {
        Project p = null;
        try {
            p = em.createNamedQuery("findProjectsByPhId", Project.class)
                    .setParameter(PROPERTY_PRJ_PH_ID, project.getPhId())
                    .getSingleResult();
        } catch (Exception e) {
            LOGGER.debug("Project not found!");
        }
        if(p!=null){
            p.updateFields(project);
            em.merge(p);
        } else {
            em.persist(project);
            if(project.getTransactions()!=null) {
                project.getTransactions().forEach(em::persist);
            }
            if(project.getSectors()!=null) {
                project.getSectors().forEach(em::persist);
            }
            if(project.getImplementingAgencies()!=null) {
                project.getImplementingAgencies().forEach(em::persist);
            }
            if(project.getLocations()!=null) {
                project.getLocations().forEach(em::persist);
            }
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

    @Override
    public List<Double> getPhysicalProgressPeriod() {
        List<Double> ret = new ArrayList<>();
        Object[] o = (Object[]) em.createNativeQuery("select max(p.physical_progress) as max_php, min(p.physical_progress) as min_php from project p").getSingleResult();
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

        FilterHelper.filterProjectQuery(params, criteriaBuilder, projectRoot, predicates, null, null);

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
