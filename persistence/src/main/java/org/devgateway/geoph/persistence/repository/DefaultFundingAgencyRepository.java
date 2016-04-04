package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.*;
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
 *         created on abr 04 2016.
 */
@Service
public class DefaultFundingAgencyRepository implements FundingAgencyRepository {

    @Autowired
    EntityManager em;

    @Override
    public List<FundingAgency> findAll() {
        return em.createNamedQuery("findAllFundingAgency", FundingAgency.class).getResultList();
    }

    @Override
    public List<Object> findFundingByFundingAgency(Parameters params){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();

        Root<Project> projectRoot = criteriaQuery.from(Project.class);

        List<Selection<?>> multiSelect = new ArrayList<>();
        List<Predicate> predicates = new ArrayList();
        List<Expression<?>> groupByList = new ArrayList<>();

        Join<Project, Agency> agencyJoin = projectRoot.join(Project_.fundingAgency);
        multiSelect.add(agencyJoin);
        multiSelect.add(criteriaBuilder.countDistinct(projectRoot).alias("projectCount"));
        groupByList.add(agencyJoin);

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

        if(params!=null) {
            if (params.getProjects() != null) {
                predicates.add(projectRoot.get(Project_.id).in(params.getProjects()));
            }
            if (params.getSectors() != null) {
                Join<Project, Sector> sectorJoin = projectRoot.join(Project_.sectors);
                predicates.add(sectorJoin.get(Sector_.id).in(params.getSectors()));
            }
            if (params.getStatuses() != null) {
                Join<Project, Status> statusJoin = projectRoot.join(Project_.status);
                predicates.add(statusJoin.get(Status_.id).in(params.getStatuses()));
            }
            if (params.getLocations() != null) {
                Join<Project, Location> locationJoin = projectRoot.join(Project_.locations);
                predicates.add(locationJoin.get(Location_.id).in(params.getLocations()));
            }
            if (params.getStartDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectRoot.get(Project_.periodStart), params.getStartDate()));
            }
            if (params.getEndDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(projectRoot.get(Project_.periodEnd), params.getEndDate()));
            }
            if (params.getFundingAgencies() != null) {
                Join<Project, Agency> fundingAgencyJoin = projectRoot.join(Project_.fundingAgency);
                predicates.add(fundingAgencyJoin.get(FundingAgency_.id).in(params.getFundingAgencies()));
            }
            if (params.getImpAgencies() != null) {
                Join<Project, Agency> impAgencyJoin = projectRoot.join(Project_.implementingAgency);
                predicates.add(impAgencyJoin.get(ImplementingAgency_.id).in(params.getImpAgencies()));
            }
            if (params.getFlowTypes() != null) {
                Join<Project, Transaction> transactionJoin = projectRoot.join(Project_.transactions);
                predicates.add(transactionJoin.get(Transaction_.flowType).in(params.getFlowTypes()));
            }
        }

        Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(other);


        criteriaQuery.groupBy(groupByList);
        TypedQuery<Object> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

        return query.getResultList();
    }

}
