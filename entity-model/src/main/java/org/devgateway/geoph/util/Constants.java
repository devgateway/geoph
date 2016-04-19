package org.devgateway.geoph.util;

/**
 * @author dbianco
 *         created on mar 10 2016.
 */
public class Constants {

    public static final String PASS_ENCODE = "valar morghulis";
    public static final String PARAM_SEPARATOR = ",";
    public static final String KEY_SEPARATOR = "-";

    public static final String FILTER_START_DATE = "ds";
    public static final String FILTER_END_DATE = "de";
    public static final String FILTER_FLOW_TYPE = "ft";
    public static final String FILTER_FUNDING_AGENCY = "fa";
    public static final String FILTER_IMPLEMENTING_AGENCY = "ia";
    public static final String FILTER_LOCATION = "lo";
    public static final String FILTER_PERFORMANCE_START = "ps";
    public static final String FILTER_PERFORMANCE_END = "pe";
    public static final String FILTER_PHYSICAL_STATUS = "ph";
    public static final String FILTER_PROJECT = "pr";
    public static final String FILTER_SECTOR = "st";
    public static final String FILTER_STATUS = "sa";
    public static final String FILTER_PROJECT_TITLE = "pt";

    public static final String PROPERTY_LOC_CODE = "code";
    public static final String PROPERTY_LOC_COMMITMENTS = "commitments";
    public static final String PROPERTY_LOC_DISBURSEMENTS = "disbursements";
    public static final String PROPERTY_LOC_EXPENDITURES = "expenditures";
    public static final String PROPERTY_LOC_TARGET = "target";
    public static final String PROPERTY_LOC_ACTUAL = "actual";
    public static final String PROPERTY_LOC_CANCELLED = "cancelled";
    public static final String PROPERTY_LOC_ID = "id";
    public static final String PROPERTY_LOC_LEVEL = "level";
    public static final String PROPERTY_LOC_NAME = "name";
    public static final String PROPERTY_LOC_PROJ_COUNT = "projectCount";
    public static final String PROPERTY_LOC_TRX_COUNT = "transactionCount";
    public static final String PROPERTY_LOC_SECTOR_AGGREGATION = "sectorAggregation";

    public static final String PROPERTY_PRJ_ID = "id";

    public static final String QUERY_HINT = "javax.persistence.fetchgraph";
    public static final String GRAPH_PROJECT_ALL = "graph.project.all";
    public static final String DOUBLE_FORMAT = "%.2f";
}
