package com.usuario.servicio.feingsclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usuario.servicio.dto.CarroDTO;

@FeignClient(name = "carro-servicio", url = "http://localhost:8002")
@RequestMapping("/carro")
public interface CarroFeignClient {

	@PostMapping
	public CarroDTO save(@RequestBody CarroDTO carro);
	
	@GetMapping("/usuario/{usuarioId}")
	public List<CarroDTO> getCars(@PathVariable("usuarioId") int usuarioId);

}
