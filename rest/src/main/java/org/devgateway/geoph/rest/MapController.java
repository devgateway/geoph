package org.devgateway.geoph.rest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.devgateway.geoph.model.*;
import org.devgateway.geoph.model.Currency;
import org.devgateway.geoph.services.AppMapService;
import org.devgateway.geoph.services.FilterService;
import org.devgateway.geoph.services.GeoJsonService;
import org.devgateway.geoph.services.ProjectService;
import org.devgateway.geoph.util.Parameters;
import org.devgateway.geoph.util.PropsHelper;
import org.devgateway.geoph.util.TransactionTypeEnum;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.devgateway.geoph.util.Constants.*;
import static org.devgateway.geoph.util.Constants.FILTER_GENDER_RESPONSIVENESS;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author dbianco
 *         created on abr 20 2016.
 */
@RestController
@RequestMapping(value = "/maps")
public class MapController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapController.class);
    public static final String COMMA = ",";

    private final AppMapService service;

    private final FilterService filterService;

    private final ProjectService projectService;

    private final GeoJsonService geoJsonService;

    @RequestMapping(method = GET)
    public Page<AppMap> findMaps( @PageableDefault(page = 0, size = 20, sort = "id") final Pageable pageable) {
        LOGGER.debug("findMaps");
        return service.findAll(pageable);
    }

    @Autowired
    public MapController(AppMapService service, FilterService filterService,
                         ProjectService projectService, GeoJsonService geoJsonService) {
        this.service = service;
        this.filterService = filterService;
        this.projectService = projectService;
        this.geoJsonService = geoJsonService;
    }

    @RequestMapping(value = "/save", method = POST)
    public AppMap saveMap(@RequestParam(value = "name", required = true) String name,
                          @RequestParam(value = "description", required = true) String description,
                          @RequestBody Object mapVariables) {
        LOGGER.debug("saveMap");
        AppMap appMap = new AppMap(name, description, mapVariables.toString());
        return service.save(appMap);
    }

    @RequestMapping(value = "/id/{id}", method = GET)
    public AppMap findMapById(@PathVariable final long id) {
        LOGGER.debug("findMapById");
        return service.findById(id);
    }

    @RequestMapping(value = "/key/{key}", method = GET)
    public AppMap findMapByKey(@PathVariable final String key) {
        LOGGER.debug("findMapByKey");
        return service.findByKey(key);
    }


    @RequestMapping(value = "/search/{name}", method = GET)
    public List <AppMap> findMapByName(@PathVariable final String name) {
        LOGGER.debug("findMapByKey");
        return service.findByNameOrDescription(name);
    }

    @RequestMapping(value = "/print", method = GET)
    public String printPage(@RequestParam(value = "url", required = true) String url){
        String filename = null;
        try {
            if(StringUtils.isNotBlank(PropsHelper.getScreenFirefoxExe())) {
                File pathToBinary = new File(PropsHelper.getScreenFirefoxExe());
                FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);
                driver.get(url);
                Thread.sleep(PropsHelper.getScreenCaptureTimeToWait());
                filename = getRandomKey() + ".png";
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(PropsHelper.getScreenCaptureDir() + filename));
                driver.close();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return filename;
    }

    @RequestMapping(value = "/export/fileType/{fileType}/language/{language}", method = GET)
    public String exportData(
            @PathVariable final String fileType,
            @PathVariable final String language,
            @RequestParam(value = FILTER_START_DATE, required = false) String startDate,
            @RequestParam(value = FILTER_END_DATE, required = false) String endDate,
            @RequestParam(value = FILTER_PERFORMANCE_START, required = false) String performanceStart,
            @RequestParam(value = FILTER_PERFORMANCE_END, required = false) String performanceEnd,
            @RequestParam(value = FILTER_SECTOR, required = false) String sectors,
            @RequestParam(value = FILTER_STATUS, required = false) String statuses,
            @RequestParam(value = FILTER_LOCATION, required = false) String locations,
            @RequestParam(value = FILTER_PROJECT, required = false) String projects,
            @RequestParam(value = FILTER_IMPLEMENTING_AGENCY, required = false) String impAgencies,
            @RequestParam(value = FILTER_FUNDING_AGENCY, required = false) String fundingAgencies,
            @RequestParam(value = FILTER_FLOW_TYPE, required = false) String flowTypes,
            @RequestParam(value = FILTER_PROJECT_TITLE, required = false) String projectTitle,
            @RequestParam(value = FILTER_PHYSICAL_STATUS, required = false) String physicalStatuses,
            @RequestParam(value = FILTER_CLIMATE_CHANGE, required = false) String climateChange,
            @RequestParam(value = FILTER_GENDER_RESPONSIVENESS, required = false) String genderResponsiveness,
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MAX, required = false) Double financialAmountMax,
            @RequestParam(value = FILTER_FINANCIAL_AMOUNT_MIN, required = false) Double financialAmountMin){
        LOGGER.debug("exportData");
        Parameters params = new Parameters(startDate, endDate, performanceStart,
                performanceEnd, sectors, statuses, locations,
                projects, impAgencies, fundingAgencies, flowTypes,
                projectTitle, physicalStatuses, climateChange, genderResponsiveness,
                financialAmountMin, financialAmountMax, null);
        List<Location> locationList = geoJsonService.getLocationsForExport(params);

        String filename = null;

        if(fileType.toLowerCase().trim().equals("xls")){
            filename = getExcelFile(language, locationList);
        } else {
            filename = getCsvFile(language, locationList);
        }
        return filename;
    }

    private String getCsvFile(String language, List<Location> locationList) {
        String filename = "NEDA_data_" + getRandomKey() + ".csv";
        try {
            FileWriter writer = new FileWriter(PropsHelper.getExportDir() + filename);
            String[] titles = null;
            if(language.toLowerCase().trim().equals("ph")){
                //TODO
                titles = EXPORT_ENGLISH_TITLE_ARRAY;
            } else {
                titles = EXPORT_ENGLISH_TITLE_ARRAY;
            }
            for(int i=0; i<titles.length; i++) {
                writer.append(titles[i]);
                if(i!=titles.length-1){
                    writer.append(',');
                }
            }
            writer.append(System.getProperty("line.separator"));
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            for(Location l : locationList) {
                for (Project p : l.getProjects()) {
                    writer.append(""+l.getId());
                    writer.append(COMMA +l.getCode());
                    writer.append(COMMA +l.getLevel());
                    writer.append(COMMA +l.getName().replace(',', '/'));
                    writer.append(COMMA +l.getLatitude());
                    writer.append(COMMA +l.getLongitude());
                    writer.append(COMMA +l.getRegionId());
                    writer.append(l.getProvinceId() != null ? COMMA +l.getProvinceId().toString() : COMMA);
                    writer.append(COMMA +p.getPhId());
                    writer.append(COMMA +p.getTitle().replace(',', '/'));
                    writer.append(COMMA);
                    StringBuilder iaSb = new StringBuilder();
                    for(Agency ia : p.getImplementingAgencies()){
                        iaSb.append(ia.getCode() + "/");
                    }
                    if(iaSb.length()>2){
                        writer.append(iaSb.toString().substring(0, iaSb.length()-2));
                    }
                    writer.append(p.getExecutingAgency()!=null? COMMA +p.getExecutingAgency().getName(): COMMA);
                    writer.append(p.getFundingAgency()!=null? COMMA +p.getFundingAgency().getName(): COMMA);
                    writer.append(p.getOriginalCurrency()!=null? COMMA +p.getOriginalCurrency().getName(): COMMA);
                    writer.append(COMMA +p.getTotalProjectAmount());

                    writer.append(COMMA + formatter.format(p.getStartDate()));
                    writer.append(COMMA + formatter.format(p.getEndDate()));
                    writer.append(COMMA + formatter.format(p.getRevisedClosingDate()));

                    writer.append(COMMA);
                    StringBuilder sectorSb = new StringBuilder();
                    for(Sector s : p.getSectors()){
                        sectorSb.append(s.getCode() + "/");
                    }
                    if(sectorSb.length()>2){
                        writer.append(sectorSb.toString().substring(0, sectorSb.length()-2));
                    }

                    writer.append(COMMA + formatter.format(p.getPeriodPerformanceStart()));
                    writer.append(COMMA + formatter.format(p.getPeriodPerformanceEnd()));

                    writer.append(p.getStatus()!=null?COMMA+p.getStatus().getName():COMMA);
                    writer.append(p.getPhysicalStatus()!=null?COMMA+p.getPhysicalStatus().getName():COMMA);

                    writer.append(COMMA); //TODO Physical performance
                    writer.append(COMMA);

                    writer.append(p.getGrantClassification()!=null?COMMA+p.getGrantClassification().getName():COMMA);
                    long disbursements = 0;
                    long commitments = 0;
                    for(Transaction t : p.getTransactions()){
                        if(t.getTransactionType().getId() == TransactionTypeEnum.DISBURSEMENT.getId()){
                            disbursements += t.getAmount();
                        }
                        if(t.getTransactionType().getId() == TransactionTypeEnum.COMMITMENT.getId()){
                            commitments += t.getAmount();
                        }
                    }
                    writer.append(COMMA+disbursements);
                    writer.append(COMMA+commitments);
                    writer.append(System.getProperty("line.separator"));
                }
            }
            writer.flush();
            writer.close();
        } catch(IOException e) {
            LOGGER.error(e.getMessage());
        }
        return filename;
    }

    private String getExcelFile(String language, List<Location> locationList) {
        String filename = "";
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("Geoph export");
        Row row = sheet.createRow((short)0);
        String[] titles = null;
        if(language.toLowerCase().trim().equals("ph")){
            //TODO
            titles = EXPORT_ENGLISH_TITLE_ARRAY;
        } else {
            titles = EXPORT_ENGLISH_TITLE_ARRAY;
        }
        CellStyle titleStyle = getCellStyle(wb);
        for(int i=0; i<titles.length; i++){
            Cell cell = row.createCell((short) i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(titleStyle);
        }
        short rowNumber = 0;
        CreationHelper createHelper = wb.getCreationHelper();
        CellStyle dataStyle = wb.createCellStyle();
        dataStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        for(Location l : locationList){
            for(Project p : l.getProjects()){
                rowNumber ++;
                Row dataRow = sheet.createRow(rowNumber);
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
                for(Agency ia : p.getImplementingAgencies()){
                    iaSb.append(ia.getCode() + ", ");
                }
                if(iaSb.length()>3){
                    dataRow.createCell(10).setCellValue(iaSb.toString().substring(0, iaSb.length()-3));
                }
                dataRow.createCell(11).setCellValue(p.getExecutingAgency()!=null?p.getExecutingAgency().getName():"");
                dataRow.createCell(12).setCellValue(p.getFundingAgency()!=null?p.getFundingAgency().getName():"");
                dataRow.createCell(13).setCellValue(p.getOriginalCurrency()!=null?p.getOriginalCurrency().getName():"");
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
                for(Sector s : p.getSectors()){
                    sectorSb.append(s.getCode() + ", ");
                }
                if(sectorSb.length()>3){
                    dataRow.createCell(18).setCellValue(sectorSb.toString().substring(0, sectorSb.length()-3));
                }

                Cell cellPeriodPerformanceStart = dataRow.createCell(19);
                cellPeriodPerformanceStart.setCellValue(p.getPeriodPerformanceStart());
                cellPeriodPerformanceStart.setCellStyle(dataStyle);

                Cell cellPeriodPerformanceEnd = dataRow.createCell(20);
                cellPeriodPerformanceEnd.setCellValue(p.getPeriodPerformanceEnd());
                cellPeriodPerformanceEnd.setCellStyle(dataStyle);

                dataRow.createCell(21).setCellValue(p.getStatus()!=null?p.getStatus().getName():"");
                dataRow.createCell(22).setCellValue(p.getPhysicalStatus()!=null?p.getPhysicalStatus().getName():"");
                dataRow.createCell(23).setCellValue(""); //TODO Physical performance
                dataRow.createCell(24).setCellValue("");
                dataRow.createCell(25).setCellValue(p.getGrantClassification()!=null?p.getGrantClassification().getName():"");
                long disbursements = 0;
                long commitments = 0;
                for(Transaction t : p.getTransactions()){
                    if(t.getTransactionType().getId() == TransactionTypeEnum.DISBURSEMENT.getId()){
                        disbursements += t.getAmount();
                    }
                    if(t.getTransactionType().getId() == TransactionTypeEnum.COMMITMENT.getId()){
                        commitments += t.getAmount();
                    }
                }
                dataRow.createCell(26).setCellValue(disbursements);
                dataRow.createCell(27).setCellValue(commitments);
            }
        }

        try {
            filename = "NEDA_data_" + getRandomKey() + ".xls";
            FileOutputStream fileOut = new FileOutputStream(PropsHelper.getExportDir()+filename);
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception e){
            LOGGER.error(e.getMessage());
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

    private static String getRandomKey(){
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            sb.append(ALPHABET.charAt(r.nextInt(ALPHABET_NUMBER)));
        }
        return sb.toString().toLowerCase();
    }


    @RequestMapping(value = "/readCSV", method = GET)
    public void readCSV() {
        LOGGER.debug("readCSV");
        try {
            String line = "";
            //Create the file reader
            BufferedReader fileReader = new BufferedReader(new FileReader("grants3.csv"));

            List<Currency> currList = filterService.findAllCurrencies();
            Map<String, Currency> currMap = new HashMap<>();
            for(Currency c : currList){
                currMap.put(c.getCode().toLowerCase().trim(), c);
            }

            List<GrantSubType> gstList = filterService.findAllGrantSubTypes();
            Map<String, GrantSubType> gstMap = new HashMap<>();
            for(GrantSubType g : gstList){
                gstMap.put(g.getName().toLowerCase(), g);
            }

            List<Sector> sList = filterService.findAllSectors();
            Map<String, Sector> sMap = new HashMap<>();
            for (Sector s : sList){
                sMap.put(s.getName().toLowerCase().trim(), s);
            }

            List<Location> lList = filterService.findAllLocations();
            Map<String, Location> lMap = new HashMap<>();
            for (Location l : lList){
                lMap.put(l.getCode().toString(), l);
            }

            List<Status> stList = filterService.findAllStatuses();
            Map<String, Status> stMap = new HashMap<>();
            for (Status st : stList){
                stMap.put(st.getName().toLowerCase().trim(), st);
            }
            List<PhysicalStatus> psList = filterService.findAllPhysicalStatus();
            Map<String, PhysicalStatus> psMap = new HashMap<>();
            for (PhysicalStatus ps : psList){
                psMap.put(ps.getName().toLowerCase().trim(), ps);
            }

            List<FundingAgency> faList = filterService.findAllFundingAgencies();
            Map<String, FundingAgency> faMap = new HashMap<>();
            for (FundingAgency fa : faList){
                faMap.put(fa.getCode().toLowerCase().trim(), fa);
            }

            List<ImplementingAgency> iaList = filterService.findAllImpAgencies();
            Map<String, ImplementingAgency> iaMap = new HashMap<>();
            for (ImplementingAgency ia : iaList){
                iaMap.put(ia.getCode().toLowerCase().trim(), ia);
            }

            List<Classification> caList = filterService.findAllClassifications();
            Map<String, Classification> caMap = new HashMap<>();
            for (Classification ca : caList){
                caMap.put(ca.getName().toLowerCase().trim(), ca);
            }

            List<ExecutingAgency> eaList = filterService.findAllExecutingAgencies();
            Map<String, ExecutingAgency> eaMap = new HashMap<>();
            for (ExecutingAgency ea : eaList){
                eaMap.put(ea.getName().toLowerCase().trim(), ea);
            }
            ExecutingAgency eaDef = eaMap.get("several agencies");
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatter2 = new SimpleDateFormat("MM/dd/yyyy");

            //Read the file line by line
            while ((line = fileReader.readLine()) != null)
            {
                //Get all tokens available in line
                String[] tokens = line.split(";");
                Project p = new Project();
                if(tokens[2].length()<255){
                    p.setTitle(tokens[2].trim());
                } else {
                    p.setTitle(tokens[2].trim().substring(0,255));
                }
                p.setPhId(tokens[1].trim());
                if(StringUtils.isNotBlank(tokens[3])) {
                    p.setFundingAgency(faMap.get(tokens[3].toLowerCase().trim()));
                }
                if(StringUtils.isNotBlank(tokens[5])) {
                    String[] ias = tokens[5].split(COMMA);
                    Set<Agency> iaSet = new HashSet<>();
                    for(String ia : ias){
                        if(iaMap.get(ia.toLowerCase().trim())!=null){
                            iaSet.add(iaMap.get(ia.toLowerCase().trim()));
                        }
                    }
                    if(iaSet.size()>0){
                        p.setImplementingAgencies(iaSet);
                    }
                }
                if(StringUtils.isNotBlank(tokens[6])) {
                    p.setGrantClassification(caMap.get(tokens[6].toLowerCase().trim()));
                }
                if(StringUtils.isNotBlank(tokens[4])) {
                    ExecutingAgency ea = eaMap.get(tokens[4].toLowerCase().trim());
                    p.setExecutingAgency(ea != null ? ea : eaDef);
                }

                if(StringUtils.isNotBlank(tokens[7])) {
                    Currency c = currMap.get("usd".toLowerCase().trim());
                    p.setOriginalCurrency(c != null ? c : null);
                }
                if(StringUtils.isNotBlank(tokens[7])) {
                    p.setTotalProjectAmountOriginal(Double.parseDouble(tokens[7].replace(".", "").replace(COMMA, ".").replaceAll("[^0-9?!\\.]", "")));
                }
                if(StringUtils.isNotBlank(tokens[8])) {
                    p.setTotalProjectAmount(Double.parseDouble(tokens[8].replace(".", "").replace(COMMA, ".")));
                }

                if(StringUtils.isNotBlank(tokens[10])) {
                    Grant grant = new Grant();
                    if(tokens[10].trim().equals("-") || tokens[10].trim().toLowerCase().equals("n/a")){
                        grant.setAmount(0);
                    } else {
                        grant.setAmount(Double.parseDouble(tokens[10].replace(".", "").replace(COMMA, ".")));
                    }
                    grant.setTransactionStatusId(2);
                    grant.setTransactionTypeId(2);
                    grant.setDate(formatter2.parse("12/31/2015"));
                    grant.setProject(p);
                    if(StringUtils.isNotBlank(tokens[18])) {
                        GrantSubType gst = gstMap.get(tokens[18].toLowerCase().trim());
                        grant.setGrantSubTypeId(gst != null ? gst.getId() : null);
                    }
                    Set<Transaction> tSet = new HashSet<>();
                    tSet.add(grant);
                    p.setTransactions(tSet);
                }

                if(StringUtils.isNotBlank(tokens[11])) {
                    if(tokens[11].indexOf("/")<0){
                        p.setStartDate(formatter1.parse(tokens[11]));
                    } else{
                        p.setStartDate(formatter2.parse(tokens[11]));
                    }
                }
                if(StringUtils.isNotBlank(tokens[12])) {
                    if(tokens[12].indexOf("/")<0){
                        p.setEndDate(formatter1.parse(tokens[12]));
                    } else{
                        p.setEndDate(formatter2.parse(tokens[12]));
                    }
                }
                if(StringUtils.isNotBlank(tokens[13])) {
                    if(tokens[13].indexOf("/")<0){
                        p.setRevisedClosingDate(formatter1.parse(tokens[13]));
                    } else{
                        p.setRevisedClosingDate(formatter2.parse(tokens[13]));
                    }
                }
                if(StringUtils.isNotBlank(tokens[14])) {
                    Set<Sector> ss = new HashSet<>();
                    Sector s = sMap.get(tokens[14].toLowerCase().trim());
                    if(s!=null){
                        ss.add(s);
                        p.setSectors(ss);
                    }
                }
                if(tokens.length>15 &&StringUtils.isNotBlank(tokens[15])) {
                    String[] locs = tokens[15].split(COMMA);
                    Set<Location> ls = new HashSet<>();
                    for(String loc : locs){
                        ls.add(lMap.get(loc.trim()));
                    }
                    p.setLocations(ls);
                }
                if(tokens.length>18 && StringUtils.isNotBlank(tokens[19])) {
                    p.setStatus(stMap.get(tokens[19].toLowerCase().trim()));
                }
                if(tokens.length>19 &&StringUtils.isNotBlank(tokens[20])) {
                    p.setPhysicalStatus(psMap.get(tokens[20].toLowerCase().trim()));
                }
                if(tokens.length>20 &&StringUtils.isNotBlank(tokens[21])) {
                    if(tokens[21].indexOf("/")<0){
                        p.setPeriodPerformanceStart(formatter1.parse(tokens[21]));
                    } else{
                        p.setPeriodPerformanceStart(formatter2.parse(tokens[21]));
                    }
                }
                if(tokens.length>21 &&StringUtils.isNotBlank(tokens[22])) {
                    if(tokens[22].indexOf("/")<0){
                        p.setPeriodPerformanceEnd(formatter1.parse(tokens[22]));
                    } else{
                        p.setPeriodPerformanceEnd(formatter2.parse(tokens[22]));
                    }
                }
                projectService.save(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
