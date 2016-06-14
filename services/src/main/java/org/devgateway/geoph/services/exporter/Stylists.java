package org.devgateway.geoph.services.exporter;

import org.apache.poi.ss.usermodel.*;
import org.devgateway.geoph.core.export.Stylist;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Sebastian Dimunzio on 6/13/2016.
 */
public class Stylists {
    public static Stylist getDateStylist() {
        return new Stylist() {
            @Value("${export.style.date}")
            String dateFormat;

            @Override
            public CellStyle getStyle(Workbook wb) {
                CreationHelper createHelper = wb.getCreationHelper();
                CellStyle dataStyle = wb.createCellStyle();
                dataStyle.setDataFormat(createHelper.createDataFormat().getFormat(dateFormat));
                return dataStyle;
            }
        };

    }

    public static Stylist getAmountStylist() {
        return new Stylist() {
            @Value("${export.style.amount}")
            String amountFormat; //#,##0.0000

            @Override
            public CellStyle getStyle(Workbook wb) {
                CreationHelper createHelper = wb.getCreationHelper();
                DataFormat format = createHelper.createDataFormat();
                CellStyle amountStyle = wb.createCellStyle();
                amountStyle.setDataFormat(format.getFormat(amountFormat));
                return amountStyle;
            }
        };

    }


    public static Stylist getRegularStyle() {
        return new Stylist() {
            @Override
            public CellStyle getStyle(Workbook wb) {
                CellStyle style = wb.createCellStyle();
                Font font = wb.createFont();
                font.setFontName("Open Sans");
                font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
                style.setFont(font);
                style.setBorderBottom(CellStyle.BORDER_NONE);
                style.setBorderTop(CellStyle.BORDER_NONE);
                style.setBorderRight(CellStyle.BORDER_NONE);
                style.setBorderLeft(CellStyle.BORDER_NONE);
                style.setWrapText(true);
                return style;
            }
        };

    }

    public static Stylist getDecimalStyle() {
        return new Stylist() {
            @Value("${export.style.decimal}")
            String decimalFormat; //#,##0.0000

            @Override
            public CellStyle getStyle(Workbook wb) {
                CreationHelper createHelper = wb.getCreationHelper();
                DataFormat format = createHelper.createDataFormat();
                CellStyle amountStyle = wb.createCellStyle();
                amountStyle.setDataFormat(format.getFormat(decimalFormat));
                return amountStyle;
            }
        };

    }

    public static Stylist getNumberStyle() {
        return new Stylist() {
            @Value("${export.style.number}")
            String numberFormat; //#,##0.0000

            @Override
            public CellStyle getStyle(Workbook wb) {
                CreationHelper createHelper = wb.getCreationHelper();
                DataFormat format = createHelper.createDataFormat();
                CellStyle amountStyle = wb.createCellStyle();
                amountStyle.setDataFormat(format.getFormat(numberFormat));
                return amountStyle;
            }
        };

    }
}
