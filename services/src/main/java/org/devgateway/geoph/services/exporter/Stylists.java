package org.devgateway.geoph.services.exporter;

import org.apache.poi.ss.usermodel.*;
import org.devgateway.geoph.core.export.Stylist;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Sebastian Dimunzio on 6/13/2016.
 */
@Service
public class Stylists {
    @Value("${export.style.number}")
    String numberFormat; //#,##0.0000
    @Value("${export.style.date}")
    String dateFormat;
    @Value("${export.style.amount}")
    String amountFormat; //#,##0.0000

    @Value("${export.style.decimal}")
    String decimalFormat; //#,##0.0000


    public Stylist getDateStylist() {
        return new Stylist() {

            @Override
            public CellStyle getStyle(Workbook wb) {
                CreationHelper createHelper = wb.getCreationHelper();
                CellStyle dataStyle = wb.createCellStyle();
                dataStyle.setDataFormat(createHelper.createDataFormat().getFormat(dateFormat));
                return dataStyle;
            }
        };

    }

    public Stylist getAmountStylist() {
        return new Stylist() {

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

    public Stylist getRegularStyle() {
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

    public Stylist getDecimalStyle() {
        return new Stylist() {

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

    public Stylist getNumberStyle() {
        return new Stylist() {

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
