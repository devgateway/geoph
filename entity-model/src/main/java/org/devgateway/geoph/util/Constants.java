package org.devgateway.geoph.util;

/**
 * @author dbianco
 *         created on mar 10 2016.
 */
public class Constants {

    public static final String PASS_ENCODE = "valar morghulis";
    public static final String PARAM_SEPARATOR = ",";
    public static final String KEY_SEPARATOR = "-";

    public static final String FILTER_DATE_START = "ds";
    public static final String FILTER_DATE_END = "de";
    public static final String FILTER_FLOW_TYPE = "ft";
    public static final String FILTER_FUNDING_AGENCY = "fa";
    public static final String FILTER_IMPLEMENTING_AGENCY = "ia";
    public static final String FILTER_LOCATION = "lo";
    public static final String FILTER_PROJECT = "pr";
    public static final String FILTER_SECTOR = "st";
    public static final String FILTER_STATUS = "sa";

    public static final String PROPERTY_LOC_CODE = "code";
    public static final String PROPERTY_LOC_GRANTS = "grants";
    public static final String PROPERTY_LOC_ID = "id";
    public static final String PROPERTY_LOC_LEVEL = "level";
    public static final String PROPERTY_LOC_LOANS = "loans";
    public static final String PROPERTY_LOC_PMC = "pmc";
    public static final String PROPERTY_LOC_NAME = "name";
    public static final String PROPERTY_LOC_PROJ_COUNT = "projectCount";
    public static final String PROPERTY_LOC_TRX_COUNT = "transactionCount";
    public static final String PROPERTY_LOC_SECTOR_AGGREGATION = "sectorAggregation";

    public static final String PROPERTY_PRJ_ID = "id";

    public static final String QUERY_HINT = "javax.persistence.fetchgraph";
    public static final String GRAPH_PROJECT_ALL = "graph.project.all";
    public static final String DOUBLE_FORMAT = "%.2f";
}
