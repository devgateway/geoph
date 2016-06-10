package org.devgateway.geoph.services.exporter.generators;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.devgateway.geoph.core.export.ColumnDefinition;
import org.devgateway.geoph.core.export.Generator;
import org.devgateway.geoph.core.export.RawCell;
import org.devgateway.geoph.core.export.RawRow;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by Sebastian Dimunzio on 6/9/2016.
 */

public class XLSGenerator implements Generator {
    private static int START_ROW = 0;
    private static int START_COLUMN = 0;

    @Value("${export.xls.title}")
    private String title;
    private Workbook wb;
    private Sheet sheet;
    private int rowNumber = 0;



    public XLSGenerator() {
        wb = new HSSFWorkbook();
        sheet = wb.createSheet("TEST");
        rowNumber = START_ROW;
    }

    private CellStyle getHeaderStyle() {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(font);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setWrapText(true);
        return style;
    }

    public CellStyle getDateStyle() {
        CreationHelper createHelper = wb.getCreationHelper();
        CellStyle dataStyle = wb.createCellStyle();
        dataStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        return dataStyle;
    }

    public void writeHeaders(List<ColumnDefinition> columnDefinitions) {
        Row row = sheet.createRow(rowNumber++);
        int position = START_COLUMN;
        for (ColumnDefinition def : columnDefinitions) {
            Cell cell = row.createCell(position++);
            cell.setCellValue(def.getTitle());
            cell.setCellStyle(getHeaderStyle());
        }
    }


    public void writeRow(RawRow rawRow) {
        Row row = sheet.createRow(rowNumber++);
        int colNumber = START_COLUMN;
        for (RawCell rawcell : rawRow.getCells()) {
            createCell(row, colNumber++, rawcell.getValue());
        }
    }

    private Cell createCell(Row row, int position, Object value) {
        Cell cell = row.createCell(position);
        if (value instanceof String) {
            cell.setCellValue((String) value);
        }
        if (value instanceof Date) {
            cell.setCellValue((Date) value);
        }
        if (value instanceof Double) {
            cell.setCellValue((Double) value);
        }
        if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }
        return cell;
    }


    public File toFile(File file) throws Exception {
        FileOutputStream fileOut = new FileOutputStream(file);
        wb.write(fileOut);
        fileOut.close();
        return file;
    }

    @Override
    public String toOutputStream() throws Exception {
        return null;
    }
}
