package org.devgateway.geoph.services;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.devgateway.geoph.core.repositories.IndicatorDetailRepository;
import org.devgateway.geoph.core.repositories.IndicatorRepository;
import org.devgateway.geoph.core.repositories.LocationRepository;
import org.devgateway.geoph.core.request.IndicatorRequest;
import org.devgateway.geoph.core.response.IndicatorResponse;
import org.devgateway.geoph.core.services.ImportService;
import org.devgateway.geoph.model.IndicatorDetail;
import org.devgateway.geoph.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Iterator;

import static org.devgateway.geoph.core.constants.Constants.CSV_RECORD_SEPARATOR;

/**
 * @author dbianco
 *         created on jun 17 2016.
 */
@Service
public class ImportServiceImpl implements ImportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportServiceImpl.class);
    private static final int SHEET_TO_READ = 0;
    private static final int STARTING_ROW = 0;
    private static final int LOCATION_ID_POS = 1;
    private static final int INDICATOR_VALUE_POS = 2;

    @Autowired
    IndicatorRepository indicatorRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    IndicatorDetailRepository indicatorDetailRepository;

    @Override
    public IndicatorResponse importIndicatorFromFile(IndicatorRequest indicatorParam, MultipartFile file) {
        IndicatorResponse indicator = new IndicatorResponse(indicatorRepository.save(indicatorParam.getIndicator()));

        if (file.getOriginalFilename().toLowerCase().endsWith(".csv") || file.getOriginalFilename().toLowerCase().endsWith(".txt")) {
            try {
                byte[] byteArr = file.getBytes();
                if (byteArr.length > 0) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(byteArr)));
                    generateIndicatorDetailFromCSV(indicator, br);
                } else {
                    indicator.addError("File is empty");
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        } else if (file.getOriginalFilename().toLowerCase().endsWith(".xls") || file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
            try {
                byte[] byteArr = file.getBytes();
                InputStream inputStream = new ByteArrayInputStream(byteArr);
                Workbook wb = WorkbookFactory.create(inputStream);
                Sheet sheet = wb.getSheetAt(SHEET_TO_READ);
                generateIndicatorDetailFromSheet(indicator, sheet);
            } catch (Exception e) {
                indicator.addError("File is empty or corrupted");
                LOGGER.error(e.getMessage());
            }
        } else {
            indicator.addError("File type not allowed - It should be an excel or csv file");
        }
        return indicator;
    }

    private void generateIndicatorDetailFromCSV(IndicatorResponse indicator, BufferedReader br) throws IOException {
        String line;
        //Avoid labels
        br.readLine();
        int rowNumber = STARTING_ROW;
        while ((line = br.readLine()) != null) {
            rowNumber++;
            String[] values = line.split(String.valueOf(CSV_RECORD_SEPARATOR));
            Location location = null;
            if (StringUtils.isNotBlank(values[LOCATION_ID_POS]) && StringUtils.isNumeric(values[LOCATION_ID_POS])) {
                try {
                    location = locationRepository.findByCode(values[LOCATION_ID_POS]);
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
            }
            if (location != null) {
                IndicatorDetail detail = new IndicatorDetail();
                detail.setLocationId(location.getId());
                detail.setValue(values[INDICATOR_VALUE_POS]);
                detail.setIndicatorId(indicator.getId().longValue());
                indicatorDetailRepository.save(detail);
            } else {
                indicator.addError("Error at row #" + rowNumber + " - Missing/Wrong UACS code");
            }
        }
    }

    private void generateIndicatorDetailFromSheet(IndicatorResponse indicator, Sheet sheet) {
        if (sheet.getLastRowNum() > 0) {
            Iterator<Row> rowIterator = sheet.iterator();
            //Avoid labels
            rowIterator.next();
            int rowNumber = STARTING_ROW;
            while (rowIterator.hasNext()) {
                rowNumber++;
                IndicatorDetail detail = new IndicatorDetail();
                Row row = rowIterator.next();
                Cell locCode = row.getCell(LOCATION_ID_POS);
                Cell value = row.getCell(INDICATOR_VALUE_POS);
                String strCode = "";
                if (locCode != null && locCode.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    strCode += Double.valueOf(locCode.getNumericCellValue()).intValue();
                } else if (locCode != null && locCode.getCellType() == Cell.CELL_TYPE_STRING) {
                    strCode += locCode.getStringCellValue();
                }
                String strValue = "";
                if (value != null && value.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    strValue += Double.valueOf(value.getNumericCellValue()).intValue();
                } else if (value != null && value.getCellType() == Cell.CELL_TYPE_STRING) {
                    strValue += value.getStringCellValue();
                }
                Location location = null;
                if (StringUtils.isNotBlank(strCode) && StringUtils.isNumeric(strCode)) {
                    try {
                        location = locationRepository.findByCode(strCode);
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage());
                    }
                }
                if (location != null) {
                    detail.setLocationId(location.getId());
                    detail.setValue(strValue);
                    detail.setIndicatorId(indicator.getId().longValue());
                    indicatorDetailRepository.save(detail);
                } else {
                    indicator.addError("Error at row #" + rowNumber + " - Missing/Wrong UACS code");
                }
            }
        } else {
            indicator.addError("File is empty");
        }
    }
}
