package com.chihuo.food;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;

@SpringBootApplication
@EnableTransactionManagement
@EnableDistributedTransaction
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableHystrix
@EnableFeignClients
@EnableDiscoveryClient
public class StartFoodApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartFoodApplication.class, args);
    }
}
