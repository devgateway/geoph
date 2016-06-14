package org.devgateway.geoph.services.exporter;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.devgateway.geoph.core.export.Formatter;
import org.devgateway.geoph.core.export.RawCell;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
public class RawCellImpl<T> implements RawCell {

    private T value;
    private ColumnDefinitionImp<T> tColumnDefinition;
    private CellStyle cellStyle = null;
    public T getValue() {
        return value;
    }

    @Override
    public CellStyle getCellStyle(Workbook wb) {
        return tColumnDefinition.getCellStyle(wb);
    }

    public void setValue(T value) {
        this.value = value;
    }

    private String format(Formatter<T> formatter) {
        return formatter.format(value);
    }


    public RawCellImpl(ColumnDefinitionImp<T> tColumnDefinition, T value) {
        this.tColumnDefinition = tColumnDefinition;
        this.value = value;

    }
}
