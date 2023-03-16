package com.usuario.servicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//esta notacion habilita el cliente feign y denota que este servicio es el cliente feign
@EnableFeignClients 
@SpringBootApplication
public class UsuarioServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuarioServicioApplication.class, args);
	}
	
}
