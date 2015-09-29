package com.kp;

import com.kp.config.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {MvcConfig.class, RepositoryConfig.class,
        SecurityConfig.class, AppConfig.class,
        CacheConfig.class})
public class KodlapaylasApplication {

    public static void main(String[] args) {
        SpringApplication.run(KodlapaylasApplication.class);
    }
}
