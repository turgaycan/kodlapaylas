package com.kp.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by tcan on 21/02/17.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootApplication
@EntityScan(basePackages = {"com.kp.domain"})
@EnableJpaRepositories(basePackages = {"com.kp.repository"})
@EnableAutoConfiguration(exclude = {
        JpaRepositoriesAutoConfiguration.class,
        DataSourceAutoConfiguration.class
})
@DirtiesContext
@PropertySource(value = "classpath:/application-test.properties")
@ActiveProfiles("test")
@Transactional
public @interface RepositoryTest {
}
