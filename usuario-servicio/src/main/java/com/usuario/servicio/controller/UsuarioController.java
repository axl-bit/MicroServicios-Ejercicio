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
	 * 5.- getUsersCars => Obtener los carros de un usuario, llamandolo de carro-servicio (RestTemplate)
	 * 6.- getUsersMotos => Obtener las motos de un usuario, llamandola de moto-servicio (RestTemplate)
	 * 7.- saveCar => Crear un carro nuevo, comunicandose con carro-servicio (FeignClient)
	 * 8.- saveMoto => Crear una moto nueva, comunicandose son moto-servicio (FeingClient)
	 * 9.- getUservehicles => Obtener todos los vehiculos de un usuario (FeignClient)
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
	
	@GetMapping("/carros/{usuarioId}")
	public ResponseEntity<?> getUsersCars(@PathVariable("usuarioId") int id){
		
		try {
			
			UsuarioDTO usuarioDTO = usuarioServicio.getUserById(id);
			
			if(usuarioDTO == null)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
			List<CarroDTO> carrosDTO = usuarioServicio.getCars(id);
			
			return new ResponseEntity<>(carrosDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("error", "Hubo un error al intentar listar los carros del usuario (RestTemplate): " + e);
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<?> getUsersMotos(@PathVariable("usuarioId") int id){
		
		try {
			
			UsuarioDTO usuarioDTO = usuarioServicio.getUserById(id);
			
			if(usuarioDTO == null)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
			List<MotoDTO> motosDTO = usuarioServicio.getMotos(id);
			
			return new ResponseEntity<>(motosDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("error", "Hubo un error al intentar listar las motos del usuario (RestTemplate): " + e);
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	@PostMapping("/carro/{usuarioId}")
	public ResponseEntity<?> saveCar(@PathVariable("usuarioId") int usuarioId, @RequestBody CarroDTO carroDTO){
		
		try {
			
			CarroDTO carroDTONuevo = usuarioServicio.saveCar(usuarioId, carroDTO);
			
			return new ResponseEntity<>(carroDTONuevo, HttpStatus.OK);
			
		} catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("error", "Hubo un error al intentar crear el carro usando FeignClient: " + e);
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	@PostMapping("/moto/{usuarioId}")
	public ResponseEntity<?> saveMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody MotoDTO motoDTO){
		
		try {
			
			MotoDTO motoDTONuevo = usuarioServicio.saveMoto(usuarioId, motoDTO);
			
			return new ResponseEntity<>(motoDTONuevo, HttpStatus.OK);
			
		} catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("error", "Hubo un error al intentar crear la moto usando FeignClient: " + e);
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	@GetMapping("/todos/{usuarioId}")
	public ResponseEntity<?> getUservehicles(@PathVariable("usuarioId") int usuarioId){
		
		try {
			
			Map<String , Object> resultado = usuarioServicio.getUservehicles(usuarioId);
			return new ResponseEntity<>(resultado, HttpStatus.OK);
			
		} catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("error", "Hubo un error al intentar listar todos los vehiculos del usuario usando FeignClient: " + e);
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	
}
