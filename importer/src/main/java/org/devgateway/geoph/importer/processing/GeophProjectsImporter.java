package org.devgateway.geoph.importer.processing;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * @author dbianco
 *         created on jul 01 2016.
 */
@Service
public abstract class GeophProjectsImporter {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeophProjectsImporter.class);

    private static final String EMPTY_STRING = "";

    @Value("${import.sheetToRead}")
    private int sheetToRead;

    @Value("${import.filepath}")
    private String importFile;

    @Value("${import.start.row.data}")
    private int startRowData;

    @Value("${import.end.row.data}")
    private int endRowData;

    @Value("${import.date.format}")
    private String dateFormat;

    @Value("${import.separator}")
    protected String separator;

    @Value("${import.date}")
    protected String datesStr;

    @Value("${import.statusId}")
    protected int statusId;

    @Value("${import.typeId}")
    protected int typeId;


    @Autowired
    protected ImportStats importStats;

    public enum onProblem { NOTHING, WARN, ERROR, ABORT }

    @Autowired
    protected ImportBaseData importBaseData;

    public void importProjects() throws IOException, InvalidFormatException {
        LOGGER.debug("importProjects started");
        Sheet sheet = getSheetToImport();
        if (sheet.getLastRowNum() > startRowData) {
            Iterator<Row> rowIterator = sheet.iterator();
            for(int i = 0; i<startRowData-1; i++){
                rowIterator.next();
            }
            int rowNumber = startRowData;
            while (rowIterator.hasNext() && rowNumber<=endRowData) {
                rowNumber++;
                addProject(rowIterator.next(), rowNumber);
            }
        }
        LOGGER.info(importStats.toString());
    }

    protected abstract void addProject(Row next, int rowNumber);

    public Sheet getSheetToImport() throws IOException, InvalidFormatException {
        LOGGER.debug("Importing file " + importFile);
        File file = new File(importFile);
        InputStream inputStream = new FileInputStream(file);
        Workbook wb = WorkbookFactory.create(inputStream);
        //Sheet is zero based
        return wb.getSheetAt(sheetToRead-1);
    }

    protected String getStringValueFromCell(Cell cell, String cellName, int rowNumber, onProblem action, boolean toLowerCase) throws Exception {
        if(cell!=null) {
            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                if (toLowerCase) {
                    return cell.getStringCellValue().toLowerCase();
                } else {
                    return cell.getStringCellValue();
                }
            } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                return Double.toString(cell.getNumericCellValue());
            } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                return EMPTY_STRING;
            } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                return Boolean.toString(cell.getBooleanCellValue());
            } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                return cell.getStringCellValue();
            }
            if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
                if (action.equals(onProblem.WARN)) {
                    importStats.addWarning("On row " + rowNumber + " there is a problem on cell " + cellName);
                } else if (action.equals(onProblem.ERROR)) {
                    importStats.addError("On row " + rowNumber + " there is a problem on cell " + cellName);
                } else if (action.equals(onProblem.ABORT)) {
                    importStats.addFailedProject("#row " + rowNumber);
                    throw new Exception("Project should be aborted for a problem in row " + rowNumber + " on cell " + cellName);
                }
            }
        }
        return EMPTY_STRING;
    }

    protected String[] getStringArrayValueFromCell(Cell cell, String cellName, int rowNumber, onProblem action) throws Exception {
        if(cell!=null) {
            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                String cellValue = cell.getStringCellValue();
                if (StringUtils.isNotBlank(cellValue)) {
                    return cellValue.split(separator);
                }
            } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                if (StringUtils.isNotBlank(Double.toString(cell.getNumericCellValue()))) {
                    return new String[]{Double.toString(cell.getNumericCellValue())};
                }
            }
            if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
                if (action.equals(onProblem.WARN)) {
                    importStats.addWarning("On row " + rowNumber + " there is a problem on cell " + cellName);
                } else if (action.equals(onProblem.ERROR)) {
                    importStats.addError("On row " + rowNumber + " there is a problem on cell " + cellName);
                } else if (action.equals(onProblem.ABORT)) {
                    importStats.addFailedProject("#row " + rowNumber);
                    throw new Exception("Project should be aborted for a problem in row " + rowNumber + " on cell " + cellName);
                }
            }
        }
        return new String[]{};
    }

    protected Long getLongValueFromCell(Cell cell, String cellName, int rowNumber, onProblem action) throws Exception {
        if(cell!=null) {
            try {
                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    return Long.parseLong(cell.getStringCellValue());
                } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    return Math.round(cell.getNumericCellValue());
                } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                    return null;
                } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                    return null;
                } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                    return Math.round(cell.getNumericCellValue());
                }
                if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
                    throw new Exception("Cell type error");
                }
            } catch (Exception e) {
                if (action.equals(onProblem.WARN)) {
                    importStats.addWarning("On row " + rowNumber + " there is a problem on cell " + cellName);
                } else if (action.equals(onProblem.ERROR)) {
                    importStats.addError("On row " + rowNumber + " there is a problem on cell " + cellName);
                } else if (action.equals(onProblem.ABORT)) {
                    importStats.addFailedProject("#row " + rowNumber);
                    throw new Exception("Project should be aborted for a problem in row " + rowNumber + " on cell " + cellName);
                }
            }
        }
        return null;
    }

    protected Double getDoubleValueFromCell(Cell cell, String cellName, int rowNumber, onProblem action) throws Exception {
        if(cell!=null) {
            try {
                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    return Double.parseDouble(cell.getStringCellValue());
                } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    return cell.getNumericCellValue();
                } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                    return null;
                } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                    return null;
                } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                    return cell.getNumericCellValue();
                }
                if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
                    throw new Exception("Cell type error");
                }
            } catch (Exception e) {
                if (action.equals(onProblem.WARN)) {
                    importStats.addWarning("On row " + rowNumber + " there is a problem on cell " + cellName);
                } else if (action.equals(onProblem.ERROR)) {
                    importStats.addError("On row " + rowNumber + " there is a problem on cell " + cellName);
                } else if (action.equals(onProblem.ABORT)) {
                    importStats.addFailedProject("#row " + rowNumber);
                    throw new Exception("Project should be aborted for a problem in row " + rowNumber + " on cell " + cellName);
                }
            }
        }
        return null;
    }

    protected Date getDateValueFromCell(Cell cell, String cellName, int rowNumber, onProblem action) throws Exception {
        if(cell!=null) {
            try {
                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
                    return formatter.parse(cell.getStringCellValue());
                } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    return cell.getDateCellValue();
                } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                    return null;
                } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                    return null;
                }
                if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
                    throw new Exception("Cell type error");
                }
            } catch (Exception e) {
                if (action.equals(onProblem.WARN)) {
                    importStats.addWarning("On row " + rowNumber + " there is a problem on cell " + cellName);
                } else if (action.equals(onProblem.ERROR)) {
                    importStats.addError("On row " + rowNumber + " there is a problem on cell " + cellName);
                } else if (action.equals(onProblem.ABORT)) {
                    importStats.addFailedProject("#row " + rowNumber);
                    throw new Exception("Project should be aborted for a problem in row " + rowNumber + " on cell " + cellName);
                }
            }
        }
        return null;
    }

    public Date getImportDate() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
            return formatter.parse(datesStr);
        } catch (ParseException e){
            return null;
        }
    }
}
