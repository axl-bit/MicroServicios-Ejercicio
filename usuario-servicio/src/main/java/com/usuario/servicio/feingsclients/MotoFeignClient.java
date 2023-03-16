package com.usuario.servicio.feingsclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usuario.servicio.dto.MotoDTO;

@FeignClient(name = "moto-servicio", url = "http://localhost:8003")
@RequestMapping("/moto")
public interface MotoFeignClient {
	
	@PostMapping
	public MotoDTO save(@RequestBody MotoDTO motoDTO);

}
