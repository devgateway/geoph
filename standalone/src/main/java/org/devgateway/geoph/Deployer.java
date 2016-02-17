package org.devgateway.geoph;
import org.devgateway.geoph.persistence.spring.PersistenceApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

/**
 * Created by Sebastian Dimunzio on 2/16/2016.
 */

@SpringBootApplication

@Import({
        PersistenceApplication.class

})

public class Deployer {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Deployer.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
}
