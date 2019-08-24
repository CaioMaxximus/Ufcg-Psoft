package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@ComponentScan(basePackages = "com.example.Controllers")
@SpringBootApplication
public class HelloWordApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(HelloWordApplication.class, args);
	}

}
