package com.jurisitsm.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.jurisitsm.test.model")
public class UsedCarApplication {
    public static void main(String[] args) {
        SpringApplication.run(UsedCarApplication.class, args);
    }

}
