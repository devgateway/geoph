package org.devgateway.geoph.generators;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author idobre
 * @since 16/08/2017
 *
 * {@link KeyGenerator} that uses method name + parameters to create a key.
 * Usually the first parameter is a filter object or a {@link Map} object.
 */
public class GenericFilterKeyGenerator implements KeyGenerator {
    private final Logger logger = LoggerFactory.getLogger(GenericFilterKeyGenerator.class);

    private final ObjectMapper objectMapper;

    public GenericFilterKeyGenerator(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Object generate(final Object target, final Method method, final Object... params) {
        final StringBuilder key = new StringBuilder(method.toString());

        if (params.length > 0) {
            try {
                for (Object param : params) {
                    key.append(objectMapper.writeValueAsString(param));
                }
            } catch (JsonProcessingException e) {
                logger.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }

        return key.toString().hashCode();
    }
}
