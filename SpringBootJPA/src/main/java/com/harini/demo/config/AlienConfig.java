package com.harini.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AlienConfig {
	
	// create Bean for Rest Template to auto wire the Rest Template object.
	
	@Autowired
	@Bean
	public RestTemplate countryRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		
		return restTemplateBuilder.build();

	}

}
