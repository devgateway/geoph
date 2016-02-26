package org.devgateway.geoph;
import org.devgateway.geoph.persistence.spring.PersistenceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.util.Arrays;

/**
 * Created by Sebastian Dimunzio on 2/16/2016.
 */

@SpringBootApplication

@Import({
        PersistenceApplication.class
})
public class Deployer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Deployer.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Deployer.class, args);

        LOGGER.info("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

        LOGGER.info("******GeoPH app started******");
        LOGGER.info("*****************************");
    }
}
