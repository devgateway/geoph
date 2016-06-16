package org.devgateway.geoph.services.exporter.generators;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.devgateway.geoph.core.export.ColumnDefinition;
import org.devgateway.geoph.core.export.Generator;
import org.devgateway.geoph.core.export.RawRow;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.devgateway.geoph.core.constants.Constants.*;

/**
 * Created by Sebastian Dimunzio on 6/10/2016.
 */
public class CSVGenerator implements Generator {

    List<String> csvHeaderIterable;

    List<List<String>> csvDataIterable;

    public CSVGenerator() {
        csvHeaderIterable = new ArrayList<>();
        csvDataIterable = new ArrayList<>();
    }

    @Override
    public void writeHeaders(List<ColumnDefinition> columnDefinitions) {
        columnDefinitions.stream().forEach(def -> csvHeaderIterable.add(def.getTitle()));
    }

    @Override
    public void writeRow(RawRow rawRow) {
        List<String> dataList = new ArrayList<>();
        rawRow.getCells().stream().forEach(cell -> dataList.add(cell.getFormattedValue()));
        csvDataIterable.add(dataList);
    }

    @Override
    public File toFile(File file) throws Exception {
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        CSVPrinter csvPrinter = new CSVPrinter(out, CSVFormat.DEFAULT
                .withRecordSeparator(System.getProperty(CSV_LINE_SEPARATOR))
                .withDelimiter(CSV_RECORD_SEPARATOR)
                .withQuote(CSV_DOUBLE_QUOTE_CHAR));
        csvPrinter.printRecord(csvHeaderIterable);
        csvPrinter.printRecords(csvDataIterable);
        csvPrinter.flush();
        csvPrinter.close();
        return file;
    }

    @Override
    public String toOutputStream() throws Exception {
        return null;
    }

    @Override
    public String getFileName() {
        return UUID.randomUUID() + ".csv";
    }
}
