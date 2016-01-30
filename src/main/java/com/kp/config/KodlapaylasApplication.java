package com.kp.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {
        PropertyConfig.class,
        AppConfig.class,
        MvcConfig.class,
        AppConfig.class,
        RepositoryConfig.class,
        SecurityConfig.class,
        CacheConfig.class
})
public class KodlapaylasApplication {
}
