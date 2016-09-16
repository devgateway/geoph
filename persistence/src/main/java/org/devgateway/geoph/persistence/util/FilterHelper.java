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

    private static final Object LOCK = new Object() {};

    public static Expression<Double> filterProjectQuery(Parameters params, CriteriaBuilder criteriaBuilder, Root<Project> projectRoot,
                                                        List<Predicate> predicates, Expression<Double> expression, Join<Project, Transaction> transactionJoin) {
        synchronized (LOCK) {
            if (params != null) {
                if(transactionJoin==null) {
                    transactionJoin = projectRoot.join(Project_.transactions, JoinType.LEFT);
                }
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

                if(params.getLocationLevels()!=null) {
                    Join<Project, ProjectLocation> projectLocationJoin = projectRoot.join(Project_.locations, JoinType.LEFT);
                    Join<ProjectLocation, ProjectLocationId> idJoin = projectLocationJoin.join(ProjectLocation_.pk, JoinType.LEFT);
                    Join<ProjectLocationId, Location> locationJoin = idJoin.join(ProjectLocationId_.location, JoinType.LEFT);
                    predicates.add(locationJoin.get(Location_.level).in(params.getLocationLevels()));
                }

                if(params.getTrxType() != null || params.getTrxStatus() != null) {
                    addTrxTypeStatusFilter(params, predicates, transactionJoin);
                }

                expression = addCommonFilters(params, criteriaBuilder, predicates, projectRoot, expression);

            }
        }
        return expression;
    }

    public static Expression<Double>  filterLocationQuery(Parameters params, CriteriaBuilder criteriaBuilder, Root<Location> locationRoot,
                                                          List<Predicate> predicates, Join<ProjectLocationId, Project> projectJoin,
                                                          Expression<Double> expression,
                                                          Join<Location, ProjectLocation> projectLocationJoin,
                                                          Join<Project, Transaction> transactionJoin) {
        synchronized (LOCK) {
            if (params != null) {
                if(transactionJoin==null) {
                    transactionJoin = projectJoin.join(Project_.transactions, JoinType.LEFT);
                }
                if(params.getLocationLevels()!=null) {
                    predicates.add(locationRoot.get(Location_.level).in(params.getLocationLevels()));
                }
                if (params.getLocations() != null) {
                    predicates.add(locationRoot.get(Location_.id).in(params.getLocations()));
                    if(expression!=null) {
                        expression = criteriaBuilder.prod(projectLocationJoin.get(ProjectLocation_.utilization), expression);
                    }
                }
                if (params.getProjects() != null) {
                    predicates.add(projectJoin.in(params.getProjects()));
                }
                addTrxTypeStatusFilter(params, predicates, transactionJoin);

                expression = addCommonFilters(params, criteriaBuilder, predicates, projectJoin, expression);

            }
            return expression;
        }
    }

    private static Expression<Double> addCommonFilters(Parameters params, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, From projectFrom, Expression<Double> expression) {
        addProjectTitleFilter(params, criteriaBuilder, predicates, projectFrom);
        addDateFilters(params, criteriaBuilder, predicates, projectFrom);
        addClassificationFilter(params, criteriaBuilder, predicates, projectFrom);
        addPeriodPerformanceFilters(params, criteriaBuilder, predicates, projectFrom);
        addFinancialAmountFilters(params, criteriaBuilder, predicates, projectFrom);
        addPhysicalProgressFilters(params, criteriaBuilder, predicates, projectFrom);
        addFlowTypeFilters(params, criteriaBuilder, predicates, projectFrom);

        addFundingAgencyFilter(params, predicates, projectFrom);
        addPhysicalStatusFilter(params, predicates, projectFrom);
        addStatusFilter(params, predicates, projectFrom);

        expression = addSectorsFilter(params, criteriaBuilder, predicates, projectFrom, expression);
        expression = addImpAgenciesFilter(params, criteriaBuilder, predicates, projectFrom, expression);
        expression = addClimateChangesFilter(params, criteriaBuilder, predicates, projectFrom, expression);
        expression = addGenderResponsivenessFilter(params, criteriaBuilder, predicates, projectFrom, expression);
        return expression;
    }

    private static void addProjectTitleFilter(Parameters params, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, From projectFrom) {
        if (StringUtils.isNotBlank(params.getProjectTitle())) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(projectFrom.get(Project_.title)), "%" + params.getProjectTitle().toUpperCase() + "%"));
        }
    }


    private static void addClassificationFilter(Parameters params, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, From projectFrom) {
        if(params.getClassifications() != null){
            predicates.add(projectFrom.get(Project_.grantClassification).in(params.getClassifications()));
        }
    }

    private static Expression<Double> addSectorsFilter(Parameters params, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, From projectFrom, Expression<Double> expression) {
        if (params.getSectors() != null) {
            Join<Project, ProjectSector> sectorJoin = projectFrom.join(Project_.sectors, JoinType.LEFT);
            Join<ProjectSector, ProjectSectorId> pk = sectorJoin.join(ProjectSector_.pk);
            predicates.add(pk.get(ProjectSectorId_.sector).in(params.getSectors()));
            if(expression!=null) {
                expression = criteriaBuilder.prod(sectorJoin.get(ProjectSector_.utilization), expression);
            }
        }
        return expression;
    }

    private static Expression<Double> addGenderResponsivenessFilter(Parameters params, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, From projectFrom, Expression<Double> expression) {
        if (params.getGenderResponsiveness() != null) {
            Join<Project, ProjectGenderResponsiveness> genderResponsivenessJoin = projectFrom.join(Project_.genderResponsiveness, JoinType.LEFT);
            Join<ProjectGenderResponsiveness, ProjectGenderResponsivenessId> pk = genderResponsivenessJoin.join(ProjectGenderResponsiveness_.pk);
            predicates.add(pk.get(ProjectGenderResponsivenessId_.gender_responsiveness).in(params.getGenderResponsiveness()));
            if(expression!=null) {
                expression = criteriaBuilder.prod(genderResponsivenessJoin.get(ProjectGenderResponsiveness_.utilization), expression);
            }
        }
        return expression;
    }

    private static Expression<Double> addClimateChangesFilter(Parameters params, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, From projectFrom, Expression<Double> expression) {
        if (params.getClimateChanges() != null) {
            Join<Project, ProjectClimateChange> climateChangeJoin = projectFrom.join(Project_.climateChange, JoinType.LEFT);
            Join<ProjectClimateChange, ProjectClimateChangeId> pk = climateChangeJoin.join(ProjectClimateChange_.pk);
            predicates.add(pk.get(ProjectClimateChangeId_.climateChange).in(params.getClimateChanges()));
            if(expression!=null) {
                expression = criteriaBuilder.prod(climateChangeJoin.get(ProjectClimateChange_.utilization), expression);
            }
        }
        return expression;
    }

    private static Expression<Double> addImpAgenciesFilter(Parameters params, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, From projectFrom, Expression<Double> expression) {
        if (params.getImpAgencies() != null) {
            Join<Project, ProjectAgency> impAgencyJoin = projectFrom.join(Project_.implementingAgencies, JoinType.LEFT);
            Join<ProjectAgency, ProjectAgencyId> pk = impAgencyJoin.join(ProjectAgency_.pk);
            predicates.add(pk.get(ProjectAgencyId_.agency).in(params.getImpAgencies()));
            if(expression!=null) {
                expression = criteriaBuilder.prod(impAgencyJoin.get(ProjectAgency_.utilization), expression);
            }
        }
        return expression;
    }

    private static void addPhysicalStatusFilter(Parameters params, List<Predicate> predicates, From projectFrom) {
        if (params.getPhysicalStatuses() != null) {
            Join<Project, PhysicalStatus> physicalStatusJoin = projectFrom.join(Project_.physicalStatus);
            predicates.add(physicalStatusJoin.get(PhysicalStatus_.id).in(params.getPhysicalStatuses()));
        }
    }

    private static void addStatusFilter(Parameters params, List<Predicate> predicates, From projectFrom) {
        if (params.getStatuses() != null) {
            Join<Project, Status> statusJoin = projectFrom.join(Project_.status);
            predicates.add(statusJoin.get(Status_.id).in(params.getStatuses()));
        }
    }


    private static void addFundingAgencyFilter(Parameters params, List<Predicate> predicates, From projectFrom) {
        if (params.getFundingAgencies() != null) {
            Join<Project, Agency> fundingAgencyJoin = projectFrom.join(Project_.fundingAgency, JoinType.LEFT);
            predicates.add(fundingAgencyJoin.get(FundingAgency_.id).in(params.getFundingAgencies()));
        }
    }

    private static void addPeriodPerformanceFilters(Parameters params, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, From projectFrom) {
        if (params.getPeriodPerformanceStartMin() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectFrom.get(Project_.periodPerformanceStart), params.getPeriodPerformanceStartMin()));
        }
        if (params.getPeriodPerformanceStartMax() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(projectFrom.get(Project_.periodPerformanceStart), params.getPeriodPerformanceStartMax()));
        }
        if (params.getPeriodPerformanceEndMin() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectFrom.get(Project_.periodPerformanceEnd), params.getPeriodPerformanceEndMin()));
        }
        if (params.getPeriodPerformanceEndMax() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(projectFrom.get(Project_.periodPerformanceEnd), params.getPeriodPerformanceEndMax()));
        }
    }


    private static void addPhysicalProgressFilters(Parameters params, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, From projectFrom) {
        if(params.getTargetOwpaMin() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectFrom.get(Project_.targetOwpa), params.getTargetOwpaMin()));
        }
        if(params.getTargetOwpaMax() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(projectFrom.get(Project_.targetOwpa), params.getTargetOwpaMax()));
        }
        if(params.getPhysicalProgressMin() != null && params.getPhysicalProgressMax() != null
                && params.getPhysicalProgressMin().equals(0D) && params.getPhysicalProgressMax().equals(0D)){
            predicates.add(criteriaBuilder.or(criteriaBuilder.equal(projectFrom.get(Project_.physicalProgress), 0), criteriaBuilder.isNull(projectFrom.get(Project_.physicalProgress))));
        } else {
            if (params.getPhysicalProgressMin() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectFrom.get(Project_.physicalProgress), params.getPhysicalProgressMin()));
            }
            if (params.getPhysicalProgressMax() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(projectFrom.get(Project_.physicalProgress), params.getPhysicalProgressMax()));
            }
        }
        if(params.getActualOwpaMin() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectFrom.get(Project_.actualOwpa), params.getActualOwpaMin()));
        }
        if(params.getActualOwpaMax() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(projectFrom.get(Project_.actualOwpa), params.getActualOwpaMax()));
        }
    }

    private static void addDateFilters(Parameters params, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, From projectFrom) {
        if (params.getStartDateMin() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectFrom.get(Project_.startDate), params.getStartDateMin()));
        }
        if (params.getStartDateMax() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(projectFrom.get(Project_.startDate), params.getStartDateMax()));
        }
        if (params.getEndDateMin() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectFrom.get(Project_.endDate), params.getEndDateMin()));
        }
        if (params.getEndDateMax() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(projectFrom.get(Project_.endDate), params.getEndDateMax()));
        }
    }

    private static void addFinancialAmountFilters(Parameters params, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, From projectFrom) {
        if(params.getFinancialAmountMin() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(projectFrom.get(Project_.totalProjectAmount), params.getFinancialAmountMin()));
        }
        if(params.getFinancialAmountMax() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(projectFrom.get(Project_.totalProjectAmount), params.getFinancialAmountMax()));
        }
    }

    private static void addFlowTypeFilters(Parameters params, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, From projectFrom) {
        if (params.getFlowTypes() != null || params.getGrantSubTypes() != null) {
            Predicate ft = null;
            boolean isFlowType = false;
            Join<Project, Transaction> transactionJoin = projectFrom.join(Project_.transactions);

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
    }


    private static void addTrxTypeStatusFilter(Parameters params, List<Predicate> predicates, Join<Project, Transaction> transactionJoin) {
        if (params.getTrxType() != null) {
            predicates.add(transactionJoin.get(Transaction_.transactionTypeId).in(params.getTrxType()));
        }
        if (params.getTrxStatus() != null) {
            predicates.add(transactionJoin.get(Transaction_.transactionStatusId).in(params.getTrxStatus()));
        }
    }
}
