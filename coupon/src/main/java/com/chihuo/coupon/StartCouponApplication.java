package com.chihuo.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient // 表示是eureka的客户端
@EnableDiscoveryClient
public class StartCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartCouponApplication.class, args);
    }

}
