package org.devgateway.geoph.services.exporter;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.devgateway.geoph.core.export.ColumnDefinition;
import org.devgateway.geoph.core.export.RawCell;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
public class RawCellImpl<T> implements RawCell {

    private T value;
    private ColumnDefinition<T> columnDefinition;
    private CellStyle cellStyle = null;

    public RawCellImpl(ColumnDefinition<T> columnDefinition, T value) {
        this.columnDefinition = columnDefinition;
        this.value = value;

    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public String getFormattedValue() {
        return columnDefinition.getFormatter().format(value);
    }

    @Override
    public CellStyle getCellStyle(Workbook wb) {
        return columnDefinition.getCellStyle(wb);
    }

    public void setValue(T value) {
        this.value = value;
    }

}
