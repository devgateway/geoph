package org.devgateway.geoph.services.exporter;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
public class Cell <T> {

    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    private String format(Formatter<T> formatter){
        return formatter.format(value);
    }

    public Cell(T value) {
        this.value = value;
    }
}
