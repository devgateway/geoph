package org.devgateway.geoph.services.exporter;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.devgateway.geoph.core.export.*;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
public class ColumnDefinitionImp<T> implements ColumnDefinition {

    String title;
    String getter;

    Formatter<T> formatter;

    Extractor<T> extractor;

    Stylist stylist;

    public ColumnDefinitionImp(String title, Formatter<T> formatter, Extractor<T> extractor) {
        this.title = title;
        this.formatter = formatter;
        this.extractor = extractor;
    }

    public ColumnDefinitionImp(String title, Stylist stylist, Extractor<T> extractor) {
        this.title = title;
        this.stylist = stylist;
        this.extractor = extractor;

    }

    public ColumnDefinitionImp(String title, Stylist stylist, Formatter<T> formatter, Extractor<T> extractor) {
        this.title = title;
        this.stylist = stylist;
        this.extractor = extractor;
        this.formatter = formatter;
    }

    private CellStyle cellStyle = null;

    public CellStyle getCellStyle(Workbook wb) {
        if (cellStyle == null) {
            cellStyle = this.stylist.getStyle(wb);
        }
        return cellStyle;
    }


    @Override
    public RawCell getCell(Object value) {
        return new RawCellImpl<T>(this, (T) value);
    }

    @Override
    public Extractor getExtractor() {
        return extractor;
    }


    @Override
    public String getTitle() {
        return title;
    }


    @Override
    public String getGetter() {
        return getter;
    }


    @Override
    public Formatter getFormatter() {
        return formatter;
    }


}
