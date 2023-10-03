package com.example.microserviciowastemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
public class MicroservicioWasteManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioWasteManagerApplication.class, args);
	}

}
