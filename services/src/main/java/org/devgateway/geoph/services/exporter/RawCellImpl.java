package org.devgateway.geoph.services.exporter;

import org.devgateway.geoph.core.export.Formatter;
import org.devgateway.geoph.core.export.RawCell;
import org.devgateway.geoph.core.export.Stylist;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
public class RawCellImpl<T> implements RawCell {

    private T value;
    private ColumnDefinitionImp<T> tColumnDefinition;

    public T getValue() {
        return value;
    }

    @Override
    public Stylist getStylist() {
        return this.tColumnDefinition.getStylist();
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
