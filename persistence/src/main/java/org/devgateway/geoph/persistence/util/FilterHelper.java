package org.devgateway.geoph.persistence.util;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.util.Parameters;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author dbianco
 *         created on abr 05 2016.
 */
public class FilterHelper {

    private static final Object LOCK_PROJECT = new Object() {};

    public static void filterProjectQuery(Parameters params, CriteriaBuilder criteriaBuilder, Root<Project> projectRoot, List<Predicate> predicates) {
        synchronized (LOCK_PROJECT) {
            if (params != null) {
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
        }
    }
}
