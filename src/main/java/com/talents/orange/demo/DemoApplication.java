package com.talents.orange.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@SpringBootApplication
public class DemoApplication implements RepositoryRestConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
