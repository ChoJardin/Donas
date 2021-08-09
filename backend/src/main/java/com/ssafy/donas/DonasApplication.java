package com.ssafy.donas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan("com.ssafy.donas.domain")
public class DonasApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonasApplication.class, args);
	}

}
