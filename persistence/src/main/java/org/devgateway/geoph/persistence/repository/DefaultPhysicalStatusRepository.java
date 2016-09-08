package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.core.repositories.PhysicalStatusRepository;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.PhysicalStatusDao;
import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.util.FilterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dbianco
 *         created on abr 12 2016.
 */
@Service
public class DefaultPhysicalStatusRepository implements PhysicalStatusRepository {

    @Autowired
    EntityManager em;

    @Override
    public List<PhysicalStatus> findAll() {
        return em.createNamedQuery("findAllPhysicalStatus", PhysicalStatus.class)
                .getResultList();
    }

    @Override
    public PhysicalStatus findByName(String name) {
        return em.createNamedQuery("findPhysicalStatusByName", PhysicalStatus.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    @Cacheable("findPhysicalStatusById")
    public PhysicalStatus findById(Long id) {
        return em.createNamedQuery("findPhysicalStatusById", PhysicalStatus.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public PhysicalStatus findByCode(String code) {
        return em.createNamedQuery("findPhysicalStatusByCode", PhysicalStatus.class)
                .setParameter("code", code)
                .getSingleResult();
    }

    @Override
    @Cacheable("findPhysicalStatusByParams")
    public List<PhysicalStatusDao> findFundingByPhysicalStatus(Parameters params, int trxType, int trxStatus) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<PhysicalStatusDao> criteriaQuery = criteriaBuilder.createQuery(PhysicalStatusDao.class);

        Root<Project> projectRoot = criteriaQuery.from(Project.class);

        List<Selection<?>> multiSelect = new ArrayList<>();
        List<Predicate> predicates = new ArrayList<>();
        List<Expression<?>> groupByList = new ArrayList<>();

        Join<Project, PhysicalStatus> physicalStatusJoin = projectRoot.join(Project_.physicalStatus);
        Join<Project, Transaction> transactionJoin = projectRoot.join(Project_.transactions);

        multiSelect.add(physicalStatusJoin);
        Expression<Double> expression = FilterHelper.filterProjectQuery(params, criteriaBuilder, projectRoot, predicates, transactionJoin.get(Transaction_.amount));
        multiSelect.add(criteriaBuilder.sum(expression));

        multiSelect.add(criteriaBuilder.count(projectRoot.get(Project_.id)));
        groupByList.add(physicalStatusJoin);

        predicates.add(transactionJoin.get(Transaction_.transactionTypeId).in(trxType));
        predicates.add(transactionJoin.get(Transaction_.transactionStatusId).in(trxStatus));
        Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(other);

        criteriaQuery.groupBy(groupByList);
        TypedQuery<PhysicalStatusDao> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

        return query.getResultList();
    }
}
