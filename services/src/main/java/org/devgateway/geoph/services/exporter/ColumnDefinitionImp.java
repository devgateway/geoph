package org.devgateway.geoph.services.exporter;

import org.devgateway.geoph.core.export.ColumnDefinition;
import org.devgateway.geoph.core.export.Extractor;
import org.devgateway.geoph.core.export.Formatter;
import org.devgateway.geoph.core.export.RawCell;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
public class ColumnDefinitionImp<T> implements ColumnDefinition {

    String title;
    String getter;
    Formatter<T> formatter;
    Extractor<T> extractor;


    public ColumnDefinitionImp(String title, Formatter<T> formatter, Extractor<T> extractor) {
        this.title = title;
        this.formatter = formatter;
        this.extractor = extractor;
    }

    public RawCell getCell(Object value) {
        return new RawCellImpl(this, value);
    }

    public Extractor<T> getExtractor() {
        return extractor;
    }

    public void setExtractor(Extractor extractor) {
        this.extractor = extractor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public Formatter<T> getFormatter() {
        return formatter;
    }


    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }
}
