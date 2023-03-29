package com.gateway.servicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServicioApplication.class, args);
	}

}
