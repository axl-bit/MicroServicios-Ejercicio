package com.usuario.servicio.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.servicio.dto.CarroDTO;
import com.usuario.servicio.dto.MotoDTO;
import com.usuario.servicio.dto.UsuarioDTO;
import com.usuario.servicio.service.UsuarioServicio;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	
	/*
	 * Indice de UsuarioController
	 * 
	 * 1.- getAllUsers => Obtener listado de todos los usuarios
	 * 2.- getUserById => Obtener los datos de un usuario mediante su Id
	 * 3.- saveUser => Crear un Usuario nuevo 
	 * 5.- getUsersCars => Obtener los carros de un usuario, llamandolo de carro-servicio (RestTemplate) (Circuit-Breaker)
	 * 6.- getUsersMotos => Obtener las motos de un usuario, llamandola de moto-servicio (RestTemplate) (Circuit-Breaker)
	 * 7.- saveCar => Crear un carro nuevo, comunicandose con carro-servicio (FeignClient) (Circuit-Breaker)
	 * 8.- saveMoto => Crear una moto nueva, comunicandose son moto-servicio (FeingClient) (Circuit-Breaker)
	 * 9.- getUservehicles => Obtener todos los vehiculos de un usuario (FeignClient) (Circuit-Breaker)
	 * 
	 * metodos fallback para los circuit breakers
	 * 
	 * 1.- fallBackGetUsersCars
	 * 2.- fallBackGetUsersMotos
	 * 3.- fallBackSaveCar
	 * 4.- fallBackSaveMoto
	 * 5.- fallBackGetUservehicles
	 * 
	 * */
	
	@GetMapping
	public ResponseEntity<?> getAllUsers(){
		try {
			
			List<UsuarioDTO> usuariosDTO = usuarioServicio.getAllUsers();
			
			if(usuariosDTO.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
			return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("error", "hubo un error al intentar obtener el listado de usuarios: " + e);
			
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable int id){
		
		try {
			
			UsuarioDTO usuarioDTO = usuarioServicio.getUserById(id);
			
			if(usuarioDTO == null) {
				
				Map<String, String> respuesta = new HashMap<>();
				respuesta.put("not_found", "Usuario no existe");
				return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
				
			}
			return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("error", "Hubo un error al intentar obtener el usuario: " + e);
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}	
		
	}
	
	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody UsuarioDTO usuarioDTO){
		try {
			
			UsuarioDTO usuarioDTOSave = usuarioServicio.saveUser(usuarioDTO);
			return new ResponseEntity<>(usuarioDTOSave, HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("error", "Hubo un error al intentar crear el usuario: " + e);
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	@CircuitBreaker(name= "carrosCB", fallbackMethod = "fallBackGetUsersCars")
	@GetMapping("/carros/{usuarioId}")
	public ResponseEntity<?> getUsersCars(@PathVariable("usuarioId") int id) {

		UsuarioDTO usuarioDTO = usuarioServicio.getUserById(id);

		if (usuarioDTO == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		List<CarroDTO> carrosDTO = usuarioServicio.getCars(id);

		return new ResponseEntity<>(carrosDTO, HttpStatus.OK);

	}
	
	@CircuitBreaker(name= "motosCB", fallbackMethod = "fallBackGetUsersMotos")
	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<?> getUsersMotos(@PathVariable("usuarioId") int id) {

		UsuarioDTO usuarioDTO = usuarioServicio.getUserById(id);

		if (usuarioDTO == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		List<MotoDTO> motosDTO = usuarioServicio.getMotos(id);

		return new ResponseEntity<>(motosDTO, HttpStatus.OK);

	}
	
	@CircuitBreaker(name= "carrosCB", fallbackMethod = "fallBackSaveCar")
	@PostMapping("/carro/{usuarioId}")
	public ResponseEntity<?> saveCar(@PathVariable("usuarioId") int usuarioId, @RequestBody CarroDTO carroDTO) {

		CarroDTO carroDTONuevo = usuarioServicio.saveCar(usuarioId, carroDTO);

		return new ResponseEntity<>(carroDTONuevo, HttpStatus.OK);

	}
	
	@CircuitBreaker(name= "motosCB", fallbackMethod = "fallBackSaveMoto")
	@PostMapping("/moto/{usuarioId}")
	public ResponseEntity<?> saveMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody MotoDTO motoDTO) {

		MotoDTO motoDTONuevo = usuarioServicio.saveMoto(usuarioId, motoDTO);

		return new ResponseEntity<>(motoDTONuevo, HttpStatus.OK);

	}
	
	@CircuitBreaker(name = "todosCB", fallbackMethod = "fallBackGetUservehicles")
	@GetMapping("/todos/{usuarioId}")
	public ResponseEntity<?> getUservehicles(@PathVariable("usuarioId") int usuarioId) {

		Map<String, Object> resultado = usuarioServicio.getUservehicles(usuarioId);
		return new ResponseEntity<>(resultado, HttpStatus.OK);

	}
	
	/*
	 * 
	 * Creando los metodos fallback de circuit breaker
	 * 
	 */ 
	
	private ResponseEntity<?> fallBackGetUsersCars(@PathVariable("usuarioId") int id, RuntimeException exception){
		return new ResponseEntity<>("El usuario: " + id + " tiene los carros en el taller",HttpStatus.OK);
	}
	
	private ResponseEntity<?> fallBackGetUsersMotos(@PathVariable("usuarioId") int id, RuntimeException exception){
		return new ResponseEntity<>("El usuario: " + id + " tiene las motos en el taller",HttpStatus.OK);
	}
	
	private ResponseEntity<?> fallBackSaveCar(@PathVariable("usuarioId") int id, @RequestBody CarroDTO carroDTO, RuntimeException exception){
		return new ResponseEntity<>("El usuario: " + id + " no tiene dinero para los carros",HttpStatus.OK);
	}
	
	private ResponseEntity<?> fallBackSaveMoto(@PathVariable("usuarioId") int id, @RequestBody MotoDTO motoDTO, RuntimeException exception){
		return new ResponseEntity<>("El usuario: " + id + " no tiene dinero para las motos",HttpStatus.OK);
	}
	
	private ResponseEntity<?> fallBackGetUservehicles(@PathVariable("usuarioId") int id, RuntimeException exception){
		return new ResponseEntity<>("El usuario: " + id + " tiene los vehiculos en el taller",HttpStatus.OK);
	}
}
