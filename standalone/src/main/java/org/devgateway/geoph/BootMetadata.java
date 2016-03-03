package org.devgateway.geoph;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    FundingTypeRepository fundingTypeRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    TransactionTypeRepository trxTypeRepository;


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

        agencyRepository.save(new ImplementingAgency("DPWH", "DPWH", iatRepository.findByTypeId("NGA")));
        agencyRepository.save(new ImplementingAgency("DA", "DA", iatRepository.findByTypeId("NGA")));
        agencyRepository.save(new ImplementingAgency("NIA", "NIA", iatRepository.findByTypeId("NGA")));
        agencyRepository.save(new ImplementingAgency("LBP", "LBP", iatRepository.findByTypeId("GFI")));
        agencyRepository.save(new ImplementingAgency("MWSS", "DPWH", iatRepository.findByTypeId("GOCC")));

        currencyRepository.deleteAll();
        currencyRepository.save(new Currency("Philippine peso", "PHP"));
        currencyRepository.save(new Currency("United States dollar", "USD"));
        currencyRepository.save(new Currency("Euro", "EUR"));
        currencyRepository.save(new Currency("Japanese yen", "JPY"));

        fundingTypeRepository.deleteAll();
        fundingTypeRepository.save(new FundingType("Loan"));
        fundingTypeRepository.save(new FundingType("Grant"));
        fundingTypeRepository.save(new FundingType("PMC"));

        statusRepository.deleteAll();
        statusRepository.save(new Status("OL", "Ongoing"));
        statusRepository.save(new Status("CL", "Closed"));
        statusRepository.save(new Status("NEL", "Newly Effective"));
        statusRepository.save(new Status("NSL", "Newly Signed"));

        trxTypeRepository.deleteAll();
        trxTypeRepository.save(new TransactionType("Target"));
        trxTypeRepository.save(new TransactionType("Actual"));
        trxTypeRepository.save(new TransactionType("Cancelled"));
    }
}
