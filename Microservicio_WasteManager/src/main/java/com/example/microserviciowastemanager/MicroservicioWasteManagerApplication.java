package com.example.microserviciowastemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroservicioWasteManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioWasteManagerApplication.class, args);
	}

}
