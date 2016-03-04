package org.devgateway.geoph;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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
    FlowTypeRepository fundingTypeRepository;

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


    protected void boot() {

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

        fundingTypeRepository.deleteAll();
        fundingTypeRepository.save(new FlowType("Loan"));
        fundingTypeRepository.save(new FlowType("Grant"));
        fundingTypeRepository.save(new FlowType("PMC"));

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
        sectorRepository.save(new Sector("AARNR", "Agriculture, Natural Resources and Agrarian Reform", null, 1));
        sectorRepository.save(new Sector("GID", "Governance and Institutions Development", null, 1));
        sectorRepository.save(new Sector("INF", "Infrastructure", null, 1));
        sectorRepository.save(new Sector("IS", "Industry, Trade and Tourism", null, 1));
        sectorRepository.save(new Sector("SRD", "Social Reform and Community Development", null, 1));

        Sector s1 = sectorRepository.findByCode("AARNR");
        s1.getItems().add(sectorRepository.save(new Sector("AAR", "Agriculture and Agrarian Reform", s1.getId(), 2)));
        s1.getItems().add(sectorRepository.save(new Sector("ENR", "Environment and Natural Resources", s1.getId(), 2)));
        s1.getItems().add(sectorRepository.save(new Sector("IRR", "Irrigation", s1.getId(), 2)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("GID");
        s1.getItems().add(sectorRepository.save(new Sector("AG", "Administrative Governance", s1.getId(), 2)));
        s1.getItems().add(sectorRepository.save(new Sector("EG", "Economic Governance", s1.getId(), 2)));
        s1.getItems().add(sectorRepository.save(new Sector("PG", "Political Governance", s1.getId(), 2)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("INF");
        s1.getItems().add(sectorRepository.save(new Sector("TRAN", "Transportation", s1.getId(), 2)));
        s1.getItems().add(sectorRepository.save(new Sector("WR", "Water Resources", s1.getId(), 2)));
        s1.getItems().add(sectorRepository.save(new Sector("EPE", "Energy, Power and Electrification", s1.getId(), 2)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("IS");
        s1.getItems().add(sectorRepository.save(new Sector("IST", "Industry, Trade and Tourism", s1.getId(), 2)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("SRD");
        s1.getItems().add(sectorRepository.save(new Sector("SWCD", "Social Welfare and Community Development", s1.getId(), 2)));
        s1.getItems().add(sectorRepository.save(new Sector("HPN", "Health, Population and Nutrition", s1.getId(), 2)));
        s1.getItems().add(sectorRepository.save(new Sector("SUD", "Shelter and Urban Development", s1.getId(), 2)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("AAR");
        s1.getItems().add(sectorRepository.save(new Sector("IRRZ", "Irrigation", s1.getId(), 3)));
        s1.getItems().add(sectorRepository.save(new Sector("AGRZ", "Agrarian Reform", s1.getId(), 3)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("ENR");
        s1.getItems().add(sectorRepository.save(new Sector("ENVZ", "Environment", s1.getId(), 3)));
        s1.getItems().add(sectorRepository.save(new Sector("FORZ", "Forestry", s1.getId(), 3)));
        s1.getItems().add(sectorRepository.save(new Sector("LANZ", "Land", s1.getId(), 3)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("EG");
        s1.getItems().add(sectorRepository.save(new Sector("GESZ", "General Social",s1.getId(), 3)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("PG");
        s1.getItems().add(sectorRepository.save(new Sector("DIMZ", "Disaster Mitigation",s1.getId(), 3)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("TRAN");
        s1.getItems().add(sectorRepository.save(new Sector("RABZ", "Roads and Bridges", s1.getId(), 3)));
        s1.getItems().add(sectorRepository.save(new Sector("AAAZ", "Airport and Airnavigation", s1.getId(), 3)));
        s1.getItems().add(sectorRepository.save(new Sector("RAIZ", "Rails", s1.getId(), 3)));
        s1.getItems().add(sectorRepository.save(new Sector("PORZ", "Ports", s1.getId(), 3)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("WR");
        s1.getItems().add(sectorRepository.save(new Sector("FLCZ", "Flood Control", s1.getId(), 3)));
        s1.getItems().add(sectorRepository.save(new Sector("WSSZ", "Water Supply, Sewerage and Sanitation", s1.getId(), 3)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("EPE");
        s1.getItems().add(sectorRepository.save(new Sector("PGTZ", "Power Generation and Transmission", s1.getId(), 3)));
        s1.getItems().add(sectorRepository.save(new Sector("GEDZ", "Geothermal Exploration and Development", s1.getId(), 3)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("IS");
        s1.getItems().add(sectorRepository.save(new Sector("RELZ", "Relending", s1.getId(), 3)));
        sectorRepository.save(s1);

        s1 = sectorRepository.findByCode("SUD");
        s1.getItems().add(sectorRepository.save(new Sector("SOIZ", "Social Infrastructure", s1.getId(), 3)));
        sectorRepository.save(s1);

        locationRepository.deleteAll();
        locationRepository.save(new Location("Region I - Ilocos", null, 1, "1", 16.9087797110D, 120.4868698D));

        Location l1 = locationRepository.findByCode("1");
        locationRepository.save(new Location("Ilocos Norte", 1L, 1, "128", 18.1998273575D, 120.7309813D));
        locationRepository.save(new Location("Ilocos Sur", 1L, 1, "129", 17.2212386812D, 120.5516706D));
        locationRepository.save(new Location("La Union", 1L, 1, "133", 16.5810538295D,	120.4277635D));

        l1 = locationRepository.findByCode("128");
        locationRepository.save(new Location("Adams", 1L, 1, "12801", 18.4498697122D, 120.9212904D));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        projectRepository.deleteAll();
        try {
            projectRepository.save(new Project(
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
                    trxTypeRepository.findByName("Target"),
                    classificationRepository.findByName("GOP-implemented").get(0),
                    new HashSet<Location>(Arrays.asList(locationRepository.findByCode("12801"))),
                    new HashSet<Sector>(Arrays.asList(sectorRepository.findByCode("AGRZ")))));
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
