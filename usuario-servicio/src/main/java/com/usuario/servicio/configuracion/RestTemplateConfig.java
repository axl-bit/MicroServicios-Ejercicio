package com.usuario.servicio.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
	/*
	 * 
	 * creando la instancia de restemplate para la comunicacion entre servicios
	 * 
	 * */

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
