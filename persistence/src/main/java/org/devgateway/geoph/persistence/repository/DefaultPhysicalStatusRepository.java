package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.util.FilterHelper;
import org.devgateway.geoph.util.FlowType;
import org.devgateway.geoph.util.Parameters;
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
    public List<Object> findFundingByPhysicalStatus(Parameters params) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();

        Root<Project> projectRoot = criteriaQuery.from(Project.class);

        List<Selection<?>> multiSelect = new ArrayList<>();
        List<Predicate> predicates = new ArrayList();
        List<Expression<?>> groupByList = new ArrayList<>();

        Join<Project, PhysicalStatus> physicalStatusJoin = projectRoot.join(Project_.physicalStatus);
        multiSelect.add(physicalStatusJoin);
        multiSelect.add(criteriaBuilder.countDistinct(projectRoot).alias("projectCount"));
        groupByList.add(physicalStatusJoin);

        Join<Project, Transaction> transaction0Join = projectRoot.join(Project_.transactions);
        multiSelect.add(criteriaBuilder.countDistinct(transaction0Join).alias("transactionCount"));

        Join<Project, Transaction> transaction1Join = projectRoot.join(Project_.transactions, JoinType.LEFT);
        transaction1Join.on(transaction1Join.get(Transaction_.flowType).in(FlowType.LOAN.name().toLowerCase()));
        multiSelect.add(criteriaBuilder.sum(transaction1Join.get(Transaction_.amount)).alias("loans"));

        Join<Project, Transaction> transaction2Join = projectRoot.join(Project_.transactions, JoinType.LEFT);
        transaction2Join.on(transaction2Join.get(Transaction_.flowType).in(FlowType.GRANT.name().toLowerCase()));
        multiSelect.add(criteriaBuilder.sum(transaction2Join.get(Transaction_.amount)).alias("grants"));

        Join<Project, Transaction> transaction3Join = projectRoot.join(Project_.transactions, JoinType.LEFT);
        transaction3Join.on(transaction3Join.get(Transaction_.flowType).in(FlowType.PMC.name().toLowerCase()));
        multiSelect.add(criteriaBuilder.sum(transaction3Join.get(Transaction_.amount)).alias("pmcs"));

        FilterHelper.filterProjectQuery(params, criteriaBuilder, projectRoot, predicates);

        Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(other);


        criteriaQuery.groupBy(groupByList);
        TypedQuery<Object> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

        return query.getResultList();
    }
}
