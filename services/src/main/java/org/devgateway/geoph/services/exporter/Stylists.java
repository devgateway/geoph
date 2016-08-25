package org.devgateway.geoph.services.exporter;

import org.apache.poi.ss.usermodel.*;
import org.devgateway.geoph.core.export.Stylist;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sebastian Dimunzio on 6/13/2016.
 */
@Service
public class Stylists {

    private static final String NUMBER_STYLE = "numberStyle";
    private static final String DECIMAL_STYLE = "decimalStyle";
    private static final String BOLD_STYLE = "boldStyle";
    private static final String REGULAR_STYLE = "regularStyle";
    private static final String AMOUNT_STYLE = "amountStyle";
    private static final String DATE_STYLE = "dateStyle";
    private static final String OPEN_SANS = "Open Sans";

    @Value("${export.style.number}")
    String numberFormat;

    @Value("${export.style.date}")
    String dateFormat;

    @Value("${export.style.amount}")
    String amountFormat;

    @Value("${export.style.decimal}")
    String decimalFormat;

    static Map<String, CellStyle> cellStyleMap = new HashMap<>();

    public Stylist getDateStylist() {
        return new Stylist() {
            @Override
            public CellStyle getStyle(Workbook wb) {
                if(cellStyleMap.get(DATE_STYLE)==null) {
                    CreationHelper createHelper = wb.getCreationHelper();
                    CellStyle dataStyle = wb.createCellStyle();
                    dataStyle.setDataFormat(createHelper.createDataFormat().getFormat(dateFormat));
                    cellStyleMap.put(DATE_STYLE, dataStyle);
                    return dataStyle;
                } else {
                    return cellStyleMap.get(DATE_STYLE);
                }
            }
        };
    }

    public Stylist getAmountStylist() {
        return new Stylist() {
            @Override
            public CellStyle getStyle(Workbook wb) {
                if(cellStyleMap.get(AMOUNT_STYLE)==null) {
                    CreationHelper createHelper = wb.getCreationHelper();
                    DataFormat format = createHelper.createDataFormat();
                    CellStyle amountStyle = wb.createCellStyle();
                    amountStyle.setDataFormat(format.getFormat(amountFormat));
                    cellStyleMap.put(AMOUNT_STYLE, amountStyle);
                    return amountStyle;
                } else {
                    return cellStyleMap.get(AMOUNT_STYLE);
                }
            }
        };
    }

    public Stylist getRegularStylist() {
        return new Stylist() {
            @Override
            public CellStyle getStyle(Workbook wb) {
                if(cellStyleMap.get(REGULAR_STYLE)==null) {
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
                    cellStyleMap.put(REGULAR_STYLE, style);
                    return style;
                } else {
                    return cellStyleMap.get(REGULAR_STYLE);
                }
            }
        };
    }

    public Stylist getBoldStylist() {
        return new Stylist() {
            @Override
            public CellStyle getStyle(Workbook wb) {
                if(cellStyleMap.get(BOLD_STYLE)==null) {
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
                    cellStyleMap.put(BOLD_STYLE, style);
                    return style;
                } else {
                    return cellStyleMap.get(BOLD_STYLE);
                }
            }
        };
    }

    public Stylist getDecimalStylist() {
        return new Stylist() {

            @Override
            public CellStyle getStyle(Workbook wb) {
                if(cellStyleMap.get(DECIMAL_STYLE)==null) {
                    CreationHelper createHelper = wb.getCreationHelper();
                    DataFormat format = createHelper.createDataFormat();
                    CellStyle amountStyle = wb.createCellStyle();
                    amountStyle.setDataFormat(format.getFormat(decimalFormat));
                    cellStyleMap.put(DECIMAL_STYLE, amountStyle);
                    return amountStyle;
                } else {
                    return cellStyleMap.get(DECIMAL_STYLE);
                }
            }
        };
    }

    public Stylist getNumberStylist() {
        return new Stylist() {
            @Override
            public CellStyle getStyle(Workbook wb) {
                if(cellStyleMap.get(NUMBER_STYLE)==null) {
                    CreationHelper createHelper = wb.getCreationHelper();
                    DataFormat format = createHelper.createDataFormat();
                    CellStyle amountStyle = wb.createCellStyle();
                    amountStyle.setDataFormat(format.getFormat(numberFormat));
                    cellStyleMap.put(NUMBER_STYLE, amountStyle);
                    return amountStyle;
                } else {
                    return cellStyleMap.get(NUMBER_STYLE);
                }
            }
        };
    }
}
