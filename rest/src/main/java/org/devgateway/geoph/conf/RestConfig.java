package org.devgateway.geoph.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.devgateway.geoph.generators.GenericFilterKeyGenerator;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author idobre
 * @since 16/08/2017
 */
@Configuration
public class RestConfig {
    @Bean(name = "genericFilterKeyGenerator")
    public KeyGenerator genericFilterKeyGenerator(final ObjectMapper objectMapper) {
        return new GenericFilterKeyGenerator(objectMapper);
    }
}
