package com.fastcode.emailtest2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.fastcode.emailtest2","org.springframework.versions"})
public class Emailtest2Application {

	public static void main(String[] args) {
		SpringApplication.run(Emailtest2Application.class, args);
	}

}

