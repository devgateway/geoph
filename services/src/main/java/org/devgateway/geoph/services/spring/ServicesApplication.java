package org.devgateway.geoph.services.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @author dbianco
 *         created on jul 01 2016.
 */
@SpringBootApplication
@ComponentScan("org.devgateway.geoph.services")
@PropertySource("classpath:/org/devgateway/geoph/services/application.properties")
public class ServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicesApplication.class, args);
    }

}
