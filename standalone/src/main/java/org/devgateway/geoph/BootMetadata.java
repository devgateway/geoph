package org.devgateway.geoph;

import org.devgateway.geoph.model.security.GrantedAuthority;
import org.devgateway.geoph.model.security.SystemUser;
import org.devgateway.geoph.services.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.devgateway.geoph.util.Constants.PASS_ENCODE;
import static java.lang.Math.random;
import static java.lang.System.currentTimeMillis;
import static java.util.concurrent.TimeUnit.DAYS;

/**
 * @author dbianco
 *         created on mar 03 2016.
 */
@Component
@Transactional
public class BootMetadata {

    private static final Logger LOGGER = LoggerFactory.getLogger(BootMetadata.class);

    PasswordEncoder encoder = new StandardPasswordEncoder(PASS_ENCODE);

    @Autowired
    DataSource dataSource;

    @Autowired
    SecurityService securityService;



    protected void boot() {

        runScript(
                "/sql/basic-data/locations.sql",
                "/sql/basic-data/location_items.sql",
                "/sql/basic-data/implementing_agency_types.sql",
                "/sql/basic-data/agencies.sql",
                "/sql/basic-data/currencies.sql",
                "/sql/basic-data/flow_type.sql",
                "/sql/basic-data/statuses.sql",
                "/sql/basic-data/physical_statuses.sql",
                "/sql/basic-data/transaction_type.sql",
                "/sql/basic-data/transaction_status.sql",
                "/sql/basic-data/classifications.sql",
                "/sql/basic-data/sectors.sql",
                "/sql/basic-data/climate_change.sql",
                "/sql/basic-data/gender_responsiveness.sql",
                "/sql/basic-data/sector_items.sql",
                "/sql/basic-data/projects.sql",
                "/sql/basic-data/project_agencies.sql",
                "/sql/basic-data/project_sectors.sql",
                "/sql/basic-data/project_locations.sql",
                "/sql/basic-data/project_climate_change.sql",
                "/sql/basic-data/project_gender_responsiveness.sql",
                "/sql/basic-data/transactions.sql",
                "/sql/basic-data/indicator.sql",
                "/sql/basic-data/indicator_detail.sql",
                "/sql/basic-data/geophoto_source.sql",
                "/sql/basic-data/geophoto_geometry.sql"
                );

        //The princess is in another castle

        GrantedAuthority readPermission = securityService.saveGrantedAuthority(
                new GrantedAuthority("Login, Search and View Layers", "READ" ));
        List<GrantedAuthority> commonAuthorities = new ArrayList<>();
        commonAuthorities.add(readPermission);

        SystemUser geophUser = new SystemUser();
        geophUser.setEmail("admin@geoph.org");
        geophUser.setName("Admin");
        geophUser.setLastName("Geoph");
        geophUser.setPassword(encoder.encode("hello123"));
        geophUser.setEnabled(true);
        geophUser.setCreated(new Date(currentTimeMillis() - DAYS.toMillis((long) (random() * 60))));
        geophUser.setAccountNonExpired(true);
        geophUser.setAccountNonLocked(true);
        geophUser.setCredentialsNonExpired(true);
        geophUser.setAuthorities(commonAuthorities);
        geophUser = securityService.savePerson(geophUser);
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
