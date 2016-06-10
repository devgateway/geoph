package org.devgateway.geoph.core.constants;

/**
 * @author dbianco
 *         created on mar 10 2016.
 */
public class Constants {

    public static final String PASS_ENCODE = "valar morghulis";
    public static final String PARAM_SEPARATOR = ",";

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
    public static final String PROPERTY_LOC_ACTUAL_PHY_AVG = "actualPhysicalProgressAverage";
    public static final String PROPERTY_LOC_TARGET_PHY_AVG = "targetPhysicalProgressAverage";

    public static final String PROPERTY_PRJ_ID = "id";

    public static final String QUERY_HINT = "javax.persistence.fetchgraph";
    public static final String GRAPH_PROJECT_ALL = "graph.project.all";

    public static final String FLOW_TYPE_ID_SEPARATOR = ".";

    public static final String INDICATOR_FILENAME = "NEDA_indicator_";
    public static final String EXPORT_DATA_FILENAME = "NEDA_data_";

    public static final String[] EXPORT_ENGLISH_TITLE_ARRAY = {"Location ID", "UACS Code", "ADM Level", "Name",
            "Latitude", "Longitude", "Region", "Province", "Project ID", "Title", "Implementing Agency",
            "Executing Agency", "Funding Institution", "Original Currency (OC)", "Amount in OC", "Start Date",
            "Closing Date", "Revised Closing Date", "Sectors", "Period of Performance Start",
            "Period of Performance End", "Status", "Physical Status", "Physical Progress (Actual)",
            "Physical Progress (Target)", "Grant Classification", "Total Disbursements", "Total Commitments"};

    public static final String[] INDICATORS_ENGLISH_TITLE_ARRAY = {"Location Name", "UACS Code", "Indicator Value"};

    public static final String LINE_SEPARATOR = "line.separator";
    public static final String COMMA = ",";
    public static final String GEOPH_EXPORT_SHEET_NAME = "Geoph export";
    public static final String PHILIPPINES_LANGUAJE = "ph";
    public static final String DATE_FORMAT_MDYY_HMM = "m/d/yy h:mm";
    public static final String DATE_FORMAT_MMDDYYYY = "MM/dd/yyyy";
    public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_DDMMYYYY = "dd/MM/yyyy";
}
