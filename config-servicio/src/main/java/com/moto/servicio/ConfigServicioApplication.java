package com.moto.servicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer //habilita el servidor de configuracion y permita obtener la info del repo
public class ConfigServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServicioApplication.class, args);
	}

}
