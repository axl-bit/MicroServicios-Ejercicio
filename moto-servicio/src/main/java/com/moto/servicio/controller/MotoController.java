package com.moto.servicio.controller;

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

import com.moto.servicio.dto.MotoDTO;
import com.moto.servicio.service.MotoServicio;

@RestController
@RequestMapping("/moto")
public class MotoController {

	@Autowired
	private MotoServicio motoServicio;

	/*
	 * Indice de MotoController
	 * 
	 * 1.- getAllMotos => Obtener listado de todas las motos.
	 * 2.- getMotoById => Obtener los datos de una moto mediante su Id. 
	 * 3.- saveMoto => Crear una Moto nueva.
	 * 4.- ListMotoForUsers => Listar las motos mediante el id del usuario.
	 * 
	 */

	@GetMapping
	private ResponseEntity<?> getAllMotos() {

		try {

			List<MotoDTO> motoDTO = motoServicio.getAllMotos();
			
			if(motoDTO.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
			return new ResponseEntity<>(motoDTO, HttpStatus.OK);
			
		} catch (Exception e) {

			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("error", "hubo un error al intentar obtener el listado de motos: " + e);

			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getMotoById(@PathVariable int id){
		
		try {
			
			MotoDTO motoDTO = motoServicio.getMotoById(id);
			
			if(motoDTO == null) {
				
				Map<String, String> respuesta = new HashMap<>();
				respuesta.put("not_found", "Moto no existe");
				return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
				
			}
			
			return new ResponseEntity<>(motoDTO, HttpStatus.OK);
			
			
		} catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("error", "Hubo un error al intentar obtener la moto: " + e);
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	@PostMapping
	public ResponseEntity<?> saveMoto(@RequestBody MotoDTO motoDTO){
		
		try {
			
			MotoDTO motoDTOSave = motoServicio.saveMoto(motoDTO);
			return new ResponseEntity<>(motoDTOSave, HttpStatus.CREATED);
			
			
		} catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("error", "Hubo un error al intentar crear la moto: " + e);
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<?> findCarByUserId(@PathVariable("usuarioId") int id){
		
		try {
			List<MotoDTO> motoDTO = motoServicio.findMotoByUserId(id);
			
			if(motoDTO.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
			return new ResponseEntity<>(motoDTO,HttpStatus.OK);
		} catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("error", "hubo un error al intentar obtener el listado de motos por usuario: " + e);
			
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	
}
