package com.chihuo.uid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableEurekaClient // 表示是eureka的客户端
@EnableDiscoveryClient
@ImportResource(locations = "classpath:spring.xml")
public class StartUidApplication {
	public static void main(String[] args) {
		SpringApplication.run(StartUidApplication.class, args);
	}
}
