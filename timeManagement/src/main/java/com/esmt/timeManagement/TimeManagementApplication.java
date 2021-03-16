package com.esmt.timeManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@ComponentScan
@PropertySources({ @PropertySource("classpath:db.properties") })

public class TimeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeManagementApplication.class, args);
	}

}
