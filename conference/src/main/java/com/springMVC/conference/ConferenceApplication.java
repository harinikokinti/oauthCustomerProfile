package com.springMVC.conference;
/*
Spring Boot application by extending SpringBootServletInitializer, it creates a dispatcher servlet and start serving up things.
And as part of that annotation of @SpringBootApplication, it goes and starts looking for the controllers(Greeting and Regirstration) and the
annotations such as @Controller and @GetMapping.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ConferenceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(ConferenceApplication.class, args);
	}

}
