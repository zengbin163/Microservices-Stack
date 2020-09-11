package com.springcloud.client2lcn;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDistributedTransaction
public class Client2LcnApplication {

    public static void main(String[] args) {
        SpringApplication.run(Client2LcnApplication.class, args);
    }

}
