package com.ssafy.donas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.ssafy.donas.service.FcmService;

@SpringBootApplication
@EntityScan("com.ssafy.donas.domain")
public class DonasApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonasApplication.class, args);
		
	}

}
