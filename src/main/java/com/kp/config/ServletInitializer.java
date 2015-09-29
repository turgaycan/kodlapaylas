package com.kp.config;

import com.kp.KodlapaylasApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(KodlapaylasApplication.class).web(true)
				.logStartupInfo(true).showBanner(true);
	}

}
