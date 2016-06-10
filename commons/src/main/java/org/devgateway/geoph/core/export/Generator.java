package org.devgateway.geoph.core.export;

import java.io.File;
import java.util.List;

/**
 * Created by Sebastian Dimunzio on 6/10/2016.
 */
public interface Generator {

    void writeHeaders(List<ColumnDefinition> columnDefinitions);

    void writeRow(RawRow rawRow);

    File toFile(File file) throws Exception;

    String toOutputStream() throws Exception;

}
