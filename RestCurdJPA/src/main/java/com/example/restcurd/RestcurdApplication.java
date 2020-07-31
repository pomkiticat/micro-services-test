package com.example.restcurd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class RestcurdApplication {

    public static void main(String[] args) {
        System.out.println("hi");
        SpringApplication.run(RestcurdApplication.class, args);
    }

}
