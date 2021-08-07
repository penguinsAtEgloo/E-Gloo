package com.project.egloo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class EglooApplication {

	public static void main(String[] args) {
		SpringApplication.run(EglooApplication.class, args);
	}

}
