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
    DataSource dataSource;


    protected void boot() {

        runScript(
                "/sql/basic-data/locations.sql",
                "/sql/basic-data/location_items.sql",
                "/sql/basic-data/implementing_agency_types.sql",
                "/sql/basic-data/agencies.sql",
                "/sql/basic-data/currencies.sql",
                "/sql/basic-data/flow_type.sql",
                "/sql/basic-data/statuses.sql",
                "/sql/basic-data/transaction_type.sql",
                "/sql/basic-data/classifications.sql",
                "/sql/basic-data/sectors.sql",
                "/sql/basic-data/sector_items.sql",
                "/sql/basic-data/projects.sql",
                "/sql/basic-data/project_sectors.sql",
                "/sql/basic-data/project_locations.sql",
                "/sql/basic-data/transactions.sql"
                );

        //The princess is in another castle
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
            LOGGER.error(ignored.getMessage());
        }
    }
}
