package com.kp.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by turgaycan on 9/26/15.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.kp.domain"})
@EnableJpaRepositories(basePackages = {"com.kp.repository"})
@EnableTransactionManagement
@PropertySource(value = "classpath:/application-test.properties")
public class RepositoryTestConfig {
}
