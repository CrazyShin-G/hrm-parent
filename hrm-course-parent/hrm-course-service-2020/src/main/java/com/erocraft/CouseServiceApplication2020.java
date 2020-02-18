package com.erocraft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 14179
 */
@SpringBootApplication
@EnableEurekaClient
public class CouseServiceApplication2020 {
    public static void main(String[] args) {
        SpringApplication.run(CouseServiceApplication2020.class,args);
    }
}
