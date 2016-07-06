package org.devgateway.geoph.importer.spring;

import org.devgateway.geoph.enums.FlowTypeEnum;
import org.devgateway.geoph.importer.processing.GeophProjectsImporter;
import org.devgateway.geoph.persistence.spring.PersistenceApplication;
import org.devgateway.geoph.services.spring.ServicesApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author dbianco
 *         created on jul 01 2016.
 */
@SpringBootApplication
@Import({PersistenceApplication.class, ServicesApplication.class})
@EnableJpaRepositories("org.devgateway.geoph")
@ComponentScan("org.devgateway.geoph.importer")
@PropertySources({
        @PropertySource("classpath:/org/devgateway/geoph/importer/application.properties"),
        @PropertySource(value = "file:${CONF_FILE}", ignoreResourceNotFound = true)
})
public class ImporterApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImporterApplication.class);

    @Value("${import.type}")
    private String importType;

    public static void main(String[] args) {
        SpringApplication.run(ImporterApplication.class, args);
    }

    @Autowired
    @Qualifier("grantImporter")
    private GeophProjectsImporter grantImporter;

    @Autowired
    @Qualifier("loanImporter")
    private GeophProjectsImporter loanImporter;

    @Autowired
    private ConfigurableApplicationContext context;


    @Override
    public void run(String... strings) throws Exception {
        LOGGER.info("......... Running import now .........");
        if(importType.toLowerCase().equals(FlowTypeEnum.GRANT.getLCName())){
            grantImporter.importProjects();
        } else if (importType.toLowerCase().equals(FlowTypeEnum.LOAN.getLCName())){
            loanImporter.importProjects();
        }
        LOGGER.info("......... Import is done, please check the log .........");

        System.exit(SpringApplication.exit(context));
    }
}
