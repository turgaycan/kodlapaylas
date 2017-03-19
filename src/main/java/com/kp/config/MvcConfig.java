package com.kp.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.annotation.WebServlet;
import java.nio.charset.Charset;

/**
 * Created by turgaycan on 9/20/15.
 */

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.kp.controller", "com.kp.handler"})
public class MvcConfig extends WebMvcConfigurerAdapter {

    private static final int CACHE_PERIOD = 24 * 60 * 60 * 1000;

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true).favorParameter(true).parameterName("media-type")
                .ignoreAcceptHeader(false).useJaf(false)
                .defaultContentType(MediaType.ALL).mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet ds = new DispatcherServlet();
        ds.setThrowExceptionIfNoHandlerFound(true);
        return ds;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**", "/resources/static/**",
                "/resources/templates/**", "/resources/messages/**",
                "/WEB-INF/static/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/", "/resources/static/",
                        "/resources/templates/", "/resources/messages/",
                        "/WEB-INF/static/")
                .setCachePeriod(0);
    }

    @Bean
    public InternalResourceViewResolver templateResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setContentType("*/*; charset=UTF-8");
        return resolver;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("messages/messages.properties", "messages/validation.properties", "messages/labels.properties");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }


    @Bean
    @Primary
    public Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }
}