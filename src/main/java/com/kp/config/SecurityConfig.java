package com.kp.config;

import com.kp.auth.CustomAuthenticationManager;
import com.kp.domain.model.Role;
import com.kp.service.auth.KpAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by turgaycan on 9/20/15.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/static/**", "/WEB-INF/static/**");
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.parentAuthenticationManager(customAuthenticationManager());
    }

    @Bean
    public CustomAuthenticationManager customAuthenticationManager() {
        return new CustomAuthenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();

        http.authorizeRequests()
                .antMatchers(
                        "/",
                        "/403",
                        "/404",
                        "/500",
                        "/generatedResources/**",
                        "/resources/**",
                        "/login/**",
                        "/register/**",
                        "/**/**"
                )
                .permitAll();

        http.authorizeRequests()
                .regexMatchers(
                        "/user/.*/.*"
                )
                .permitAll();

        http.authorizeRequests()
                .antMatchers("/user/**").hasAuthority(Role.USER.name())
                .antMatchers("/admin/**").hasAuthority(Role.ADMIN.name())
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .passwordParameter("password")
                .usernameParameter("username")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/")
                .permitAll();

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID")
                .permitAll();

        http.csrf().disable();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
