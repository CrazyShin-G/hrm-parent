package com.erocraft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 14179
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class PageServiceApplication2030 {
    public static void main(String[] args) {
        SpringApplication.run(PageServiceApplication2030.class,args);
    }
}
