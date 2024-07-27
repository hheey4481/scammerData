package com.example.scammerdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScammerDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScammerDataApplication.class, args);
	}

}
