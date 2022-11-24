package com.qacg.dealers.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DealersApplication {

	public static void main(String[] args) {
		SpringApplication.run(DealersApplication.class, args);
	}

}
