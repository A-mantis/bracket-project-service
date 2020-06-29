package com.mantis.brac;

import com.mantis.brac.common.annotation.EnableBracket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableBracket
public class BracProjectServiceApplication {

public static void main(String[] args) {
        SpringApplication.run(BracProjectServiceApplication.class, args);
    }

}
