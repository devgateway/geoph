package org.devgateway.geoph.deployer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * Created by Sebastian Dimunzio on 2/16/2016.
 */

@SpringBootApplication
@PropertySources({
        @PropertySource("file:${CONF_PATH}/application.properties")
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
        LOGGER.info("Deploying GeoPH using profile: {}", deployProfile);
        if (deployProfile == DeployProfile.DEV) {
            BootMetadata bootMetadata = ctx.getBean(BootMetadata.class);
            bootMetadata.boot();
        }

        LOGGER.info("******GeoPH app started******");
        LOGGER.info("*****************************");
    }
}
