package org.devgateway.geoph.services.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @author dbianco
 *         created on jul 01 2016.
 */
@SpringBootApplication
@ComponentScan("org.devgateway.geoph.services")
@PropertySources({
        @PropertySource("classpath:/org/devgateway/geoph/services/application.properties"),
        @PropertySource(value = "file:${CONF_FILE}", ignoreResourceNotFound = true)
})
public class ServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicesApplication.class, args);
    }

}
