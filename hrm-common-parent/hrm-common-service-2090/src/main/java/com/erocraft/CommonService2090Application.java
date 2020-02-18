package com.erocraft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 14179
 */
@SpringBootApplication
@EnableEurekaClient
public class CommonService2090Application {

	
	public static void main(String[] args) {
		
		SpringApplication.run(CommonService2090Application .class, args);
	}
}