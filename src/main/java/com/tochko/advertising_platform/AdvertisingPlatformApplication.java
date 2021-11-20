package com.tochko.advertising_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//TODO security disable!!!
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AdvertisingPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvertisingPlatformApplication.class, args);
    }

}
