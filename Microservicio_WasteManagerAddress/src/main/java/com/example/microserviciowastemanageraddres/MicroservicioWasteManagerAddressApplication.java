package com.example.microserviciowastemanageraddres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroservicioWasteManagerAddressApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioWasteManagerAddressApplication.class, args);
	}

}
