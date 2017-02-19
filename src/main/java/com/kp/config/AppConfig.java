package com.kp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by turgaycan on 9/20/15.
 */
@Configuration
@ComponentScan(basePackages = {
        "com.kp.service", "com.kp.exception", "com.kp.util", "com.kp.validator"
})
public class AppConfig {
}