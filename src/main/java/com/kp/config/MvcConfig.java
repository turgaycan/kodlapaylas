package com.kp.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

/**
 * Created by turgaycan on 9/20/15.
 */

@EnableWebMvc
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.kp.controller", "com.kp.handler"})
public class MvcConfig extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {

    private static final int CACHE_PERIOD = 24 * 60 * 60 * 1000;

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

//    @Bean
//    public CustomHandlerAdapter customHandlerAdapter() {
//        return new CustomHandlerAdapter();
//    }
//
//    @Bean
//    public CustomBaseHandlerMapping customBaseHandlerMapping() {
//        return new CustomBaseHandlerMapping();
//    }

//    @Bean
//    public ControllerClassNameHandlerMapping controllerClassNameHandlerMapping(){
//        ControllerClassNameHandlerMapping controllerClassNameHandlerMapping = new ControllerClassNameHandlerMapping();
//        controllerClassNameHandlerMapping.setBasePackage("com.kp.handler");
//        controllerClassNameHandlerMapping.setOrder(-1);
//        return controllerClassNameHandlerMapping;
//    }

//    @Bean
//    public BeanNameUrlHandlerMapping beanNameUrlHandlerMapping(){
//        return new BeanNameUrlHandlerMapping();
//    }

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

//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }

    @Bean
    public InternalResourceViewResolver templateResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public SimpleUrlHandlerMapping simpleServletMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Integer.MAX_VALUE - 2);

        Properties urlProperties = new Properties();
        urlProperties.put("/index", "indexController");
        urlProperties.put("/", "indexController");
        mapping.setMappings(urlProperties);

        return mapping;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("messages/messages.properties", "messages/validation.properties", "messages/labels.properties");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}