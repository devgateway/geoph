package org.devgateway.geoph.util;

/**
 * @author dbianco
 *         created on mar 10 2016.
 */
public class Constants {

    public static final String PASS_ENCODE = "valar morghulis";
    public static final String PARAM_SEPARATOR = ",";

    public static final String FILTER_CLIMATE_CHANGE = "cc";
    public static final String FILTER_START_DATE_MAX = "dt_start_max";
    public static final String FILTER_START_DATE_MIN = "dt_start_min";
    public static final String FILTER_END_DATE_MAX = "dt_end_max";
    public static final String FILTER_END_DATE_MIN = "dt_end_min";
    public static final String FILTER_FLOW_TYPE = "ft";
    public static final String FILTER_FUNDING_AGENCY = "fa";
    public static final String FILTER_GENDER_RESPONSIVENESS = "gr";
    public static final String FILTER_FINANCIAL_AMOUNT_MAX = "fin_amount_max";
    public static final String FILTER_FINANCIAL_AMOUNT_MIN = "fin_amount_min";
    public static final String FILTER_IMPLEMENTING_AGENCY = "ia";
    public static final String FILTER_LOCATION = "lo";
    public static final String FILTER_PERFORMANCE_START_MAX = "pp_start_max";
    public static final String FILTER_PERFORMANCE_START_MIN = "pp_start_min";
    public static final String FILTER_PERFORMANCE_END_MAX = "pp_end_max";
    public static final String FILTER_PERFORMANCE_END_MIN = "pp_end_min";
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

    public static final String PROPERTY_PRJ_ID = "id";

    public static final String QUERY_HINT = "javax.persistence.fetchgraph";
    public static final String GRAPH_PROJECT_ALL = "graph.project.all";
    public static final String DOUBLE_FORMAT = "%.2f";

    public static final String ALPHABET = "BCDFGHIJKLMNPQRSTVWXZ";
    public static final int ALPHABET_NUMBER = ALPHABET.length();

    public static final String FLOW_TYPE_ID_SEPARATOR = ".";

    public static final String[] EXPORT_ENGLISH_TITLE_ARRAY = {"Location ID", "UACS Code", "ADM Level", "Name",
            "Latitude", "Longitude", "Region", "Province", "Project ID", "Title", "Implementing Agency",
            "Executing Agency", "Funding Institution", "Original Currency (OC)", "Amount in OC", "Start Date",
            "Closing Date", "Revised Closing Date", "Sectors", "Period of Performance Start",
            "Period of Performance End", "Status", "Physical Status", "Physical Progress (Actual)",
            "Physical Progress (Target)", "Grant Classification", "Total Disbursements", "Total Commitments"};

    public static final String[] INDICATORS_ENGLISH_TITLE_ARRAY = {"Location Name", "UACS Code", "Indicator Value"};

    public static final String SLASH = "/";
}
