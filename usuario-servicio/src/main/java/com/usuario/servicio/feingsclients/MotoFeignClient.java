package com.usuario.servicio.feingsclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usuario.servicio.dto.MotoDTO;

@FeignClient(name = "moto-servicio")
@RequestMapping("/moto")
public interface MotoFeignClient {
	
	@PostMapping
	public MotoDTO save(@RequestBody MotoDTO motoDTO);
	
	@GetMapping("usuario/{usuarioId}")
	public List<MotoDTO> getMotos(@PathVariable("usuarioId") int usuarioId);

}
