package com.erocraft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 14179
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka1010App {
    public static void main(String[] args) {
        SpringApplication.run(Eureka1010App.class,args);
    }
}
