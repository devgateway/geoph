package org.devgateway.geoph.core.constants;

import org.devgateway.geoph.model.IndicatorDetail;
import org.devgateway.geoph.model.Location;
import org.devgateway.geoph.model.Project;
import org.springframework.data.jpa.domain.AbstractPersistable;

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
    public static final String PROPERTY_LOC_TRANSACTIONS = "expenditures";

    public static final String PROPERTY_LOC_TARGET = "target";
    public static final String PROPERTY_LOC_ACTUAL = "actual";
    public static final String PROPERTY_LOC_CANCELLED = "cancelled";
    public static final String PROPERTY_LOC_ID = "id";
    public static final String PROPERTY_LOC_LEVEL = "level";
    public static final String PROPERTY_LOC_NAME = "name";
    public static final String PROPERTY_LOC_PROJ_COUNT = "projectCount";
    public static final String PROPERTY_LOC_TRX_COUNT = "transactionCount";
    public static final String PROPERTY_LOC_PHYSICAL_PROGRESS = "physicalProgress";
    public static final String PROPERTY_LAYER_GID = "gid";
    public static final String PROPERTY_LAYER_KML_ID = "kmlId";
    public static final String PROPERTY_LAYER_NAME = "name";
    public static final String PROPERTY_LAYER_SYMBOL_ID = "symbolId";
    public static final String PROPERTY_LAYER_TYPE = "type";
    public static final String PROPERTY_LAYER_DESCRIPTION = "description";
    public static final String PROPERTY_LAYER_IMAGE_PATH = "imagePath";
    public static final String PROPERTY_INDICATOR_VALUE = "value";
    public static final String PROPERTY_INDICATOR_ID = "indicatorId";
    public static final String PROPERTY_INDICATOR_COLOR_SCHEME = "colorScheme";
    public static final String PROPERTY_INDICATOR_LOC_ID = "locationId";
    public static final String PROPERTY_INDICATOR_NAME = "name";

    public static final String PROPERTY_PRJ_ID = "id";
    public static final String PROPERTY_PRJ_PH_ID = "phId";

    public static final String QUERY_HINT = "javax.persistence.fetchgraph";
    public static final String GRAPH_PROJECT_ALL = "graph.project.all";

    public static final String FLOW_TYPE_ID_SEPARATOR = ".";

    public static final String INDICATOR_FILENAME = "NEDA_indicator_";
    public static final String EXPORT_DATA_FILENAME = "NEDA_data_";

    public static final String CSV_LINE_SEPARATOR = "line.separator";
    public static final char CSV_RECORD_SEPARATOR =  '|';
    public static final Character CSV_DOUBLE_QUOTE_CHAR = Character.valueOf('"');

    public static final String COMMA = ",";
    public static final String GEOPH_EXPORT_SHEET_NAME = "Geoph export";
    public static final String DATE_FORMAT_MMDDYYYY = "MM/dd/yyyy";
    public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_DDMMYYYY = "dd/MM/yyyy";

    public static final String LOCATION_CLASSNAME = Location.class.getSimpleName().toLowerCase();
    public static final String PROJECT_CLASSNAME = Project.class.getSimpleName().toLowerCase();
    public static final String ABSTRACT_PERSISTABLE_CLASSNAME = AbstractPersistable.class.getSimpleName().toLowerCase();
    public static final String INDICATOR_DETAIL_CLASSNAME = IndicatorDetail.class.getSimpleName().toLowerCase();
}
