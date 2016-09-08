package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.ChartProjectCountDao;
import org.devgateway.geoph.core.repositories.ExecutingAgencyRepository;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.AgencyResultsDao;
import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.util.FilterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dbianco
 *         created on may 05 2016.
 */
@Service
public class DefaultExecutingAgencyRepository implements ExecutingAgencyRepository {

    @Autowired
    EntityManager em;

    @Override
    public List<ExecutingAgency> findAll() {
        return em.createNamedQuery("findAllExecutingAgency", ExecutingAgency.class).getResultList();
    }

    @Override
    public ExecutingAgency findById(Long id) {
        return em.createNamedQuery("findExecutingAgencyById", ExecutingAgency.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Integer countAll() {
        return ((BigInteger) em.createNativeQuery("select count(*) from agency a where a.discriminator like 'executing_agency'").getSingleResult()).intValue();
    }

    @Override
    public List<ChartProjectCountDao> findFundingByExecutingAgencyWithProjectStats(Parameters params) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ChartProjectCountDao> criteriaQuery = criteriaBuilder.createQuery(ChartProjectCountDao.class);

        Root<Project> projectRoot = criteriaQuery.from(Project.class);

        List<Selection<?>> multiSelect = new ArrayList<>();
        List<Predicate> predicates = new ArrayList<>();
        List<Expression<?>> groupByList = new ArrayList<>();

        Join<Project, Agency> agencyJoin = projectRoot.join(Project_.executingAgency);

        multiSelect.add(agencyJoin.get(Agency_.id));
        groupByList.add(agencyJoin.get(Agency_.id));

        multiSelect.add(criteriaBuilder.countDistinct(projectRoot));

        FilterHelper.filterProjectQuery(params, criteriaBuilder, projectRoot, predicates, null);

        Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(other);

        criteriaQuery.groupBy(groupByList);
        TypedQuery<ChartProjectCountDao> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

        return query.getResultList();
    }

    @Override
    public List<AgencyResultsDao> findFundingByExecutingAgencyWithTransactionStats(Parameters params) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<AgencyResultsDao> criteriaQuery = criteriaBuilder.createQuery(AgencyResultsDao.class);

        Root<Project> projectRoot = criteriaQuery.from(Project.class);

        List<Selection<?>> multiSelect = new ArrayList<>();
        List<Predicate> predicates = new ArrayList<>();
        List<Expression<?>> groupByList = new ArrayList<>();

        Join<Project, Agency> agencyJoin = projectRoot.join(Project_.executingAgency);
        Join<Project, Transaction> transactionJoin = projectRoot.join(Project_.transactions);

        multiSelect.add(agencyJoin);
        groupByList.add(agencyJoin);

        Expression<Double> expression = FilterHelper.filterProjectQuery(params, criteriaBuilder, projectRoot, predicates, transactionJoin.get(Transaction_.amount));
        multiSelect.add(criteriaBuilder.sum(expression));

        multiSelect.add(transactionJoin.get(Transaction_.transactionTypeId));
        groupByList.add(transactionJoin.get(Transaction_.transactionTypeId));
        multiSelect.add(transactionJoin.get(Transaction_.transactionStatusId));
        groupByList.add(transactionJoin.get(Transaction_.transactionStatusId));

        Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(other);

        criteriaQuery.groupBy(groupByList);
        TypedQuery<AgencyResultsDao> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

        return query.getResultList();
    }
}
