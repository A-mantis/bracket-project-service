package com.mantis.brac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BracProjectServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BracProjectServiceApplication.class, args);
    }

}
