package com.project.incidentmanagement;

import java.util.Date;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.project.incidentmanagement.model.Person;
import com.project.incidentmanagement.service.MyService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@EnableConfigurationProperties
@ComponentScan(basePackages = "com.project") 
@SpringBootApplication
public class IncidentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncidentManagementApplication.class, args);

	}

	@Bean
	public CommandLineRunner init(final MyService myService) {

		return new CommandLineRunner() {
			public void run(String... strings) throws Exception {
				myService.createPersons();
			}
		};

	}
	
	public void add_person() {
		Person p1 = new Person("David Browser", new Date(01/01/1928));
		Person p2 = new Person("Tom Browser", new Date(01/01/1938));
		Person p3 = new Person("Sam Browser", new Date(01/01/1948));
	}

}
