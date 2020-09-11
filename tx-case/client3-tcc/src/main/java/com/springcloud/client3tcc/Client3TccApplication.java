package com.springcloud.client3tcc;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDistributedTransaction
public class Client3TccApplication {

    public static void main(String[] args) {
        SpringApplication.run(Client3TccApplication.class, args);
    }

}
