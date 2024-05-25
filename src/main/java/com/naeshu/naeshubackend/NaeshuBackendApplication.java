package com.naeshu.naeshubackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class NaeshuBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NaeshuBackendApplication.class, args);
    }

}
