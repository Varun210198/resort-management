package com.example.vvs.resort_service_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ResortServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResortServiceRegistryApplication.class, args);
	}

}
