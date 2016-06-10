package org.devgateway.geoph.core.export;

/**
 * Created by Sebastian Dimunzio on 6/10/2016.
 */
public interface ColumnDefinition<T> {

    RawCell<T> getCell(T value);

    Extractor<T> getExtractor();

    void setExtractor(Extractor<T> extractor);

    String getTitle();

    void setTitle(String title);

    String getGetter();

    void setGetter(String getter);

    Formatter<T> getFormatter();

    void setFormatter(Formatter<T> formatter);
}
