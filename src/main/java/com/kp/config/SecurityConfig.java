//package com.kp.config;
//
//import com.kp.domain.model.Role;
//import com.kp.service.auth.KpAuthenticationProvider;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.access.expression.SecurityExpressionOperations;
//import org.springframework.security.access.expression.SecurityExpressionRoot;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
//
///**
// * Created by turgaycan on 9/20/15.
// */
//@Configuration
//@EnableWebMvcSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);
//
//    @Autowired
//    private KpAuthenticationProvider kpAuthenticationProvider;
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/resources/static/**", "/WEB-INF/static/**");
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(kpAuthenticationProvider);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/**").permitAll()
//                .antMatchers("/user/**").hasAuthority(Role.USER.name())
//                .antMatchers("/admin/**").hasAuthority(Role.ADMIN.name())
//                .antMatchers("/resources/**").permitAll()
//                .anyRequest().fullyAuthenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/login?error")
//                .defaultSuccessUrl("/user/")
//                .usernameParameter("email")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .deleteCookies("remember-me")
//                .logoutSuccessUrl("/")
//                .permitAll()
//                .and()
//                .rememberMe();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
