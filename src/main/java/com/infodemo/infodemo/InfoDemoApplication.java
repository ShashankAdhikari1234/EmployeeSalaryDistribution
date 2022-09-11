package com.infodemo.infodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InfoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfoDemoApplication.class, args);
	}

}
