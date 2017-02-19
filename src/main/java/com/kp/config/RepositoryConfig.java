package com.kp.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by turgaycan on 9/20/15.
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.kp.repository")
@EntityScan(basePackages = "com.kp.domain")
public class RepositoryConfig {

    @Value("${spring.jpa.database}")
    private String databaseService;

    @Value("${spring.datasource.platform}")
    private String databasePlatform;

    @Value("${spring.jpa.show-sql}")
    private String showSql;

    @Value("${hibernate.format_sql}")
    private String formatSql;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String hibernateDialect;

    @Value("${spring.jpa.hibernate.naming-strategy}")
    private String namingStrategy;

    @Value("${spring.database.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.schema}")
    private String schema;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"com.kp.domain"});
        sessionFactory.setHibernateProperties(additionalProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(datasourceUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setConnectionProperties(additionalProperties());
        return dataSource;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(final SessionFactory sessionFactory) {
        final HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    Properties additionalProperties() {
        Properties properties = new Properties();

        properties.setProperty("hibernate.dialect", hibernateDialect);
        properties.setProperty("spring.jpa.database", databaseService);
        properties.setProperty("spring.jpa.show-sql", showSql);
        properties.setProperty("hibernate.format_sql", formatSql);
        properties.setProperty("spring.jpa.hibernate.naming-strategy", namingStrategy);
        properties.setProperty("spring.datasource.schema", schema);
        properties.setProperty("spring.datasource.platform", databasePlatform);
        properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");

        return properties;
    }

}
