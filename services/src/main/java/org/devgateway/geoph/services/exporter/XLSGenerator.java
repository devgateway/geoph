package org.devgateway.geoph.services.exporter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.devgateway.geoph.dao.PropsHelper;
import org.devgateway.geoph.enums.TransactionTypeEnum;
import org.devgateway.geoph.model.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileOutputStream;
import java.util.List;

import static org.devgateway.geoph.core.constants.Constants.EXPORT_ENGLISH_TITLE_ARRAY;

/**
 * Created by Sebastian Dimunzio on 6/9/2016.
 */
public class XLSGenerator {
    private static int START_ROW=0;
    private static int START_COLUMN=0;

    @Value("${export.xls.title}")
    private String title;
    private Workbook wb;
    private Sheet sheet;
    private int rowNumber=0;


    public XLSGenerator() {
        wb = new HSSFWorkbook();
        sheet = wb.createSheet(title);
        rowNumber=START_ROW;
    }

    public CellStyle getHeaderStyle(){
        CellStyle titleStyle = getCellStyle(wb);
        return titleStyle;
    }

    public CellStyle getDateStyle(){
        CreationHelper createHelper = wb.getCreationHelper();
        CellStyle dataStyle = wb.createCellStyle();
        dataStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        return dataStyle;
    }

    public void writeHeaders(List<ColumnDefinition> columnDefinitions){
        Row row = sheet.createRow(rowNumber++);
        int position=START_COLUMN;
        for (ColumnDefinition def:columnDefinitions){
            Cell cell = row.createCell(position++);
            cell.setCellValue(def.getTitle());
            cell.setCellStyle(getHeaderStyle());
        }

      }


    private String writeRow(RawRow rawRow) {
        Row dataRow = sheet.createRow(rowNumber++);
        int colNumber=START_COLUMN;




    }

    private String writeRow() {


        for (Location l : locationList) {
            for (Project p : l.getProjects()) {
                rowNumber++;
                dataRow.createCell(0).setCellValue(l.getId());
                dataRow.createCell(1).setCellValue(l.getCode());
                dataRow.createCell(2).setCellValue(l.getLevel());
                dataRow.createCell(3).setCellValue(l.getName());
                dataRow.createCell(4).setCellValue(l.getLatitude());
                dataRow.createCell(5).setCellValue(l.getLongitude());
                dataRow.createCell(6).setCellValue(l.getRegionId());
                dataRow.createCell(7).setCellValue(l.getProvinceId() != null ? l.getProvinceId().toString() : "");
                dataRow.createCell(8).setCellValue(p.getPhId());
                dataRow.createCell(9).setCellValue(p.getTitle());
                StringBuilder iaSb = new StringBuilder();
                for (Agency ia : p.getImplementingAgencies()) {
                    iaSb.append(ia.getCode() + ", ");
                }
                if (iaSb.length() > 3) {
                    dataRow.createCell(10).setCellValue(iaSb.toString().substring(0, iaSb.length() - 3));
                }
                dataRow.createCell(11).setCellValue(p.getExecutingAgency() != null ? p.getExecutingAgency().getName() : "");
                dataRow.createCell(12).setCellValue(p.getFundingAgency() != null ? p.getFundingAgency().getName() : "");
                dataRow.createCell(13).setCellValue(p.getOriginalCurrency() != null ? p.getOriginalCurrency().getName() : "");
                dataRow.createCell(14).setCellValue(p.getTotalProjectAmount());

                Cell cellStartDate = dataRow.createCell(15);
                cellStartDate.setCellValue(p.getStartDate());
                cellStartDate.setCellStyle(dataStyle);

                Cell cellEndDate = dataRow.createCell(16);
                cellEndDate.setCellValue(p.getEndDate());
                cellEndDate.setCellStyle(dataStyle);

                Cell cellRevisedClosingDate = dataRow.createCell(17);
                cellRevisedClosingDate.setCellValue(p.getRevisedClosingDate());
                cellRevisedClosingDate.setCellStyle(dataStyle);

                StringBuilder sectorSb = new StringBuilder();
                for (Sector s : p.getSectors()) {
                    sectorSb.append(s.getCode() + ", ");
                }
                if (sectorSb.length() > 3) {
                    dataRow.createCell(18).setCellValue(sectorSb.toString().substring(0, sectorSb.length() - 3));
                }

                Cell cellPeriodPerformanceStart = dataRow.createCell(19);
                cellPeriodPerformanceStart.setCellValue(p.getPeriodPerformanceStart());
                cellPeriodPerformanceStart.setCellStyle(dataStyle);

                Cell cellPeriodPerformanceEnd = dataRow.createCell(20);
                cellPeriodPerformanceEnd.setCellValue(p.getPeriodPerformanceEnd());
                cellPeriodPerformanceEnd.setCellStyle(dataStyle);

                dataRow.createCell(21).setCellValue(p.getStatus() != null ? p.getStatus().getName() : "");
                dataRow.createCell(22).setCellValue(p.getPhysicalStatus() != null ? p.getPhysicalStatus().getName() : "");
                dataRow.createCell(23).setCellValue(""); //TODO Physical performance
                dataRow.createCell(24).setCellValue("");
                dataRow.createCell(25).setCellValue(p.getGrantClassification() != null ? p.getGrantClassification().getName() : "");
                long disbursements = 0;
                long commitments = 0;
                for (Transaction t : p.getTransactions()) {
                    if (t.getTransactionType().getId() == TransactionTypeEnum.DISBURSEMENT.getId()) {
                        disbursements += t.getAmount();
                    }
                    if (t.getTransactionType().getId() == TransactionTypeEnum.COMMITMENT.getId()) {
                        commitments += t.getAmount();
                    }
                }
                dataRow.createCell(26).setCellValue(disbursements);
                dataRow.createCell(27).setCellValue(commitments);
            }
        }

        try {
            filename = "NEDA_data_.xls";
            FileOutputStream fileOut = new FileOutputStream(PropsHelper.getExportDir() + filename);
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception e) {

        }
        return filename;
    }

    private CellStyle getCellStyle(Workbook wb) {
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

}
