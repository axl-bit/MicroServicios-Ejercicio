package com.usuario.servicio.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuario.servicio.dto.UsuarioDTO;
import com.usuario.servicio.entity.Usuario;
import com.usuario.servicio.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	/*
	 * 
	 * Creamos los conversores de UsuarioDTO y Usuario
	 * 
	 * */
	
	private UsuarioDTO convertToUsuarioDTO(Usuario usuario) {
		
		//Instanciamos y asignamos los datos a UsuarioDTO
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNombre(usuario.getNombre());
		usuarioDTO.setEmail(usuario.getEmail());
		
		//Retornamos el usuarioDTO
		return usuarioDTO;
	}
	
	private Usuario convertToUsuario(UsuarioDTO usuarioDTO) {
		
		//Instancemos y asignamos los datos a Usuario
		Usuario usuario = new Usuario();
		usuario.setNombre(usuarioDTO.getNombre());
		usuario.setEmail(usuarioDTO.getEmail());
		
		//Retornamos el Usuario
		return usuario;
	}
	
	/*
	 * 
	 * Abajo se encuentran los siguientes servicios
	 * 
	 * getAllUsers -> obtener todos los usuarios de la base de datos
	 * getUserById -> obtener un usuarios en especifico mediante el Id
	 * saveUser    ->    crear un usuario nuevo
	 * 
	 * */
	

	/*
	 * Metodo el metodo getAllUsers
	 * 
	 * primero obtenemos los valores de la base de datos usando el metodo del repositorio JPA
	 * Creamos una lista vacia de UsuariosDTO, esta para guardar los datos de retorno
	 * usando un fort convertimos los datos obtenidos a usuarioDTO
	 * luego lo añadimos a la lista de usuariosDTO
	 * por ultimo retornamos el listado de usuariosDTO
	 * 
	 * */
	public List<UsuarioDTO> getAllUsers(){
	 
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = new ArrayList<>();
		for(Usuario usuario: usuarios) { usuariosDTO.add(convertToUsuarioDTO(usuario));}
		return usuariosDTO;
		
	}
	
	/*
	 * Metodo get UserById
	 * 
	 * Generamos un usuario usuarioOptional y le asiganmos los valores 
	 * obtenidos por el metodo del repositorio JPA findbyId
	 * luego retornamos los datos mapeado un objeto UsuarioDTO
	 * si el objeto existe se devolvera el objeto y en caso no exista se retornara null
	 * 
	 * */
	
	public UsuarioDTO getUserById(int id) {
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		return usuarioOptional.map(this::convertToUsuarioDTO).orElse(null) ;
	}
	
	/*
	 * Metodo el metodo saveUser
	 * 
	 * primero instanciamos Usuario y convertimos los datos obtenido de UsuarioDTO
	 * luego se guardaran los datos usando el repositorio JPA
	 * y por ultimo retornaremos los datos convertidos a DTO.
	 * 
	 * */
	
	public UsuarioDTO saveUser(UsuarioDTO usuarioDTO) {
		
		Usuario usuario = convertToUsuario(usuarioDTO);		 
		Usuario usuarioGuardado = usuarioRepository.save(usuario);
		return convertToUsuarioDTO(usuarioGuardado);
		
	}
}