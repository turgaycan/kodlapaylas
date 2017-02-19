package com.kp.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {
        PropertyConfig.class,
        CouchbaseConfiguration.class,
        AppConfig.class,
        MvcConfig.class,
        AppConfig.class,
        RepositoryConfig.class,
        SecurityConfig.class
})
public class KodlapaylasApplication {
}
