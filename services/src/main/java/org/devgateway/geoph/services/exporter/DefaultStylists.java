package org.devgateway.geoph.services.exporter;

import org.apache.poi.ss.usermodel.*;
import org.devgateway.geoph.core.export.Stylist;
import org.devgateway.geoph.core.export.Stylists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Sebastian Dimunzio on 6/13/2016.
 */
@Service
public class DefaultStylists implements Stylists{

    public static final String NUMBER_STYLE = "numberStyle";
    public static final String DECIMAL_STYLE = "decimalStyle";
    public static final String BOLD_STYLE = "boldStyle";
    public static final String REGULAR_STYLE = "regularStyle";
    public static final String AMOUNT_STYLE = "amountStyle";
    public static final String DATE_STYLE = "dateStyle";
    private static final String OPEN_SANS = "Open Sans";

    @Value("${export.style.number}")
    String numberFormat;

    @Value("${export.style.date}")
    String dateFormat;

    @Value("${export.style.amount}")
    String amountFormat;

    @Value("${export.style.decimal}")
    String decimalFormat;

    static Map<String, CellStyle> cellStyleMap = new LinkedHashMap(){
        private static final int MAX_ENTRIES = 20;

        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > MAX_ENTRIES;
        }
    };

    public Stylist getDateStylist(String wbName) {
        return new Stylist() {
            @Override
            public CellStyle getStyle(Workbook wb) {
                String entryName = wbName+DATE_STYLE;
                if(cellStyleMap.get(entryName)==null) {
                    CreationHelper createHelper = wb.getCreationHelper();
                    CellStyle dataStyle = wb.createCellStyle();
                    dataStyle.setDataFormat(createHelper.createDataFormat().getFormat(dateFormat));
                    cellStyleMap.put(entryName, dataStyle);
                    return dataStyle;
                } else {
                    return cellStyleMap.get(entryName);
                }
            }
        };
    }

    public Stylist getAmountStylist(String wbName) {
        return new Stylist() {
            @Override
            public CellStyle getStyle(Workbook wb) {
                String entryName = wbName+AMOUNT_STYLE;
                if(cellStyleMap.get(entryName)==null) {
                    CreationHelper createHelper = wb.getCreationHelper();
                    DataFormat format = createHelper.createDataFormat();
                    CellStyle amountStyle = wb.createCellStyle();
                    amountStyle.setDataFormat(format.getFormat(amountFormat));
                    cellStyleMap.put(entryName, amountStyle);
                    return amountStyle;
                } else {
                    return cellStyleMap.get(entryName);
                }
            }
        };
    }

    public Stylist getRegularStylist(String wbName) {
        return new Stylist() {
            @Override
            public CellStyle getStyle(Workbook wb) {
                String entryName = wbName+REGULAR_STYLE;
                if(cellStyleMap.get(entryName)==null) {
                    CellStyle style = wb.createCellStyle();
                    Font font = wb.createFont();
                    font.setFontName(OPEN_SANS);
                    font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
                    style.setFont(font);
                    style.setBorderBottom(CellStyle.BORDER_NONE);
                    style.setBorderTop(CellStyle.BORDER_NONE);
                    style.setBorderRight(CellStyle.BORDER_NONE);
                    style.setBorderLeft(CellStyle.BORDER_NONE);
                    style.setWrapText(true);
                    cellStyleMap.put(entryName, style);
                    return style;
                } else {
                    return cellStyleMap.get(entryName);
                }
            }
        };
    }

    public Stylist getBoldStylist(String wbName) {
        return new Stylist() {
            @Override
            public CellStyle getStyle(Workbook wb) {
                String entryName = wbName+BOLD_STYLE;
                if(cellStyleMap.get(entryName)==null) {
                    CellStyle style = wb.createCellStyle();
                    Font font = wb.createFont();
                    font.setFontName(OPEN_SANS);
                    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
                    style.setFont(font);
                    style.setBorderBottom(CellStyle.BORDER_NONE);
                    style.setBorderTop(CellStyle.BORDER_NONE);
                    style.setBorderRight(CellStyle.BORDER_NONE);
                    style.setBorderLeft(CellStyle.BORDER_NONE);
                    style.setWrapText(true);
                    cellStyleMap.put(entryName, style);
                    return style;
                } else {
                    return cellStyleMap.get(entryName);
                }
            }
        };
    }

    public Stylist getDecimalStylist(String wbName) {
        return new Stylist() {

            @Override
            public CellStyle getStyle(Workbook wb) {
                String entryName = wbName+DECIMAL_STYLE;
                if(cellStyleMap.get(entryName)==null) {
                    CreationHelper createHelper = wb.getCreationHelper();
                    DataFormat format = createHelper.createDataFormat();
                    CellStyle amountStyle = wb.createCellStyle();
                    amountStyle.setDataFormat(format.getFormat(decimalFormat));
                    cellStyleMap.put(entryName, amountStyle);
                    return amountStyle;
                } else {
                    return cellStyleMap.get(entryName);
                }
            }
        };
    }

    public Stylist getNumberStylist(String wbName) {
        return new Stylist() {
            @Override
            public CellStyle getStyle(Workbook wb) {
                String entryName = wbName+NUMBER_STYLE;
                if(cellStyleMap.get(entryName)==null) {
                    CreationHelper createHelper = wb.getCreationHelper();
                    DataFormat format = createHelper.createDataFormat();
                    CellStyle amountStyle = wb.createCellStyle();
                    amountStyle.setDataFormat(format.getFormat(numberFormat));
                    cellStyleMap.put(entryName, amountStyle);
                    return amountStyle;
                } else {
                    return cellStyleMap.get(entryName);
                }
            }
        };
    }
}
