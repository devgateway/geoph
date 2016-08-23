package org.devgateway.geoph.persistence.util;

import org.apache.commons.lang3.StringUtils;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.model.*;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @author dbianco
 *         created on abr 05 2016.
 */
public class FilterHelper {

    private static final Object LOCK_PROJECT = new Object() {};
    private static final Object LOCK_LOCATION = new Object() {};
    private static final Object LOCK_TRANSACTION = new Object() {};

    public static void filterProjectQuery(Parameters params, CriteriaBuilder criteriaBuilder, Root<Project> projectRoot, List<Predicate> predicates) {
        synchronized (LOCK_PROJECT) {
            if (params != null) {
                if (params.getProjects() != null) {
                    predicates.add(projectRoot.get(Project_.id).in(params.getProjects()));
                }
                if (StringUtils.isNotBlank(params.getProjectTitle())){
                    predicates.add(criteriaBuilder.like(criteriaBuilder.upper(projectRoot.get(Project_.title)), "%" + params.getProjectTitle().toUpperCase() + "%"));
                }
                if (params.getSectors() != null) {
                    Join<Project, ProjectSector> sectorJoin = projectRoot.join(Project_.sectors);
                    Join<ProjectSector, ProjectSectorId> pk = sectorJoin.join(ProjectSector_.pk);
                    predicates.add(pk.get(ProjectSectorId_.sector).in(params.getSectors()));
                }
                if (params.getStatuses() != null) {
                    Join<Project, Status> statusJoin = projectRoot.join(Project_.status);
                    predicates.add(statusJoin.get(Status_.id).in(params.getStatuses()));
                }
                if (params.getPhysicalStatuses() != null){
                    Join<Project, PhysicalStatus> physicalStatusJoin = projectRoot.join(Project_.physicalStatus);
                    predicates.add(physicalStatusJoin.get(PhysicalStatus_.id).in(params.getPhysicalStatuses()));
                }
                if (params.getLocations() != null) {
                    Join<Project, Location> locationJoin = projectRoot.join(Project_.locations);
                    Predicate findInAnyAdmLevel = criteriaBuilder.or(
                            locationJoin.get(Location_.id).in(params.getLocations()),
                            locationJoin.get(Location_.regionId).in(params.getLocations()),
                            locationJoin.get(Location_.provinceId
                            ).in(params.getLocations()));
                    predicates.add(findInAnyAdmLevel);
                }
                if (params.getStartDateMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectRoot.get(Project_.startDate), params.getStartDateMin()));
                }
                if (params.getStartDateMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectRoot.get(Project_.startDate), params.getStartDateMax()));
                }
                if (params.getEndDateMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectRoot.get(Project_.endDate), params.getEndDateMin()));
                }
                if (params.getEndDateMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectRoot.get(Project_.endDate), params.getEndDateMax()));
                }
                if (params.getPeriodPerformanceStartMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectRoot.get(Project_.periodPerformanceStart), params.getPeriodPerformanceStartMin()));
                }
                if (params.getPeriodPerformanceStartMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectRoot.get(Project_.periodPerformanceStart), params.getPeriodPerformanceStartMax()));
                }
                if (params.getPeriodPerformanceEndMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectRoot.get(Project_.periodPerformanceEnd), params.getPeriodPerformanceEndMin()));
                }
                if (params.getPeriodPerformanceEndMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectRoot.get(Project_.periodPerformanceEnd), params.getPeriodPerformanceEndMax()));
                }
                if (params.getFundingAgencies() != null) {
                    Join<Project, Agency> fundingAgencyJoin = projectRoot.join(Project_.fundingAgency);
                    predicates.add(fundingAgencyJoin.get(FundingAgency_.id).in(params.getFundingAgencies()));
                }
                if (params.getImpAgencies() != null) {
                    Join<Project, ProjectAgency> impAgencyJoin = projectRoot.join(Project_.implementingAgencies);
                    Join<ProjectAgency, ProjectAgencyId> pk = impAgencyJoin.join(ProjectAgency_.pk);
                    predicates.add(pk.get(ProjectAgencyId_.agency).in(params.getImpAgencies()));
                }
                if (params.getFlowTypes() != null || params.getGrantSubTypes() != null) {
                    Predicate ft = null;
                    boolean isFlowType = false;
                    if(params.getFlowTypes()!=null){
                        Join<Project, Transaction> transactionJoin = projectRoot.join(Project_.transactions);
                        ft = transactionJoin.get(Transaction_.flowType).in(params.getFlowTypes());
                        isFlowType = true;
                    }
                    Predicate gst = null;
                    boolean isGrantType = false;
                    if(params.getGrantSubTypes()!=null) {
                        Join<Project, Transaction> transactionJoin = projectRoot.join(Project_.transactions);
                        gst = transactionJoin.get(Transaction_.grantSubTypeId).in(params.getGrantSubTypes());
                        isGrantType = true;
                    }
                    if(isFlowType && isGrantType) {
                        predicates.add(criteriaBuilder.or(ft, gst));
                    } else if (isFlowType){
                        predicates.add(criteriaBuilder.or(ft));
                    } else if (isGrantType){
                        predicates.add(criteriaBuilder.or(gst));
                    }
                }
                if (params.getClimateChanges() != null) {
                    Join<Project, ClimateChange> climateChangeJoin = projectRoot.join(Project_.climateChange);
                    predicates.add(climateChangeJoin.get(ClimateChange_.id).in(params.getClimateChanges()));
                }
                if (params.getGenderResponsiveness() != null) {
                    Join<Project, GenderResponsiveness> genderResponsivenessJoin = projectRoot.join(Project_.genderResponsiveness);
                    predicates.add(genderResponsivenessJoin.get(GenderResponsiveness_.id).in(params.getGenderResponsiveness()));
                }
                if(params.getFinancialAmountMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectRoot.get(Project_.totalProjectAmount), params.getFinancialAmountMin()));
                }
                if(params.getFinancialAmountMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectRoot.get(Project_.totalProjectAmount), params.getFinancialAmountMax()));
                }
                if(params.getReachedOwpaMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectRoot.get(Project_.reachedOwpa), params.getReachedOwpaMax()));
                }
                if(params.getReachedOwpaMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectRoot.get(Project_.reachedOwpa), params.getReachedOwpaMin()));
                }
                if(params.getTargetOwpaMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectRoot.get(Project_.targetOwpa), params.getTargetOwpaMax()));
                }
                if(params.getTargetOwpaMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectRoot.get(Project_.targetOwpa), params.getTargetOwpaMin()));
                }
                if(params.getActualOwpaMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectRoot.get(Project_.actualOwpa), params.getActualOwpaMax()));
                }
                if(params.getActualOwpaMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectRoot.get(Project_.actualOwpa), params.getActualOwpaMin()));
                }
            }
        }
    }

    public static void filterLocationQuery(Parameters params, CriteriaBuilder criteriaBuilder, Root<Location> locationRoot, List<Predicate> predicates, Join<Location, Project> projectJoin) {
        synchronized (LOCK_LOCATION) {
            if (params != null) {
            /*if(params.getLocationLevels()!=null) {
                predicates.add(locationRoot.get(Location_.level).in(params.getLocationLevels()));
            }*/
                if (params.getLocations() != null) {
                    predicates.add(locationRoot.get(Location_.id).in(params.getLocations()));
                }
                if (params.getProjects() != null) {
                    predicates.add(projectJoin.in(params.getProjects()));
                }
                if (StringUtils.isNotBlank(params.getProjectTitle())) {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.upper(projectJoin.get(Project_.title)), "%" + params.getProjectTitle().toUpperCase() + "%"));
                }
                if (params.getSectors() != null) {
                    Join<Project, ProjectSector> sectorJoin = projectJoin.join(Project_.sectors, JoinType.LEFT);
                    Join<ProjectSector, ProjectSectorId> pk = sectorJoin.join(ProjectSector_.pk);
                    predicates.add(pk.get(ProjectSectorId_.sector).in(params.getSectors()));
                }
                if (params.getStatuses() != null) {
                    Join<Project, Status> statusJoin = projectJoin.join(Project_.status);
                    predicates.add(statusJoin.get(Status_.id).in(params.getStatuses()));
                }
                if (params.getPhysicalStatuses() != null) {
                    Join<Project, PhysicalStatus> physicalStatusJoin = projectJoin.join(Project_.physicalStatus);
                    predicates.add(physicalStatusJoin.get(PhysicalStatus_.id).in(params.getPhysicalStatuses()));
                }
                if (params.getStartDateMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectJoin.get(Project_.startDate), params.getStartDateMin()));
                }
                if (params.getStartDateMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectJoin.get(Project_.startDate), params.getStartDateMax()));
                }
                if (params.getEndDateMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectJoin.get(Project_.endDate), params.getEndDateMin()));
                }
                if (params.getEndDateMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectJoin.get(Project_.endDate), params.getEndDateMax()));
                }
                if (params.getPeriodPerformanceStartMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectJoin.get(Project_.periodPerformanceStart), params.getPeriodPerformanceStartMin()));
                }
                if (params.getPeriodPerformanceStartMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectJoin.get(Project_.periodPerformanceStart), params.getPeriodPerformanceStartMax()));
                }
                if (params.getPeriodPerformanceEndMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectJoin.get(Project_.periodPerformanceEnd), params.getPeriodPerformanceEndMin()));
                }
                if (params.getPeriodPerformanceEndMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectJoin.get(Project_.periodPerformanceEnd), params.getPeriodPerformanceEndMax()));
                }
                if (params.getFundingAgencies() != null) {
                    Join<Project, Agency> fundingAgencyJoin = projectJoin.join(Project_.fundingAgency, JoinType.LEFT);
                    predicates.add(fundingAgencyJoin.get(FundingAgency_.id).in(params.getFundingAgencies()));
                }
                if (params.getImpAgencies() != null) {
                    Join<Project, ProjectAgency> impAgencyJoin = projectJoin.join(Project_.implementingAgencies, JoinType.LEFT);
                    Join<ProjectAgency, ProjectAgencyId> pk = impAgencyJoin.join(ProjectAgency_.pk);
                    predicates.add(pk.get(ProjectAgencyId_.agency).in(params.getImpAgencies()));
                }
                if (params.getFlowTypes() != null || params.getGrantSubTypes() != null) {
                    Predicate ft = null;
                    boolean isFlowType = false;
                    if(params.getFlowTypes()!=null){
                        Join<Project, Transaction> transactionJoin = projectJoin.join(Project_.transactions);
                        ft = transactionJoin.get(Transaction_.flowType).in(params.getFlowTypes());
                        isFlowType = true;
                    }
                    Predicate gst = null;
                    boolean isGrantType = false;
                    if(params.getGrantSubTypes()!=null) {
                        Join<Project, Transaction> transactionJoin = projectJoin.join(Project_.transactions);
                        gst = transactionJoin.get(Transaction_.grantSubTypeId).in(params.getGrantSubTypes());
                        isGrantType = true;
                    }
                    if(isFlowType && isGrantType) {
                        predicates.add(criteriaBuilder.or(ft, gst));
                    } else if (isFlowType){
                        predicates.add(criteriaBuilder.or(ft));
                    } else if (isGrantType){
                        predicates.add(criteriaBuilder.or(gst));
                    }
                }
                if (params.getClimateChanges() != null) {
                    Join<Project, ClimateChange> climateChangeJoin = projectJoin.join(Project_.climateChange);
                    predicates.add(climateChangeJoin.get(ClimateChange_.id).in(params.getClimateChanges()));
                }
                if (params.getGenderResponsiveness() != null) {
                    Join<Project, GenderResponsiveness> genderResponsivenessJoin = projectJoin.join(Project_.genderResponsiveness);
                    predicates.add(genderResponsivenessJoin.get(GenderResponsiveness_.id).in(params.getGenderResponsiveness()));
                }
                if(params.getFinancialAmountMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectJoin.get(Project_.totalProjectAmount), params.getFinancialAmountMin()));
                }
                if(params.getFinancialAmountMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectJoin.get(Project_.totalProjectAmount), params.getFinancialAmountMax()));
                }
                if(params.getReachedOwpaMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectJoin.get(Project_.reachedOwpa), params.getReachedOwpaMin()));
                }
                if(params.getReachedOwpaMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectJoin.get(Project_.reachedOwpa), params.getReachedOwpaMax()));
                }
                if(params.getTargetOwpaMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectJoin.get(Project_.targetOwpa), params.getTargetOwpaMin()));
                }
                if(params.getTargetOwpaMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectJoin.get(Project_.targetOwpa), params.getTargetOwpaMax()));
                }
                if(params.getActualOwpaMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectJoin.get(Project_.actualOwpa), params.getActualOwpaMin()));
                }
                if(params.getActualOwpaMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectJoin.get(Project_.actualOwpa), params.getActualOwpaMax()));
                }
            }
        }
    }

    public static void addTransactionJoin(CriteriaBuilder criteriaBuilder, List<Selection<?>> multiSelect,
                                    Root<Project> projectRoot, int trxType, int trxStatus) {
        synchronized (LOCK_TRANSACTION) {
            Join<Project, Transaction> transactionJoin = projectRoot.join(Project_.transactions, JoinType.LEFT);
            transactionJoin.on(transactionJoin.get(Transaction_.transactionTypeId).in(trxType),
                    transactionJoin.get(Transaction_.transactionStatusId).in(trxStatus));
            multiSelect.add(transactionJoin.get(Transaction_.amount));
            multiSelect.add(transactionJoin.get(Transaction_.id));
        }
    }
}
