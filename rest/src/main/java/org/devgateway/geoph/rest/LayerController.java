package org.devgateway.geoph.rest;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.devgateway.geoph.core.request.IndicatorRequest;
import org.devgateway.geoph.core.response.IndicatorResponse;
import org.devgateway.geoph.core.services.FilterService;
import org.devgateway.geoph.core.services.LayerService;
import org.devgateway.geoph.dao.PropsHelper;
import org.devgateway.geoph.model.GeoPhotoSource;
import org.devgateway.geoph.model.Indicator;
import org.devgateway.geoph.model.IndicatorDetail;
import org.devgateway.geoph.model.Location;
import org.devgateway.geoph.security.NotAllowException;
import org.geojson.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.devgateway.geoph.core.constants.Constants.INDICATORS_ENGLISH_TITLE_ARRAY;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author dbianco
 *         created on abr 04 2016.
 */
@RestController
@RequestMapping(value = "/layers")
public class LayerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LayerController.class);

    private final LayerService layerService;

    private final FilterService filterService;

    @Autowired
    public LayerController(LayerService layerService, FilterService filterService) {
        this.filterService = filterService;
        this.layerService = layerService;
    }

    @RequestMapping(value = "/secure", method = GET)
    @Secured("ROLE_READ")
    public String secureHelloWorld() {
        LOGGER.debug("secureHelloWorld");
        return "Secured Hello World";
    }

    @RequestMapping(value = "/notSecure", method = GET)
    public String notSecureHelloWorld() {
        LOGGER.debug("notSecureHelloWorld");
        return "Hello World";
    }

    @RequestMapping(value = "/indicators/list", method = GET)
    //@Secured("ROLE_READ")
    public List<Indicator> getIndicatorsList() {
        LOGGER.debug("getIndicatorsList");
        return layerService.getIndicatorsList();
    }

    @RequestMapping(value = "/indicators", method = GET)
    //@Secured("ROLE_READ")
    public IndicatorResponse getIndicator(@RequestParam(value = "id") Long id) {
        LOGGER.debug("getIndicator");
        String filename = "NEDA_indicator_" + id + ".csv";
        IndicatorResponse response = layerService.getIndicatorResponse(id);
        response.setFilename(filename);
        try {
            FileWriter writer = new FileWriter(PropsHelper.getExportDir() + filename);
            String[] titles = INDICATORS_ENGLISH_TITLE_ARRAY;
            for (int i = 0; i < titles.length; i++) {
                writer.append(titles[i]);
                if (i != titles.length - 1) {
                    writer.append(';');
                }
            }
            writer.append(System.getProperty("line.separator"));
            for (Long locId : response.getDetails().keySet()) {
                Location location = filterService.findLocationById(locId);
                if (location != null) {
                    writer.append(location.getName() + ';' + location.getCode() + ';' + response.getDetails().get(locId));
                    writer.append(System.getProperty("line.separator"));
                }
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/indicators", headers = "content-type=multipart/*", method = POST)
    //@Secured("ROLE_READ")
    public IndicatorResponse putIndicator(IndicatorRequest indicatorParam,
                                          @RequestParam(value = "file", required = false) final MultipartFile file) {
        LOGGER.debug("putIndicator");
        IndicatorResponse indicator = new IndicatorResponse(layerService.saveIndicator(indicatorParam.getIndicator()));

        if (file.getOriginalFilename().toLowerCase().endsWith(".csv") || file.getOriginalFilename().toLowerCase().endsWith(".txt")) {
            try {
                byte[] byteArr = file.getBytes();
                if (byteArr.length > 0) {
                    String line = "";
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
                Sheet sheet = wb.getSheetAt(0);
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
        int rowNumber = 0;
        while ((line = br.readLine()) != null) {
            rowNumber++;
            String[] values = line.split(";");
            Location location = null;
            if (StringUtils.isNotBlank(values[1]) && StringUtils.isNumeric(values[1])) {
                try {
                    location = filterService.findLocationByCode(values[1]);
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
            }
            if (location != null) {
                IndicatorDetail detail = new IndicatorDetail();
                detail.setLocationId(location.getId());
                detail.setValue(values[2]);
                detail.setIndicatorId(indicator.getId().longValue());
                layerService.saveIndicatorDetail(detail);
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
            int rowNumber = 0;
            while (rowIterator.hasNext()) {
                rowNumber++;
                IndicatorDetail detail = new IndicatorDetail();
                Row row = rowIterator.next();
                Cell locCode = row.getCell(1);
                Cell value = row.getCell(2);
                String strCode = "";
                if (locCode != null && locCode.getCellType() == 0) {
                    strCode += Double.valueOf(locCode.getNumericCellValue()).intValue();
                } else if (locCode != null && locCode.getCellType() == 1) {
                    strCode += locCode.getStringCellValue();
                }
                String strValue = "";
                if (value != null && value.getCellType() == 0) {
                    strValue += Double.valueOf(value.getNumericCellValue()).intValue();
                } else if (value != null && value.getCellType() == 1) {
                    strValue += value.getStringCellValue();
                }
                Location location = null;
                if (StringUtils.isNotBlank(strCode) && StringUtils.isNumeric(strCode)) {
                    try {
                        location = filterService.findLocationByCode(strCode);
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage());
                    }
                }
                if (location != null) {
                    detail.setLocationId(location.getId());
                    detail.setValue(strValue);
                    detail.setIndicatorId(indicator.getId().longValue());
                    layerService.saveIndicatorDetail(detail);
                } else {
                    indicator.addError("Error at row #" + rowNumber + " - Missing/Wrong UACS code");
                }
            }
        } else {
            indicator.addError("File is empty");
        }
    }

    @RequestMapping(value = "/indicators/id/{indicatorId}", method = GET)
    //@Secured("ROLE_READ")
    public FeatureCollection getIndicatorsData(@PathVariable final long indicatorId) {
        LOGGER.debug("getIndicatorsData for indicator id:" + indicatorId);
        return layerService.getIndicatorsData(indicatorId);
    }

    @RequestMapping(value = "/geophotos/list", method = GET)
    //@Secured("ROLE_READ")
    public List<GeoPhotoSource> getGeoPhotosList() {
        LOGGER.debug("getGeoPhotosList");
        return layerService.getGeoPhotoSourceList();
    }

    @RequestMapping(value = "/geophotos/id/{kmlId}", method = GET)
    //@Secured("ROLE_READ")
    public FeatureCollection getGeoPhotosData(@PathVariable final long kmlId) {
        LOGGER.debug("getGeoPhotosData for kml id:" + kmlId);
        return layerService.getGeoPhotoData(kmlId);
    }

    @ExceptionHandler(NotAllowException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Map<String, Object> handleNotAllowException(NotAllowException nae) {
        Map<String, Object> result = new HashMap<>();
        result.put("error", "Not Allowed");
        result.put("message", nae.getMessage());
        result.put("status", 401);
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }


}
