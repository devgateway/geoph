package org.devgateway.geoph.importer.spring;

import org.devgateway.geoph.persistence.spring.PersistenceApplication;
import org.devgateway.geoph.services.spring.ServicesApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by sebas on 8/30/2016.
 */

@SpringBootApplication
@Import({PersistenceApplication.class, ServicesApplication.class})
@EnableJpaRepositories("org.devgateway.geoph")
@ComponentScan("org.devgateway.geoph.importer")
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource(value = "file:${CONF_FILE}", ignoreResourceNotFound = true)
})

public class KMLImport {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImporterApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KMLImport.class, args);
    }

    public void run(String... strings) throws Exception {
        LOGGER.info("......... Running KML now .........");

    }
}
