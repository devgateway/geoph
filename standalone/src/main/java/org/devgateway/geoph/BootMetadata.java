package org.devgateway.geoph;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author dbianco
 *         created on mar 03 2016.
 */
@Component
@Transactional
public class BootMetadata {

    private static final Logger LOGGER = LoggerFactory.getLogger(BootMetadata.class);

    @Autowired
    ImplementingAgencyTypeRepository iatRepository;

    @Autowired
    AgencyRepository agencyRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    FlowTypeRepository flowTypeRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    TransactionTypeRepository trxTypeRepository;

    @Autowired
    ClassificationRepository classificationRepository;

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    DataSource dataSource;


    protected void boot() {

        runScript(
                "/sql/basic-data/locations.sql",
                "/sql/basic-data/location_items.sql");

        iatRepository.deleteAll();
        iatRepository.save(new ImplementingAgencyType("GFI", "Government Financing Institution"));
        iatRepository.save(new ImplementingAgencyType("GOCC", "Government Owned and Controlled Corporations"));
        iatRepository.save(new ImplementingAgencyType("LGU", "Local Government Unit"));
        iatRepository.save(new ImplementingAgencyType("NGA", "National Government Agency"));

        agencyRepository.deleteAll();
        agencyRepository.save(new FundingAgency("Asian Development Bank", "ADB", "ADB"));
        agencyRepository.save(new FundingAgency("Germany", "Germany", "Others"));
        agencyRepository.save(new FundingAgency("Japan International Cooperation Agency", "GOJ/JICA", "GOJ/JICA"));
        agencyRepository.save(new FundingAgency("World Bank", "WB", "WB"));

        agencyRepository.save(new ImplementingAgency("DPWH", "DPWH", iatRepository.findByCode("NGA")));
        agencyRepository.save(new ImplementingAgency("DA", "DA", iatRepository.findByCode("NGA")));
        agencyRepository.save(new ImplementingAgency("NIA", "NIA", iatRepository.findByCode("NGA")));
        agencyRepository.save(new ImplementingAgency("LBP", "LBP", iatRepository.findByCode("GFI")));
        agencyRepository.save(new ImplementingAgency("MWSS", "DPWH", iatRepository.findByCode("GOCC")));

        currencyRepository.deleteAll();
        currencyRepository.save(new Currency("Philippine peso", "PHP"));
        currencyRepository.save(new Currency("United States dollar", "USD"));
        currencyRepository.save(new Currency("Euro", "EUR"));
        currencyRepository.save(new Currency("Japanese yen", "JPY"));

        flowTypeRepository.deleteAll();
        flowTypeRepository.save(new FlowType("Loan"));
        flowTypeRepository.save(new FlowType("Grant"));
        flowTypeRepository.save(new FlowType("PMC"));

        statusRepository.deleteAll();
        statusRepository.save(new Status("OL", "Ongoing"));
        statusRepository.save(new Status("CL", "Closed"));
        statusRepository.save(new Status("NEL", "Newly Effective"));
        statusRepository.save(new Status("NSL", "Newly Signed"));

        trxTypeRepository.deleteAll();
        trxTypeRepository.save(new TransactionType("Target"));
        trxTypeRepository.save(new TransactionType("Actual"));
        trxTypeRepository.save(new TransactionType("Cancelled"));

        classificationRepository.deleteAll();
        classificationRepository.save(new Classification("GOP-implemented"));

        sectorRepository.deleteAll();
        sectorRepository.save(new Sector("AARNR", "Agriculture, Natural Resources and Agrarian Reform", 1));
        sectorRepository.save(new Sector("GID", "Governance and Institutions Development", 1));
        sectorRepository.save(new Sector("INF", "Infrastructure", 1));
        sectorRepository.save(new Sector("IS", "Industry, Trade and Tourism", 1));
        sectorRepository.save(new Sector("SRD", "Social Reform and Community Development", 1));

        Sector s1 = sectorRepository.findByCode("AARNR");
        s1.getItems().add(sectorRepository.save(new Sector("AAR", "Agriculture and Agrarian Reform", 2)));
        s1.getItems().add(sectorRepository.save(new Sector("ENR", "Environment and Natural Resources", 2)));
        s1.getItems().add(sectorRepository.save(new Sector("IRR", "Irrigation", 2)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("GID");
        s1.getItems().add(sectorRepository.save(new Sector("AG", "Administrative Governance", 2)));
        s1.getItems().add(sectorRepository.save(new Sector("EG", "Economic Governance", 2)));
        s1.getItems().add(sectorRepository.save(new Sector("PG", "Political Governance", 2)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("INF");
        s1.getItems().add(sectorRepository.save(new Sector("TRAN", "Transportation", 2)));
        s1.getItems().add(sectorRepository.save(new Sector("WR", "Water Resources", 2)));
        s1.getItems().add(sectorRepository.save(new Sector("EPE", "Energy, Power and Electrification", 2)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("IS");
        s1.getItems().add(sectorRepository.save(new Sector("IST", "Industry, Trade and Tourism", 2)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("SRD");
        s1.getItems().add(sectorRepository.save(new Sector("SWCD", "Social Welfare and Community Development", 2)));
        s1.getItems().add(sectorRepository.save(new Sector("HPN", "Health, Population and Nutrition", 2)));
        s1.getItems().add(sectorRepository.save(new Sector("SUD", "Shelter and Urban Development", 2)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("AAR");
        s1.getItems().add(sectorRepository.save(new Sector("IRRZ", "Irrigation", 3)));
        s1.getItems().add(sectorRepository.save(new Sector("AGRZ", "Agrarian Reform", 3)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("ENR");
        s1.getItems().add(sectorRepository.save(new Sector("ENVZ", "Environment", 3)));
        s1.getItems().add(sectorRepository.save(new Sector("FORZ", "Forestry", 3)));
        s1.getItems().add(sectorRepository.save(new Sector("LANZ", "Land", 3)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("EG");
        s1.getItems().add(sectorRepository.save(new Sector("GESZ", "General Social", 3)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("PG");
        s1.getItems().add(sectorRepository.save(new Sector("DIMZ", "Disaster Mitigation", 3)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("TRAN");
        s1.getItems().add(sectorRepository.save(new Sector("RABZ", "Roads and Bridges", 3)));
        s1.getItems().add(sectorRepository.save(new Sector("AAAZ", "Airport and Airnavigation", 3)));
        s1.getItems().add(sectorRepository.save(new Sector("RAIZ", "Rails", 3)));
        s1.getItems().add(sectorRepository.save(new Sector("PORZ", "Ports", 3)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("WR");
        s1.getItems().add(sectorRepository.save(new Sector("FLCZ", "Flood Control", 3)));
        s1.getItems().add(sectorRepository.save(new Sector("WSSZ", "Water Supply, Sewerage and Sanitation", 3)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("EPE");
        s1.getItems().add(sectorRepository.save(new Sector("PGTZ", "Power Generation and Transmission", 3)));
        s1.getItems().add(sectorRepository.save(new Sector("GEDZ", "Geothermal Exploration and Development", 3)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("IS");
        s1.getItems().add(sectorRepository.save(new Sector("RELZ", "Relending", 3)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("SUD");
        s1.getItems().add(sectorRepository.save(new Sector("SOIZ", "Social Infrastructure", 3)));
        sectorRepository.save(s1);


        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        projectRepository.deleteAll();
        transactionRepository.deleteAll();
        TransactionType targetTrxType = trxTypeRepository.findByName("Target");
        FlowType grantFlowType = flowTypeRepository.findByName("Grant").get(0);
        try {
            Project p1 = projectRepository.save(new Project(
                    "The Project for the Bridge Construction for Expanded Agrarian Reform Communities Development, Phase 2（Umiray Bridge)",
                    agencyRepository.findByCode("DA"),
                    "",
                    null,
                    agencyRepository.findByCode("WB"),
                    currencyRepository.findByCode("PHP"),
                    13600000D,
                    sdf.parse("4/5/2012"),
                    sdf.parse("31/7/2015"),
                    sdf.parse("31/7/2015"),
                    statusRepository.findByCode("CL"),
                    sdf.parse("4/5/2012"),
                    sdf.parse("31/7/2015"),
                    targetTrxType,
                    classificationRepository.findByName("GOP-implemented").get(0),
                    new HashSet<Location>(Arrays.asList(locationRepository.findByCode("12801"))),
                    new HashSet<Sector>(Arrays.asList(sectorRepository.findByCode("AGRZ")))));

            transactionRepository.save(new Grant(p1, 7390000D, sdf.parse("30/6/2014"),
                    grantFlowType,
                    targetTrxType));
            transactionRepository.save(new Grant(p1, 4560000D, sdf.parse("30/12/2014"),
                    grantFlowType,
                    targetTrxType));

        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void runScript(String... scriptList) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setContinueOnError(false);
        populator.setIgnoreFailedDrops(false);
        for(String script: scriptList) {
            populator.addScript(new ClassPathResource(script));
        }

        try {
            populator.populate(dataSource.getConnection());
        } catch (SQLException ignored) {
        }
    }
}
