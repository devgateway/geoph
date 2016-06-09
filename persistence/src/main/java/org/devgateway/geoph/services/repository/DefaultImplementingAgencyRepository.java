package org.devgateway.geoph.services.repository;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.services.util.FilterHelper;
import org.devgateway.geoph.util.Parameters;
import org.devgateway.geoph.util.TransactionStatusEnum;
import org.devgateway.geoph.util.TransactionTypeEnum;
import org.devgateway.geoph.util.queries.AgencyResultsQueryHelper;
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
 *         created on abr 04 2016.
 */
@Service
public class DefaultImplementingAgencyRepository implements ImplementingAgencyRepository {

    @Autowired
    EntityManager em;

    @Override
    public List<ImplementingAgency> findAll() {
        return em.createNamedQuery("findAllImplementingAgency", ImplementingAgency.class).getResultList();
    }

    @Override
    public Integer count() {
        return ((BigInteger) em.createNativeQuery("select count(*) from agency a where a.discriminator like 'implementing_agency'").getSingleResult()).intValue();
    }

    @Override
    public List<AgencyResultsQueryHelper> findFundingByImplementingAgency(Parameters params, int trxTypeId, int trxStatusId) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<AgencyResultsQueryHelper> criteriaQuery = criteriaBuilder.createQuery(AgencyResultsQueryHelper.class);

        Root<Project> projectRoot = criteriaQuery.from(Project.class);

        List<Selection<?>> multiSelect = new ArrayList<>();
        List<Predicate> predicates = new ArrayList();
        List<Expression<?>> groupByList = new ArrayList<>();

        Join<Project, Agency> agencyJoin = projectRoot.join(Project_.implementingAgencies);
        multiSelect.add(agencyJoin);
        multiSelect.add(criteriaBuilder.countDistinct(projectRoot));
        groupByList.add(agencyJoin);

        if(trxTypeId!=0 && trxStatusId!=0) {
            FilterHelper.addTransactionJoin(criteriaBuilder, multiSelect, projectRoot, trxTypeId, trxStatusId);
        }

        FilterHelper.filterProjectQuery(params, criteriaBuilder, projectRoot, predicates);

        Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(other);


        criteriaQuery.groupBy(groupByList);
        TypedQuery<AgencyResultsQueryHelper> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

        return query.getResultList();
    }



}
