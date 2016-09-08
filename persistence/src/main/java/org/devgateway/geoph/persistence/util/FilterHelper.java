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

    public static Expression<Double> filterProjectQuery(Parameters params, CriteriaBuilder criteriaBuilder, Root<Project> projectRoot, List<Predicate> predicates, Expression<Double> expression) {
        return filterProjectQueryAdvanced(params, criteriaBuilder, projectRoot, predicates, expression, null, null, null, null, false);
    }

    public static void filterProjectQueryForIAs(Parameters params, CriteriaBuilder criteriaBuilder, Root<Project> projectRoot,
                                                List<Predicate> predicates, List<Selection<?>> multiSelect,
                                                Join<Project, ProjectAgency> agencyJoin,
                                                Join<Project, Transaction> transactionJoin) {
        filterProjectQueryAdvanced(params, criteriaBuilder, projectRoot, predicates, null, multiSelect, agencyJoin, null, transactionJoin, false);
    }

    public static void filterProjectQueryForSectors(Parameters params, CriteriaBuilder criteriaBuilder, Root<Project> projectRoot,
                                                List<Predicate> predicates, List<Selection<?>> multiSelect,
                                                Join<Project, ProjectSector> sectorJoin,
                                                Join<Project, Transaction> transactionJoin) {
        filterProjectQueryAdvanced(params, criteriaBuilder, projectRoot, predicates, null, multiSelect, null, sectorJoin, transactionJoin, false);
    }

    public static void filterProjectQueryWithUtilization(Parameters params, CriteriaBuilder criteriaBuilder, Root<Project> projectRoot, List<Predicate> predicates, List<Selection<?>> multiSelect, Join<Project, Transaction> transactionJoin) {
        filterProjectQueryAdvanced(params, criteriaBuilder, projectRoot, predicates, null, multiSelect, null, null, transactionJoin, true);
    }

    public static Expression<Double> filterProjectQueryAdvanced(Parameters params, CriteriaBuilder criteriaBuilder,
                                                  Root<Project> projectRoot, List<Predicate> predicates,
                                                  Expression<Double> expression,
                                                  List<Selection<?>> multiSelect,
                                                  Join<Project, ProjectAgency> projectAgencyJoin,
                                                  Join<Project, ProjectSector> sectorJoin,
                                                  Join<Project, Transaction> transactionJoin,
                                                  boolean isUtilizationSelectNeeded) {
        synchronized (LOCK_PROJECT) {
            if (params != null) {
                if (params.getLocations() != null) {
                    Join<Project, ProjectLocation> projectLocationJoin = projectRoot.join(Project_.locations, JoinType.LEFT);
                    Join<ProjectLocation, ProjectLocationId> idJoin = projectLocationJoin.join(ProjectLocation_.pk, JoinType.LEFT);
                    Join<ProjectLocationId, Location> locationJoin = idJoin.join(ProjectLocationId_.location, JoinType.LEFT);

                    predicates.add(locationJoin.get(Location_.id).in(params.getLocations()));
                    if(expression!=null) {
                        expression = criteriaBuilder.prod(projectLocationJoin.get(ProjectLocation_.utilization), expression);
                    }
                }
                if (params.getProjects() != null) {
                    predicates.add(projectRoot.get(Project_.id).in(params.getProjects()));
                }
                if (StringUtils.isNotBlank(params.getProjectTitle())){
                    predicates.add(criteriaBuilder.like(criteriaBuilder.upper(projectRoot.get(Project_.title)), "%" + params.getProjectTitle().toUpperCase() + "%"));
                }
                if (params.getSectors() != null) {
                    if(sectorJoin==null){
                        sectorJoin=projectRoot.join(Project_.sectors);
                    }
                    Join<ProjectSector, ProjectSectorId> pk = sectorJoin.join(ProjectSector_.pk);
                    predicates.add(pk.get(ProjectSectorId_.sector).in(params.getSectors()));
                    if(expression!=null) {
                        expression = criteriaBuilder.prod(sectorJoin.get(ProjectSector_.utilization), expression);
                    }
                }
                if (params.getStatuses() != null) {
                    Join<Project, Status> statusJoin = projectRoot.join(Project_.status);
                    predicates.add(statusJoin.get(Status_.id).in(params.getStatuses()));
                }
                if (params.getPhysicalStatuses() != null){
                    Join<Project, PhysicalStatus> physicalStatusJoin = projectRoot.join(Project_.physicalStatus);
                    predicates.add(physicalStatusJoin.get(PhysicalStatus_.id).in(params.getPhysicalStatuses()));
                }
                if(params.getLocationLevels()!=null) {
                    Join<Project, ProjectLocation> projectLocationJoin = projectRoot.join(Project_.locations, JoinType.LEFT);
                    Join<ProjectLocation, ProjectLocationId> idJoin = projectLocationJoin.join(ProjectLocation_.pk, JoinType.LEFT);
                    Join<ProjectLocationId, Location> locationJoin = idJoin.join(ProjectLocationId_.location, JoinType.LEFT);
                    predicates.add(locationJoin.get(Location_.level).in(params.getLocationLevels()));
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
                if(params.getClassifications() != null){
                    predicates.add(projectRoot.get(Project_.grantClassification).in(params.getClassifications()));
                }
                if (params.getImpAgencies() != null) {
                    if(projectAgencyJoin==null) {
                        projectAgencyJoin = projectRoot.join(Project_.implementingAgencies);
                    }
                    Join<ProjectAgency, ProjectAgencyId> pk = projectAgencyJoin.join(ProjectAgency_.pk);
                    predicates.add(pk.get(ProjectAgencyId_.agency).in(params.getImpAgencies()));
                    if(expression!=null) {
                        expression = criteriaBuilder.prod(projectAgencyJoin.get(ProjectAgency_.utilization), expression);
                    }
                }
                if (params.getFlowTypes() != null || params.getGrantSubTypes() != null) {
                    Predicate ft = null;
                    boolean isFlowType = false;
                    if(transactionJoin==null){
                        transactionJoin = projectRoot.join(Project_.transactions);
                    }
                    if(params.getFlowTypes()!=null){
                        ft = transactionJoin.get(Transaction_.flowType).in(params.getFlowTypes());
                        isFlowType = true;
                    }
                    Predicate gst = null;
                    boolean isGrantType = false;
                    if(params.getGrantSubTypes()!=null) {
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
                    Join<Project, ProjectClimateChange> climateChangeJoin = projectRoot.join(Project_.climateChange);
                    Join<ProjectClimateChange, ProjectClimateChangeId> pk = climateChangeJoin.join(ProjectClimateChange_.pk);
                    predicates.add(pk.get(ProjectClimateChangeId_.climateChange).in(params.getClimateChanges()));
                    if(expression!=null) {
                        expression = criteriaBuilder.prod(climateChangeJoin.get(ProjectClimateChange_.utilization), expression);
                    }
                }
                if (params.getGenderResponsiveness() != null) {
                    Join<Project, ProjectGenderResponsiveness> genderResponsivenessJoin = projectRoot.join(Project_.genderResponsiveness);
                    Join<ProjectGenderResponsiveness, ProjectGenderResponsivenessId> pk = genderResponsivenessJoin.join(ProjectGenderResponsiveness_.pk);
                    predicates.add(pk.get(ProjectGenderResponsivenessId_.gender_responsiveness).in(params.getGenderResponsiveness()));
                    if(expression!=null) {
                        expression = criteriaBuilder.prod(genderResponsivenessJoin.get(ProjectGenderResponsiveness_.utilization), expression);
                    }
                }
                if(params.getFinancialAmountMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectRoot.get(Project_.totalProjectAmount), params.getFinancialAmountMin()));
                }
                if(params.getFinancialAmountMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectRoot.get(Project_.totalProjectAmount), params.getFinancialAmountMax()));
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
                if(params.getPhysicalProgressMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectRoot.get(Project_.physicalProgress), params.getPhysicalProgressMin()));
                }
                if(params.getPhysicalProgressMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectRoot.get(Project_.physicalProgress), params.getPhysicalProgressMax()));
                }
            }
        }
        return expression;
    }

    public static void filterLocationQuery(Parameters params, CriteriaBuilder criteriaBuilder, Root<Location> locationRoot, List<Predicate> predicates, Join<ProjectLocationId, Project> projectJoin) {
        synchronized (LOCK_LOCATION) {
            if (params != null) {
                Join<Project, Transaction> transactionJoin = projectJoin.join(Project_.transactions);
                if(params.getLocationLevels()!=null) {
                    predicates.add(locationRoot.get(Location_.level).in(params.getLocationLevels()));
                }
                if (params.getLocations() != null) {
                    predicates.add(locationRoot.get(Location_.id).in(params.getLocations()));
                }
                if (params.getProjects() != null) {
                    predicates.add(projectJoin.in(params.getProjects()));
                }
                if(params.getClassifications() != null){
                    predicates.add(projectJoin.get(Project_.grantClassification).in(params.getClassifications()));
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
                if (params.getClimateChanges() != null) {
                    Join<Project, ProjectClimateChange> climateChangeJoin = projectJoin.join(Project_.climateChange, JoinType.LEFT);
                    Join<ProjectClimateChange, ProjectClimateChangeId> pk = climateChangeJoin.join(ProjectClimateChange_.pk);
                    predicates.add(pk.get(ProjectClimateChangeId_.climateChange).in(params.getClimateChanges()));
                }
                if (params.getGenderResponsiveness() != null) {
                    Join<Project, ProjectGenderResponsiveness> genderResponsivenessJoin = projectJoin.join(Project_.genderResponsiveness, JoinType.LEFT);
                    Join<ProjectGenderResponsiveness, ProjectGenderResponsivenessId> pk = genderResponsivenessJoin.join(ProjectGenderResponsiveness_.pk);
                    predicates.add(pk.get(ProjectGenderResponsivenessId_.gender_responsiveness).in(params.getGenderResponsiveness()));
                }
                if (params.getFlowTypes() != null || params.getGrantSubTypes() != null) {
                    Predicate ft = null;
                    boolean isFlowType = false;
                    if(params.getFlowTypes()!=null){
                        ft = transactionJoin.get(Transaction_.flowType).in(params.getFlowTypes());
                        isFlowType = true;
                    }
                    Predicate gst = null;
                    boolean isGrantType = false;
                    if(params.getGrantSubTypes()!=null) {
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
                if(params.getFinancialAmountMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectJoin.get(Project_.totalProjectAmount), params.getFinancialAmountMin()));
                }
                if(params.getFinancialAmountMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectJoin.get(Project_.totalProjectAmount), params.getFinancialAmountMax()));
                }
                if(params.getTargetOwpaMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectJoin.get(Project_.targetOwpa), params.getTargetOwpaMin()));
                }
                if(params.getTargetOwpaMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectJoin.get(Project_.targetOwpa), params.getTargetOwpaMax()));
                }
                if(params.getPhysicalProgressMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectJoin.get(Project_.physicalProgress), params.getPhysicalProgressMin()));
                }
                if(params.getPhysicalProgressMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectJoin.get(Project_.physicalProgress), params.getPhysicalProgressMax()));
                }
                if(params.getActualOwpaMin() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectJoin.get(Project_.actualOwpa), params.getActualOwpaMin()));
                }
                if(params.getActualOwpaMax() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(projectJoin.get(Project_.actualOwpa), params.getActualOwpaMax()));
                }
                if(params.getTrxType() != null) {
                    predicates.add(transactionJoin.get(Transaction_.transactionTypeId).in(params.getTrxType()));
                }
                if(params.getTrxStatus() != null) {
                    predicates.add(transactionJoin.get(Transaction_.transactionStatusId).in(params.getTrxStatus()));
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
