package com.kp.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by tcan on 03/01/16.
 */

@Configuration
@PropertySource(ignoreResourceNotFound = true,
        value = {"classpath:application.properties", "classpath:/messages/labels.properties",
                "classpath:/messages/messages.properties", "classpath:/messages/validation.properties",
                "classpath:/messages/seo.properties"})
public class PropertyConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }

    @Bean(name = "seoProperty")
    public PropertiesFactoryBean seoProperty() {
        PropertiesFactoryBean seoPropertiesBean = new PropertiesFactoryBean();
        seoPropertiesBean.setLocation(new ClassPathResource("messages/seo.properties"));

        return seoPropertiesBean;
    }

}