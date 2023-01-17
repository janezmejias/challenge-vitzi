package com.lean.vitzi.application.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.listener.RetryListenerSupport;

/**
 * @author janez@vitzi.com
 * @version 2022-10-06
 */
@Configuration
public class CustomConfiguration {

    @Bean("customRetryListener")
    public RetryListenerSupport retryListeners() {
        return new RetryListenerSupport() {
            Logger logger = LoggerFactory.getLogger(getClass());

            @Override
            public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback,
                                                         Throwable throwable) {
                logger.warn("Retryable method {} threw {} exception {}",
                        context.getAttribute("context.name"),
                        context.getRetryCount(),
                        throwable.getMessage());
                super.onError(context,
                        callback,
                        throwable);
            }
        };
    }

    @Bean
    @Primary
    public ObjectMapper objectMapperPrimary() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return mapper;
    }

    @Bean
    @Primary
    public ObjectWriter objectWriterPrimary(ObjectMapper objectMapper) {
        return objectMapper.writer();
    }

    public ModelMapper mapper() {
        return new ModelMapper();
    }
}
