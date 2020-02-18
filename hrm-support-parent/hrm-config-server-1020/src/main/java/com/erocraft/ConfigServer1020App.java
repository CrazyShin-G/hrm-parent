package com.erocraft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 14179
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class ConfigServer1020App {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServer1020App.class,args);
    }
}
