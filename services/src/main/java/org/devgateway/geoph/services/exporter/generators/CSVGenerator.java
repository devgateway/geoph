package org.devgateway.geoph.services.exporter.generators;

import org.devgateway.geoph.core.export.ColumnDefinition;
import org.devgateway.geoph.core.export.Generator;
import org.devgateway.geoph.core.export.RawRow;

import java.io.File;
import java.util.List;

/**
 * Created by Sebastian Dimunzio on 6/10/2016.
 */
public class CSVGenerator implements Generator {
    public CSVGenerator() {

        // CSVWriter writer = new CSVWriter(new FileWriter("yourfile.csv"), '\t');

    }

    @Override
    public void writeHeaders(List<ColumnDefinition> columnDefinitions) {

    }

    @Override
    public void writeRow(RawRow rawRow) {

    }

    @Override
    public File toFile(File file) throws Exception {
        return null;
    }

    @Override
    public String toOutputStream() throws Exception {
        return null;
    }
}
