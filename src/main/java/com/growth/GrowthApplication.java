package com.growth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GrowthApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrowthApplication.class, args);
    }

}
