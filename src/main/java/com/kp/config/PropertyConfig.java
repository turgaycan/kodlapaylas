package com.kp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by tcan on 03/01/16.
 */
@Configuration
@PropertySource(ignoreResourceNotFound = true,
        value = {"classpath:application.properties", "classpath:/messages/labels.properties",
                "classpath:/messages/messages.properties", "classpath:/messages/validation.properties"})
public class PropertyConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }

}