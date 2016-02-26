package org.devgateway.geoph.deployer;
import org.devgateway.geoph.persistence.dao.GenericPersistable;
import org.devgateway.geoph.persistence.repository.RoleRepository;
import org.devgateway.geoph.persistence.spring.PersistenceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

/**
 * Created by Sebastian Dimunzio on 2/16/2016.
 */

@SpringBootApplication

@PropertySource("classpath:/org/devgateway/geoph/deployer/application.properties")
@ComponentScan("org.devgateway.geoph")
/*
@Import({
        PersistenceApplication.class

})*/

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
    }
}
