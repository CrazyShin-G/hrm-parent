package com.erocraft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 14179
 */
@SpringBootApplication
@EnableEurekaClient
public class SysManage2010Application {

	
	public static void main(String[] args) {
		
		SpringApplication.run(SysManage2010Application .class, args);
	}
}