package org.devgateway.geoph;

import org.devgateway.geoph.core.services.SecurityService;
import org.devgateway.geoph.model.security.GrantedAuthority;
import org.devgateway.geoph.model.security.SystemUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import static java.lang.Math.random;
import static java.lang.System.currentTimeMillis;
import static java.util.concurrent.TimeUnit.DAYS;
import static org.devgateway.geoph.core.constants.Constants.PASS_ENCODE;

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
                "/sql/basic-data/flowType.sql",
                "/sql/basic-data/statuses.sql",
                "/sql/basic-data/physical_statuses.sql",
                "/sql/basic-data/transaction_type.sql",
                "/sql/basic-data/transaction_status.sql",
                "/sql/basic-data/classifications.sql",
                "/sql/basic-data/grant_sub_type.sql",
                "/sql/basic-data/sectors.sql",
                "/sql/basic-data/climate_change.sql",
                "/sql/basic-data/gender_responsiveness.sql",
                "/sql/basic-data/sector_items.sql",
                "/sql/basic-data/projects2.sql",
                "/sql/basic-data/project_agencies2.sql",
                "/sql/basic-data/project_sectors2.sql",
                "/sql/basic-data/project_locations2.sql",
                "/sql/basic-data/project_climate_change2.sql",
                "/sql/basic-data/project_gender_responsiveness2.sql",
                "/sql/basic-data/transactions2.sql",
                "/sql/basic-data/geo_photo.sql",
                "/sql/basic-data/geo_photo_urls.sql",
                "/sql/basic-data/indicator.sql",
                "/sql/basic-data/indicator_detail.sql",
                "/sql/basic-data/hibernate_sequence.sql",
                "/sql/basic-data/add_geometry_to_location.sql"
        );

        //The princess is in another castle

        GrantedAuthority readPermission = securityService.saveGrantedAuthority(
                new GrantedAuthority("Login, Search and View Layers", "READ"));

        GrantedAuthority admin = securityService.saveGrantedAuthority(
                new GrantedAuthority("Admin, Search and View Layers", "ADMIN"));

        List<GrantedAuthority> commonAuthorities = new ArrayList<>();
        commonAuthorities.add(readPermission);
        commonAuthorities.add(admin);

        SystemUser geophUser = new SystemUser();
        geophUser.setEmail("admin");
        geophUser.setName("Admin");
        geophUser.setLastName("Geoph");
        geophUser.setPassword(encoder.encode("hello123"));
        geophUser.setEnabled(true);
        geophUser.setCreated(new Date(currentTimeMillis() - DAYS.toMillis((long) (random() * 60))));
        geophUser.setAccountNonExpired(true);
        geophUser.setAccountNonLocked(true);
        geophUser.setCredentialsNonExpired(true);
        geophUser.setAuthorities(commonAuthorities);
        securityService.savePerson(geophUser);

    }

    private void runScript(String... scriptList) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setContinueOnError(false);
        populator.setIgnoreFailedDrops(false);
        for (String script : scriptList) {
            populator.addScript(new ClassPathResource(script));
        }

        try {
            populator.populate(dataSource.getConnection());
        } catch (SQLException ignored) {
            LOGGER.error(ignored.getMessage());
        }
    }
}
