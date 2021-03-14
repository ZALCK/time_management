package com.esmt.timeManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({ @PropertySource("classpath:db.properties") })
public class TimeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeManagementApplication.class, args);
	}

}
