package com.usuario.servicio.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.servicio.dto.UsuarioDTO;
import com.usuario.servicio.servicio.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<?> getUsers(){
		try {
			
			List<UsuarioDTO> usuariosDTO = usuarioService.getAllUsers();
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
			
			UsuarioDTO usuarioDTO = usuarioService.getUserById(id);
			
			if(usuarioDTO == null) {
				
				Map<String, String> respuesta = new HashMap<>();
				respuesta.put("not_found", "Usuario no existe");
				return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
				
			}
			return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("error", "Hubo un error al intentar obtner el usuario: " + e);
			return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
			
		}	
		
	}
}
