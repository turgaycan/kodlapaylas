package com.kp.config;

import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by turgaycan on 9/26/15.
 */

//@Configuration
//@EnableAutoConfiguration
//@DataJpaTest
@EntityScan(basePackageClasses = com.kp.domain.ArticleType.class)
@EnableJpaRepositories(repositoryBaseClass = com.kp.repository.ArticleTypeRepository.class)
//@EnableTransactionManagement
@PropertySource(value = "classpath:/application-test.properties")
//@ActiveProfiles("test")
public class RepositoryTestConfig {
}
