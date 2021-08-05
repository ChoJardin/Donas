package com.ssafy.donas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.ssafy.donas.domain")
public class DonasApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonasApplication.class, args);
	}

}
