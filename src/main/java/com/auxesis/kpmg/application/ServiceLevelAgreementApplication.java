package com.auxesis.kpmg.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.auxesis.kpmg.entity")
@EnableJpaRepositories(basePackages = "com.auxesis.kpmg.repository")
@SpringBootApplication(scanBasePackages = { "com.auxesis.kpmg" })
public class ServiceLevelAgreementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceLevelAgreementApplication.class, args);
	}
}
