package com.chihuo.sleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StartSleuthServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(StartSleuthServerApplication.class, args);
	}
}
