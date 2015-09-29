package com.kp.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by turgaycan on 9/20/15.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.kp.controller"})
public class MvcConfig extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {

    private static final int CACHE_PERIOD = 24 * 60 * 60 * 1000;

    @Bean
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet ds = new DispatcherServlet();
        ds.setThrowExceptionIfNoHandlerFound(true);
        return ds;
    }

    /**
     * Register dispatcherServlet programmatically
     *
     * @return ServletRegistrationBean
     */
//    @Bean
//    public ServletRegistrationBean dispatcherServletRegistration() {
//
//        ServletRegistrationBean registration = new ServletRegistrationBean(
//                dispatcherServlet(), "/**");
//
//        registration
//                .setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
//
//        return registration;
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

    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public InternalResourceViewResolver templateResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping();
    }

    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        return new RequestMappingHandlerAdapter();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("essages/messages.properties", "messages/validation.properties", "messages/labels.properties");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(userDetailInterceptor());
        super.addInterceptors(registry);
    }

    public UserDetailInterceptor userDetailInterceptor() {
        return new UserDetailInterceptor();
    }

    /**
     * An intereptor that pushes the current user UserDetails object into the request as an attribute
     * named 'currentUser'.
     *
     * @author Mark Meany
     */
    protected class UserDetailInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
            final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                if (!(auth instanceof AnonymousAuthenticationToken)) {
                    if (auth.getPrincipal() != null) {
                        request.setAttribute("currentUser", auth.getPrincipal());
                    }
                }
            }
            return super.preHandle(request, response, handler);
        }
    }

}