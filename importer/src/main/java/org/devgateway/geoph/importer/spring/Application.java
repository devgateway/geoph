package org.devgateway.geoph.importer.spring;

import org.devgateway.geoph.persistence.spring.PersistenceApplication;
import org.devgateway.geoph.services.spring.ServicesApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

/**
 * Created by sebas on 8/31/2016.
 */



@Import({PersistenceApplication.class, ServicesApplication.class})
@EnableJpaRepositories("org.devgateway.geoph")
@SpringBootApplication(exclude = {EmbeddedServletContainerAutoConfiguration.class,
        WebMvcAutoConfiguration.class})




@ComponentScan("org.devgateway.geoph.importer")

@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource(value = "file:${CONF_FILE}", ignoreResourceNotFound = true)
})
@Component
public class Application  implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectImport.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    ProjectImport projectImport;

    @Autowired
    KMLImport kmlImport;


    @Value("${type}")
    private String type;


    @Override
    public void run(String... strings) throws Exception {
        if ("kml".equalsIgnoreCase(type)){
            LOGGER.info("Starting KML import ");
            kmlImport.run(strings);
        }else{
            LOGGER.info("Starting project import ");
            projectImport.run(strings);
        }


    }
}





















