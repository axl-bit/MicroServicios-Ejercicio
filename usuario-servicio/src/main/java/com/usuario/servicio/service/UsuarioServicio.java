package com.usuario.servicio.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.servicio.dto.CarroDTO;
import com.usuario.servicio.dto.MotoDTO;
import com.usuario.servicio.dto.UsuarioDTO;
import com.usuario.servicio.entity.Usuario;
import com.usuario.servicio.repository.UsuarioRepository;

@Service
public class UsuarioServicio {
	
	//Instanciamos el repositorio
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	//Instanciamos Resttemplate para la comuncicacion de los microservicios
	@Autowired
	private RestTemplate restTemplate;
	
	/*
	 * 
	 * Indice:
	 * 
	 * Conversores
	 * 
	 * converToUsuarioDTO => transforma de datos de usuario a usuarioDTO
	 * convertToUsuario => transforma de datos de usuarioDTO a usuario
	 * 
	 * Servicios
	 * 
	 * getAllUsers => obtener todos los usuarios de la base de datos
	 * getUserById => obtener un usuarios en especifico mediante el Id
	 * saveUser    => crear un usuario nuevo
	 * 
	 * Servicios RestTemplate
	 * 
	 * getCars() => obtener los carros 
	 * 
	 * */

	
	
	/*
	 * 
	 * Creamos los Conversores 
	 * 
	 * */
	
	private UsuarioDTO convertToUsuarioDTO(Usuario usuario) {
		
		//Instanciamos y asignamos los datos a UsuarioDTO
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(usuario.getId());
		usuarioDTO.setNombre(usuario.getNombre());
		usuarioDTO.setEmail(usuario.getEmail());
		
		//Retornamos el usuarioDTO
		return usuarioDTO;
	}
	
	private Usuario convertToUsuario(UsuarioDTO usuarioDTO) {
		
		//Instancemos y asignamos los datos a Usuario
		Usuario usuario = new Usuario();
		usuario.setId(usuarioDTO.getId());
		usuario.setNombre(usuarioDTO.getNombre());
		usuario.setEmail(usuarioDTO.getEmail());
		
		//Retornamos el Usuario
		return usuario;
	}
		
	/*
	 * 
	 * Creamos los Servicios
	 * 
	 * */
	
	

	/*
	 * Metodo getAllUsers
	 * 
	 * creamos una lista de Usuario y le asignamos los datos obtenidos de findAll()
	 * creamos una lista de UsuarioDTO y transformamos los datos de usuarios a usuariosDTO 
	 * retornamos la lista usuariosDTO
	 * 
	 * */
	public List<UsuarioDTO> getAllUsers(){
	 
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = usuarios.stream().map(this::convertToUsuarioDTO).collect(Collectors.toList());
		return usuariosDTO;
		
	}
	
	/*
	 * Metodo getUserById
	 * 
	 * creamos Usuario que se Optional y le asignamos los valores obtenidos por findById
	 * retornmaos los datos convertidos a UsuarioDTO o null en caso no se encuentren datos
	 * 
	 * */
	
	public UsuarioDTO getUserById(int id) {
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		return usuarioOptional.map(this::convertToUsuarioDTO).orElse(null) ;
	}
	
	/*
	 * Metodo saveUser
	 * 
	 * Creamos un Usuario y le asignamos los valores obtenidos de usuarioDTO (convertidos a Usuario)
	 * Creamos un usuarioGuardado y usando save guardamos el usuario
	 * retornamos los valores de usuarioGuardado convertidos a UsuarioDTO
	 * 
	 * */
	
	public UsuarioDTO saveUser(UsuarioDTO usuarioDTO) {
		
		Usuario usuario = convertToUsuario(usuarioDTO);		 
		Usuario usuarioGuardado = usuarioRepository.save(usuario);
		return convertToUsuarioDTO(usuarioGuardado);
		
	}
	
	/*
	 * 
	 * Creamos los Servicios RestTemplate
	 * 
	 * */
	
	/*
	 * Metodo getCars (Usando RestTemplate)
	 * 
	 * creamos una lista de carrosDTO y le asignamos los datos obtenidos 
	 * del restTemplate conectado con el servicio de carro esto usando el usuarioId
	 * retornamos la lista carrosDTO
	 * 
	 * */
	
	public List<CarroDTO> getCars(int usuarioId){
		
		List<CarroDTO> carrosDTO = restTemplate.exchange(
			    "http://localhost:8002/carro/usuario/" + usuarioId,
			    HttpMethod.GET,
			    null,
			    new ParameterizedTypeReference<List<CarroDTO>>() {}).getBody();

		return carrosDTO;
	}
	
	/*
	 * Metodo getMotos (Usando RestTemplate)
	 * 
	 * creamos una lista de motosDTO y le asignamos los datos obtenidos 
	 * del restTemplate conectado con el servicio de moto esto usando el usuarioId
	 * retornamos la lista motosDTO
	 * 
	 * */
	
	public List<MotoDTO> getMotos(int usuarioId){
		
		List<MotoDTO> motosDTO = restTemplate.exchange(
			    "http://localhost:8003/moto/usuario/" + usuarioId,
			    HttpMethod.GET,
			    null,
			    new ParameterizedTypeReference<List<MotoDTO>>() {}).getBody();
		return motosDTO;
	}
	
	
	
}