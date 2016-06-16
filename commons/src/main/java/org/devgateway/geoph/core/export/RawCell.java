package org.devgateway.geoph.core.export;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Created by Sebastian Dimunzio on 6/10/2016.
 */
public interface RawCell<T> {

    T getValue();

    String getFormattedValue();

    CellStyle getCellStyle(Workbook wb);

}
