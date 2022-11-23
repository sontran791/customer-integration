package com.branch.customer_integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CustomerIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerIntegrationApplication.class, args);
    }

}
