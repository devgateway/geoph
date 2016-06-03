package org.devgateway.geoph.rest;

import org.apache.commons.lang3.StringUtils;
import org.devgateway.geoph.model.*;
import org.devgateway.geoph.model.Currency;
import org.devgateway.geoph.services.FilterService;
import org.devgateway.geoph.services.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author dbianco
 *         created on may 31 2016.
 */
@RestController
@RequestMapping(value = "/import")
public class ImportDataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapController.class);

    public static final String COMMA = ",";

    private final FilterService filterService;

    private final ProjectService projectService;

    @Autowired
    public ImportDataController(FilterService filterService, ProjectService projectService) {
        this.filterService = filterService;
        this.projectService = projectService;
    }

    @RequestMapping(value = "/grants", method = GET)
    public void importGrantsFromCSV(@RequestParam(value = "filename", required = true) String filename) {
        LOGGER.debug("importGrantsFromCSV");
        try {
            String line = "";
            //Create the file reader
            BufferedReader fileReader = new BufferedReader(new FileReader(filename));

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
                String locationsArray = "";
                if(tokens.length>17 &&StringUtils.isNotBlank(tokens[17])) {
                    locationsArray = tokens[17];
                } else if(tokens.length>16 &&StringUtils.isNotBlank(tokens[16])) {
                    locationsArray = tokens[16];
                } else if (tokens.length>15 &&StringUtils.isNotBlank(tokens[15])) {
                    locationsArray = tokens[15];
                }
                if(StringUtils.isNotBlank(locationsArray)) {
                    String[] locs = locationsArray.split(COMMA);
                    Set<Location> ls = new HashSet<>();
                    for(String loc : locs){
                        if(StringUtils.isNotBlank(loc)){
                            ls.add(lMap.get(loc.trim()));
                        }
                    }
                    p.setLocations(ls);
                }
                if(tokens.length>19 && StringUtils.isNotBlank(tokens[19])) {
                    p.setStatus(stMap.get(tokens[19].toLowerCase().trim()));
                }
                if(tokens.length>20 &&StringUtils.isNotBlank(tokens[20])) {
                    p.setPhysicalStatus(psMap.get(tokens[20].toLowerCase().trim()));
                }
                if(tokens.length>21 &&StringUtils.isNotBlank(tokens[21])) {
                    if(tokens[21].indexOf("/")<0){
                        p.setPeriodPerformanceStart(formatter1.parse(tokens[21]));
                    } else{
                        p.setPeriodPerformanceStart(formatter2.parse(tokens[21]));
                    }
                }
                if(tokens.length>22 &&StringUtils.isNotBlank(tokens[22])) {
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

    @RequestMapping(value = "/loans", method = GET)
    public void importLoansFromCSV(@RequestParam(value = "filename", required = true) String filename) {
        LOGGER.debug("importLoansFromCSV");
        try {
            String line = "";
            //Create the file reader
            BufferedReader fileReader = new BufferedReader(new FileReader(filename));

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
            SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");

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

                if(StringUtils.isNotBlank(tokens[5])) {
                    p.setFundingAgency(faMap.get(tokens[5].toLowerCase().trim()));
                }
                if(StringUtils.isNotBlank(tokens[3])) {
                    String[] ias = tokens[3].split(COMMA);
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

                if(StringUtils.isNotBlank(tokens[4])) {
                    ExecutingAgency ea = eaMap.get(tokens[4].toLowerCase().trim());
                    p.setExecutingAgency(ea != null ? ea : eaDef);
                }

                /*if(StringUtils.isNotBlank(tokens[6])) {
                    Currency c = currMap.get(tokens[6].toLowerCase().trim());
                    p.setOriginalCurrency(c != null ? c : null);
                }
                if(StringUtils.isNotBlank(tokens[7])) {
                    p.setTotalProjectAmountOriginal(Double.parseDouble(tokens[7].replace(".", "").replace(COMMA, ".").replaceAll("[^0-9?!\\.]", "")));
                }*/
                if(tokens.length>35 && StringUtils.isNotBlank(tokens[35])) {
                    p.setTotalProjectAmount(Double.parseDouble(tokens[35].replace(".", "").replace(COMMA, ".")));
                }

                if(StringUtils.isNotBlank(tokens[14])) {
                    Loan loan = new Loan();
                    if(tokens[14].trim().equals("-") || tokens[14].trim().toLowerCase().equals("n/a")){
                        loan.setAmount(0);
                    } else {
                        loan.setAmount(Double.parseDouble(tokens[14].replace(".", "").replace(COMMA, ".")));
                    }
                    loan.setTransactionStatusId(2);
                    loan.setTransactionTypeId(2);
                    loan.setDate(formatter2.parse("12/31/2015"));
                    loan.setProject(p);
                    Set<Transaction> tSet = new HashSet<>();
                    tSet.add(loan);
                    p.setTransactions(tSet);
                }

                if(StringUtils.isNotBlank(tokens[6])) {
                    if(tokens[6].indexOf("/")<0){
                        p.setStartDate(formatter1.parse(tokens[6]));
                    } else{
                        p.setStartDate(formatter2.parse(tokens[6]));
                    }
                }
                if(StringUtils.isNotBlank(tokens[7])) {
                    if(tokens[7].indexOf("/")<0){
                        p.setEndDate(formatter1.parse(tokens[7]));
                    } else{
                        p.setEndDate(formatter2.parse(tokens[7]));
                    }
                }
                if(StringUtils.isNotBlank(tokens[8])) {
                    if(tokens[8].indexOf("/")<0){
                        p.setRevisedClosingDate(formatter1.parse(tokens[8]));
                    } else{
                        p.setRevisedClosingDate(formatter2.parse(tokens[8]));
                    }
                }
                if(StringUtils.isNotBlank(tokens[9])) {
                    Set<Sector> ss = new HashSet<>();
                    Sector s = sMap.get(tokens[9].toLowerCase().trim());
                    if(s!=null){
                        ss.add(s);
                        p.setSectors(ss);
                    }
                }
                String locationsArray = "";
                if(tokens.length>12 &&StringUtils.isNotBlank(tokens[12])) {
                    locationsArray = tokens[12];
                } else if(tokens.length>11 &&StringUtils.isNotBlank(tokens[11])) {
                    locationsArray = tokens[11];
                } else if (tokens.length>10 &&StringUtils.isNotBlank(tokens[10])) {
                    locationsArray = tokens[10];
                }
                if(StringUtils.isNotBlank(locationsArray)) {
                    String[] locs = locationsArray.split(COMMA);
                    Set<Location> ls = new HashSet<>();
                    for(String loc : locs){
                        if(StringUtils.isNotBlank(loc) && lMap.get(loc.trim())!=null){
                            ls.add(lMap.get(loc.trim()));
                        }
                    }
                    p.setLocations(ls);
                }
                if(tokens.length>32 && StringUtils.isNotBlank(tokens[32])) {
                    p.setStatus(stMap.get(tokens[32].toLowerCase().trim()));
                }
                if(tokens.length>38 &&StringUtils.isNotBlank(tokens[38])) {
                    p.setPhysicalStatus(psMap.get(tokens[38].toLowerCase().trim()));
                }
                if(tokens.length>33 &&StringUtils.isNotBlank(tokens[33])) {
                    if(tokens[33].indexOf("/")<0){
                        p.setPeriodPerformanceStart(formatter1.parse(tokens[33]));
                    } else{
                        p.setPeriodPerformanceStart(formatter2.parse(tokens[33]));
                    }
                }
                if(tokens.length>34 &&StringUtils.isNotBlank(tokens[34])) {
                    if(tokens[34].indexOf("/")<0){
                        p.setPeriodPerformanceEnd(formatter1.parse(tokens[34]));
                    } else{
                        p.setPeriodPerformanceEnd(formatter2.parse(tokens[34]));
                    }
                }
                int newFieldsHelp = 36;
                if(tokens.length>newFieldsHelp+1 && StringUtils.isNotBlank(tokens[newFieldsHelp])) {
                    p.setActualOwpa(Float.parseFloat(tokens[newFieldsHelp].replace(".", "").replace(COMMA, ".")));
                }
                newFieldsHelp = 37;
                if(tokens.length>newFieldsHelp+1 && StringUtils.isNotBlank(tokens[newFieldsHelp])) {
                    p.setTargetOwpa(Float.parseFloat(tokens[newFieldsHelp].replace(".", "").replace(COMMA, ".")));
                }
                newFieldsHelp = 39;
                if(tokens.length>newFieldsHelp+1 && StringUtils.isNotBlank(tokens[newFieldsHelp])) {
                    p.setIssueType(tokens[newFieldsHelp]);
                }
                newFieldsHelp = 40;
                if(tokens.length>newFieldsHelp+1 && StringUtils.isNotBlank(tokens[newFieldsHelp])) {
                    p.setIssueDetail(tokens[newFieldsHelp]);
                }
                newFieldsHelp = 41;
                if(tokens.length>newFieldsHelp+1 && StringUtils.isNotBlank(tokens[newFieldsHelp])) {
                    p.setActionTakenIa(tokens[newFieldsHelp]);
                }
                newFieldsHelp = 42;
                if(tokens.length>newFieldsHelp+1 && StringUtils.isNotBlank(tokens[newFieldsHelp])) {
                    p.setActionToBeTakenIa(tokens[newFieldsHelp]);
                }
                newFieldsHelp = 43;
                if(tokens.length>newFieldsHelp+1 && StringUtils.isNotBlank(tokens[newFieldsHelp])) {
                    p.setActionTakenNeda(tokens[newFieldsHelp]);
                }
                newFieldsHelp = 44;
                if(tokens.length>newFieldsHelp+1 && StringUtils.isNotBlank(tokens[newFieldsHelp])) {
                    p.setAccomplishmentUpdate(tokens[newFieldsHelp]);
                }
                newFieldsHelp = 45;
                if(tokens.length>newFieldsHelp+1 && StringUtils.isNotBlank(tokens[newFieldsHelp])) {
                    p.setNatureRre(tokens[newFieldsHelp]);
                }
                newFieldsHelp = 46;
                if(tokens.length>newFieldsHelp+1 && StringUtils.isNotBlank(tokens[newFieldsHelp])) {
                    p.setReasonRre(tokens[newFieldsHelp]);
                }
                newFieldsHelp = 47;
                if(tokens.length>newFieldsHelp+1 && StringUtils.isNotBlank(tokens[newFieldsHelp])) {
                    p.setLevelRre(tokens[newFieldsHelp]);
                }
                newFieldsHelp = 48;
                if(tokens.length>newFieldsHelp+1 && StringUtils.isNotBlank(tokens[newFieldsHelp])) {
                    p.setDateRre(tokens[newFieldsHelp]);
                }
                newFieldsHelp = 49;
                if(tokens.length>newFieldsHelp+1 && StringUtils.isNotBlank(tokens[newFieldsHelp])) {
                    p.setIccNbAction(tokens[newFieldsHelp]);
                }
                newFieldsHelp = 50;
                if(tokens.length>newFieldsHelp+1 && StringUtils.isNotBlank(tokens[newFieldsHelp])) {
                    p.setIccNbConditions(tokens[newFieldsHelp]);
                }
                newFieldsHelp = 51;
                if(tokens.length>newFieldsHelp+1 && StringUtils.isNotBlank(tokens[newFieldsHelp])) {
                    p.setComplianceToConditions(tokens[newFieldsHelp]);
                }


                projectService.save(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
