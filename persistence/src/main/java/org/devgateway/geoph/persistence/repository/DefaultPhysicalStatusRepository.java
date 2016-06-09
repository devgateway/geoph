package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.util.FilterHelper;
import org.devgateway.geoph.util.Parameters;
import org.devgateway.geoph.util.TransactionStatusEnum;
import org.devgateway.geoph.util.TransactionTypeEnum;
import org.devgateway.geoph.util.queries.PhysicalStatusQueryHelper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public PhysicalStatus findByCode(String code) {
        return em.createNamedQuery("findPhysicalStatusByCode", PhysicalStatus.class)
                .setParameter("code", code)
                .getSingleResult();
    }

    @Override
    public List<PhysicalStatusQueryHelper> findFundingByPhysicalStatus(Parameters params, int trxTypeId, int trxStatusId) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<PhysicalStatusQueryHelper> criteriaQuery = criteriaBuilder.createQuery(PhysicalStatusQueryHelper.class);

        Root<Project> projectRoot = criteriaQuery.from(Project.class);

        List<Selection<?>> multiSelect = new ArrayList<>();
        List<Predicate> predicates = new ArrayList();
        List<Expression<?>> groupByList = new ArrayList<>();

        Join<Project, PhysicalStatus> physicalStatusJoin = projectRoot.join(Project_.physicalStatus);
        multiSelect.add(physicalStatusJoin);
        multiSelect.add(criteriaBuilder.countDistinct(projectRoot));
        groupByList.add(physicalStatusJoin);

        if(trxTypeId!=0 && trxStatusId!=0) {
            FilterHelper.addTransactionJoin(criteriaBuilder, multiSelect, projectRoot, trxTypeId, trxStatusId);
        }

        FilterHelper.filterProjectQuery(params, criteriaBuilder, projectRoot, predicates);

        Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(other);


        criteriaQuery.groupBy(groupByList);
        TypedQuery<PhysicalStatusQueryHelper> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

        return query.getResultList();
    }
}
