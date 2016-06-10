package org.devgateway.geoph.services.exporter;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
public class ColumnDefinition<T> {

    String title;
    String getter;
    Formatter<T> formatter;
    Extractor<T> extractor;


    public ColumnDefinition(String title, Formatter<T> formatter, Extractor<T> extractor) {
        this.title = title;
        this.formatter = formatter;
        this.extractor = extractor;
    }


    public RawCell<T> getCell(T value) {
        return new RawCell<T>(this, value);
    }

    public Extractor<T> getExtractor() {
        return extractor;
    }

    public void setExtractor(Extractor<T> extractor) {
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

    public void setFormatter(Formatter<T> formatter) {
        this.formatter = formatter;
    }
}
