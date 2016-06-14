package org.devgateway.geoph.core.export;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Created by Sebastian Dimunzio on 6/13/2016.
 */
public interface Stylist {


    CellStyle getStyle(Workbook wb);
}
