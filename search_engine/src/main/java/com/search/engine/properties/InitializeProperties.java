package com.search.engine.properties;

import java.util.Properties;

import org.springframework.boot.SpringApplication;

import com.search.engine.SearchEngineApplication;

public class InitializeProperties {

	public static void initialize(String[] args) {
	SpringApplication application = new SpringApplication(SearchEngineApplication.class);
		
		Properties properties = new Properties();
		properties.setProperty("spring.main.banner-mode", "off");
		properties.setProperty("logging.pattern.console", "");
		application.setDefaultProperties(properties);
		application.run(args);
	}
}
