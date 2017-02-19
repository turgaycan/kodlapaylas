package com.kp.config;

import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

/**
 * Created by tcan on 12/02/17.
 */
public class MvcApplicationInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter());
        characterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        characterEncodingFilter.setInitParameter("encoding", "UTF-8");
        characterEncodingFilter.setInitParameter("forceEncoding", "true");

        FilterRegistration.Dynamic openSessionInViewFilter = servletContext.addFilter("openSessionInViewFilter", new OpenSessionInViewFilter());
        openSessionInViewFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        servletContext.setInitParameter("defaultHtmlEscape", "true");
    }
}
