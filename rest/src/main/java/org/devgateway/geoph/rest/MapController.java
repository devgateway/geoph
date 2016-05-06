package org.devgateway.geoph.rest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.devgateway.geoph.model.*;
import org.devgateway.geoph.services.AppMapService;
import org.devgateway.geoph.services.FilterService;
import org.devgateway.geoph.services.ProjectService;
import org.devgateway.geoph.util.PropsHelper;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.devgateway.geoph.util.Constants.ALPHABET;
import static org.devgateway.geoph.util.Constants.ALPHABET_NUMBER;

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

    private final AppMapService service;

    private final FilterService filterService;

    private final ProjectService projectService;

    @RequestMapping(method = GET)
    public Page<AppMap> findMaps( @PageableDefault(page = 0, size = 20, sort = "id") final Pageable pageable) {
        LOGGER.debug("findMaps");
        return service.findAll(pageable);
    }

    @Autowired
    public MapController(AppMapService service, FilterService filterService, ProjectService projectService) {
        this.service = service;
        this.filterService = filterService;
        this.projectService = projectService;
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
            BufferedReader fileReader = new BufferedReader(new FileReader("grantsMarina.csv"));

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
                    String[] ias = tokens[5].split(",");
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
                if(StringUtils.isNotBlank(tokens[9])) {
                    p.setTotalProjectAmount(Double.parseDouble(tokens[9].replace(".", "").replace(",", ".")));
                }

                if(StringUtils.isNotBlank(tokens[12])) {
                    if(tokens[12].indexOf("/")<0){
                        p.setStartDate(formatter1.parse(tokens[12]));
                    } else{
                        p.setStartDate(formatter2.parse(tokens[12]));
                    }
                }
                if(StringUtils.isNotBlank(tokens[13])) {
                    if(tokens[13].indexOf("/")<0){
                        p.setEndDate(formatter1.parse(tokens[13]));
                    } else{
                        p.setEndDate(formatter2.parse(tokens[13]));
                    }
                }
                if(StringUtils.isNotBlank(tokens[14])) {
                    if(tokens[14].indexOf("/")<0){
                        p.setRevisedClosingDate(formatter1.parse(tokens[14]));
                    } else{
                        p.setRevisedClosingDate(formatter2.parse(tokens[14]));
                    }
                }
                if(StringUtils.isNotBlank(tokens[15])) {
                    Set<Sector> ss = new HashSet<>();
                    Sector s = sMap.get(tokens[15].toLowerCase().trim());
                    if(s!=null){
                        ss.add(s);
                        p.setSectors(ss);
                    }
                }
                if(tokens.length>16 &&StringUtils.isNotBlank(tokens[16])) {
                    String[] locs = tokens[16].split(",");
                    Set<Location> ls = new HashSet<>();
                    for(String loc : locs){
                        ls.add(lMap.get(loc.trim()));
                    }
                    p.setLocations(ls);
                }
                if(tokens.length>19 && StringUtils.isNotBlank(tokens[20])) {
                    p.setStatus(stMap.get(tokens[20].toLowerCase().trim()));
                }
                if(tokens.length>20 &&StringUtils.isNotBlank(tokens[21])) {
                    p.setPhysicalStatus(psMap.get(tokens[21].toLowerCase().trim()));
                }
                if(tokens.length>21 &&StringUtils.isNotBlank(tokens[22])) {
                    if(tokens[22].indexOf("/")<0){
                        p.setPeriodPerformanceStart(formatter1.parse(tokens[22]));
                    } else{
                        p.setPeriodPerformanceStart(formatter2.parse(tokens[22]));
                    }
                }
                if(tokens.length>22 &&StringUtils.isNotBlank(tokens[23])) {
                    if(tokens[23].indexOf("/")<0){
                        p.setPeriodPerformanceEnd(formatter1.parse(tokens[23]));
                    } else{
                        p.setPeriodPerformanceEnd(formatter2.parse(tokens[23]));
                    }
                }
                projectService.save(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
