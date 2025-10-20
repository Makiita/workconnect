package com.workconnect.workconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.workconnect")
@EntityScan(basePackages = "com.workconnect.model")
public class WorkconnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkconnectApplication.class, args);
	}

}
