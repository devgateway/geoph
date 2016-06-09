package org.devgateway.geoph;

import org.devgateway.geoph.persistence.repository.LocationRepository;
import org.devgateway.geoph.persistence.spring.PersistenceApplication;
import org.devgateway.geoph.services.LocationService;
import org.devgateway.geoph.services.exporter.Export;
import org.devgateway.geoph.util.PropsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * Created by Sebastian Dimunzio on 2/16/2016.
 */

@SpringBootApplication
@Configuration
@PropertySources({
        @PropertySource("classpath:/org/devgateway/geoph/application.properties"),
        @PropertySource(value = "file:${CONF_FILE}", ignoreResourceNotFound = true)
})
@Import({
        PersistenceApplication.class
})
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


     public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class, args);

        LOGGER.info("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

        Environment environment = ctx.getBean(Environment.class);
        DeployProfile deployProfile = environment.getProperty("geoph.deploy.profile", DeployProfile.class, DeployProfile.DEV);
        PropsHelper.setScreenCaptureDir(environment.getProperty("screen.capture.dir", "/tmp/"));
        PropsHelper.setScreenCaptureTimeToWait(Long.parseLong(environment.getProperty("screen.capture.waiting.time", "25000")));
        PropsHelper.setScreenFirefoxExe(environment.getProperty("screen.firefox.binary"));
        PropsHelper.setExportDir(environment.getProperty("export.dir"));
        LOGGER.info("Deploying GeoPH using profile: {}", deployProfile);
        if(deployProfile == DeployProfile.DEV) {
            BootMetadata bootMetadata = ctx.getBean(BootMetadata.class);
            bootMetadata.boot();
        }



        LOGGER.info("******GeoPH app started******");
        LOGGER.info("*****************************");
    }
}
