package org.devgateway.geoph.services.exporter;

import java.util.Map;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
public class ColumnDefinition<T> {

    String title;
    String getter;
    Formatter<T> formatter;



    public ColumnDefinition(String title, String getter, Formatter<T> formatter) {
        this.title = title;
        this.getter = getter;
        this.formatter = formatter;
    }

    public T getValue(Map<String,Object> source){
        return (T) source.get(this.getter);
    }

    public Cell<T> getCell(T value){
        return new Cell<T>(value);
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

    public void setFormatter(Formatter<T> formatter) {
        this.formatter = formatter;
    }
}
