package org.devgateway.geoph.services.exporter;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
public class RawCell<T> {

    private T value;

    private ColumnDefinition<T> tColumnDefinition;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    private String format(Formatter<T> formatter) {
        return formatter.format(value);
    }

    public RawCell(ColumnDefinition<T> tColumnDefinition, T value) {
        this.tColumnDefinition = tColumnDefinition;
        this.value = value;
    }
}
