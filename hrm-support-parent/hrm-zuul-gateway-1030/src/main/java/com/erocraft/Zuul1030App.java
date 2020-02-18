package com.erocraft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author 14179
 */
@SpringBootApplication
@EnableZuulProxy
public class Zuul1030App {
    public static void main(String[] args) {
        SpringApplication.run(Zuul1030App.class,args);
    }
}
