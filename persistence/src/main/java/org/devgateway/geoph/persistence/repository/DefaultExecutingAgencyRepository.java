package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.core.repositories.ExecutingAgencyRepository;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.enums.TransactionStatusEnum;
import org.devgateway.geoph.enums.TransactionTypeEnum;
import org.devgateway.geoph.model.Agency;
import org.devgateway.geoph.model.ExecutingAgency;
import org.devgateway.geoph.model.Project;
import org.devgateway.geoph.model.Project_;
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
    public Integer countAll() {
        return ((BigInteger) em.createNativeQuery("select count(*) from agency a where a.discriminator like 'executing_agency'").getSingleResult()).intValue();
    }

    @Override
    public List<Object> findFundingByExecutingAgency(Parameters params) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();

        Root<Project> projectRoot = criteriaQuery.from(Project.class);

        List<Selection<?>> multiSelect = new ArrayList<>();
        List<Predicate> predicates = new ArrayList();
        List<Expression<?>> groupByList = new ArrayList<>();

        Join<Project, Agency> agencyJoin = projectRoot.join(Project_.executingAgency);
        multiSelect.add(agencyJoin);
        multiSelect.add(criteriaBuilder.countDistinct(projectRoot).alias("projectCount"));
        groupByList.add(agencyJoin);

        for (TransactionTypeEnum t : TransactionTypeEnum.values()) {
            for (TransactionStatusEnum s : TransactionStatusEnum.values()) {
                FilterHelper.addTransactionJoin(criteriaBuilder, multiSelect, projectRoot, t.getId(), s.getId());
            }
        }

        FilterHelper.filterProjectQuery(params, criteriaBuilder, projectRoot, predicates);

        Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(other);


        criteriaQuery.groupBy(groupByList);
        TypedQuery<Object> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

        return query.getResultList();
    }

}
