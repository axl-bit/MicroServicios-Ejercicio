package com.usuario.servicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//esta notacion habilita el cliente eureka
@EnableEurekaClient
@SpringBootApplication
public class CarroServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarroServicioApplication.class, args);
	}

}
