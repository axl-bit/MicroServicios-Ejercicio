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
import com.usuario.servicio.service.CarroServicio;

@RestController
@RequestMapping("/carro")
public class CarroController {
	
	@Autowired
	private CarroServicio carroServicio;
	
	
	/*
	 * Indice de CarroController
	 * 
	 * 1.- getAllCars => Obtener listado de todos los carros
	 * 2.- getCarById => Obtener los datos de un carro mediante su Id
	 * 3.- saveCar => Crear un Carro nuevo
	 * 4.- ListCarsForUsers => listar los carros mediante el id del usuario
	 * 
	 * */
	
	@GetMapping
	public ResponseEntity<?> getAllCars(){
		try {
			
			List<CarroDTO> carroDTO = carroServicio.getAllCars();
			
			if(carroDTO.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
			return new ResponseEntity<>(carroDTO,HttpStatus.OK);
			
		} catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("error", "hubo un error al intentar obtener el listado de carros: " + e);
			
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCarById(@PathVariable int id){
		
		try {
			
			CarroDTO carroDTO = carroServicio.getCarById(id);
			
			if(carroDTO == null) {
				
				Map<String, String> respuesta = new HashMap<>();
				respuesta.put("not_found", "Carro no existe");
				return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(carroDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("error", "Hubo un error al intentar obtener el carro: " + e);
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	@PostMapping
	public ResponseEntity<?> saveCar(@RequestBody CarroDTO carroDTO){
		
		try {
			
			CarroDTO carroDTOSave = carroServicio.saveCar(carroDTO);
			return new ResponseEntity<>(carroDTOSave, HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("error", "Hubo un error al intentar crear el carro: " + e);
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<?> findCarByUserId(@PathVariable("usuarioId") int id){
		
		try {
			List<CarroDTO> carroDTO = carroServicio.findCarByUserId(id);
			
			if(carroDTO.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
			return new ResponseEntity<>(carroDTO,HttpStatus.OK);
		} catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("error", "hubo un error al intentar obtener el listado de carros por usuario: " + e);
			
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	

}
