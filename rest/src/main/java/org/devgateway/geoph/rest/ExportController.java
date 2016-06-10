package org.devgateway.geoph.rest;

import org.apache.derby.impl.load.Export;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.devgateway.geoph.core.request.AppRequestParams;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.services.ExportService;
import org.devgateway.geoph.core.services.LocationService;
import org.devgateway.geoph.dao.PropsHelper;
import org.devgateway.geoph.enums.TransactionTypeEnum;
import org.devgateway.geoph.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.devgateway.geoph.core.constants.Constants.EXPORT_ENGLISH_TITLE_ARRAY;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Sebastian Dimunzio on 6/9/2016.
 */
@RestController
@RequestMapping(value = "/export")
public class ExportController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExportController.class);

    @Autowired
    LocationService locationService;

    @Autowired
    ExportService exportService;

    public static final String COMMA = ",";

    @RequestMapping(value = "/{fileType}/{language}", method = GET)
    public String exportData(
            @PathVariable final String fileType,
            @PathVariable final String language,
            AppRequestParams filters) {
        LOGGER.debug("exportData");

        exportService.export(filters.getParameters());

        return null;
    }

    private String getCsvFile(String language, List<Location> locationList) {
        String filename = "NEDA_data_.csv";
        try {
            FileWriter writer = new FileWriter(PropsHelper.getExportDir() + filename);
            String[] titles = null;
            if (language.toLowerCase().trim().equals("ph")) {
                //TODO
                titles = EXPORT_ENGLISH_TITLE_ARRAY;
            } else {
                titles = EXPORT_ENGLISH_TITLE_ARRAY;
            }
            for (int i = 0; i < titles.length; i++) {
                writer.append(titles[i]);
                if (i != titles.length - 1) {
                    writer.append(',');
                }
            }
            writer.append(System.getProperty("line.separator"));
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            for (Location l : locationList) {
                for (Project p : l.getProjects()) {
                    writer.append("" + l.getId());
                    writer.append(COMMA + l.getCode());
                    writer.append(COMMA + l.getLevel());
                    writer.append(COMMA + l.getName().replace(',', '/'));
                    writer.append(COMMA + l.getLatitude());
                    writer.append(COMMA + l.getLongitude());
                    writer.append(COMMA + l.getRegionId());
                    writer.append(l.getProvinceId() != null ? COMMA + l.getProvinceId().toString() : COMMA);
                    writer.append(COMMA + p.getPhId());
                    writer.append(COMMA + p.getTitle().replace(',', '/'));
                    writer.append(COMMA);
                    StringBuilder iaSb = new StringBuilder();
                    for (Agency ia : p.getImplementingAgencies()) {
                        iaSb.append(ia.getCode() + "/");
                    }
                    if (iaSb.length() > 2) {
                        writer.append(iaSb.toString().substring(0, iaSb.length() - 2));
                    }
                    writer.append(p.getExecutingAgency() != null ? COMMA + p.getExecutingAgency().getName() : COMMA);
                    writer.append(p.getFundingAgency() != null ? COMMA + p.getFundingAgency().getName() : COMMA);
                    writer.append(p.getOriginalCurrency() != null ? COMMA + p.getOriginalCurrency().getName() : COMMA);
                    writer.append(COMMA + p.getTotalProjectAmount());

                    writer.append(p.getStartDate() != null ? COMMA + formatter.format(p.getStartDate()) : COMMA);

                    writer.append(p.getEndDate() != null ? COMMA + formatter.format(p.getEndDate()) : COMMA);

                    writer.append(p.getRevisedClosingDate() != null ? COMMA + formatter.format(p.getRevisedClosingDate()) : COMMA);

                    writer.append(COMMA);
                    StringBuilder sectorSb = new StringBuilder();
                    for (Sector s : p.getSectors()) {
                        sectorSb.append(s.getCode() + "/");
                    }
                    if (sectorSb.length() > 2) {
                        writer.append(sectorSb.toString().substring(0, sectorSb.length() - 2));
                    }

                    writer.append(p.getPeriodPerformanceStart() != null ? COMMA + formatter.format(p.getPeriodPerformanceStart()) : COMMA);
                    writer.append(p.getPeriodPerformanceEnd() != null ? COMMA + formatter.format(p.getPeriodPerformanceEnd()) : COMMA);

                    writer.append(p.getStatus() != null ? COMMA + p.getStatus().getName() : COMMA);
                    writer.append(p.getPhysicalStatus() != null ? COMMA + p.getPhysicalStatus().getName() : COMMA);

                    writer.append(COMMA); //TODO Physical performance
                    writer.append(COMMA);

                    writer.append(p.getGrantClassification() != null ? COMMA + p.getGrantClassification().getName() : COMMA);
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
                    writer.append(COMMA + disbursements);
                    writer.append(COMMA + commitments);
                    writer.append(System.getProperty("line.separator"));
                }
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return filename;
    }



}
