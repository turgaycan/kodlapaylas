package com.kp.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {AppConfig.class, MvcConfig.class,
//        AppConfig.class,
        RepositoryConfig.class,
//        SecurityConfig.class,

        CacheConfig.class})
public class KodlapaylasApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(KodlapaylasApplication.class);
//    }
}
