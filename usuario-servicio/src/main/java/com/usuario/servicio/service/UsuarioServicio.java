package com.usuario.servicio.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.usuario.servicio.feingsclients.CarroFeignClient;
import com.usuario.servicio.feingsclients.MotoFeignClient;
import com.usuario.servicio.repository.UsuarioRepository;

@Service
public class UsuarioServicio {
	
	//Injectamos el repositorio
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	//Injectamos RestTemplate para la comuncicacion de los microservicios
	@Autowired
	private RestTemplate restTemplate;
	
	//Injectamos la interfaz de carro FeignClient
	@Autowired
	private CarroFeignClient carroFeignClient;
	
	//Injectamos la interfaz de moto FeignClient
	@Autowired
	private MotoFeignClient motoFeignClient;
	
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
	 * getCars() => obtener los carros de carro-servicio
	 * getMotos() => obtener las motos de moto-servicio
	 * 
	 * Servicios FeignClient
	 * 
	 * saveCars() => crear un carro nuevo comunicandoce con carro-servicio
	 * saveMotos() => crear una moto nueva comunicandoce con moto-servicio
	 * getUservehicles() => Obtener todos los vehiculos (carros y motos)
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
		
		//Usuario usuario = convertToUsuario(usuarioDTO);		 
		//Usuario usuarioGuardado = usuarioRepository.save(usuario);
		Usuario usuarioGuardado = usuarioRepository.save(convertToUsuario(usuarioDTO));
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
			    "http://carro-servicio/carro/usuario/" + usuarioId,
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
			    "http://moto-servicio/moto/usuario/" + usuarioId,
			    HttpMethod.GET,
			    null,
			    new ParameterizedTypeReference<List<MotoDTO>>() {}).getBody();
		return motosDTO;
	}
	
	
	/*
	 * 
	 * Creamos los Servicios FeignClient
	 * 
	 * */
	
	/*
	 * Metodo saveCar (FeignClient)
	 * 
	 * asignamos el usuarioId al carroDTO
	 * guardamos los datos usando la interfaz que creamos con feignclient
	 * retornamos el carroNuevo
	 * 
	 * */
	
	public CarroDTO saveCar(int usuarioId, CarroDTO carroDTO) {
		carroDTO.setUsuarioId(usuarioId);
		CarroDTO carroNuevo = carroFeignClient.save(carroDTO);
		return carroNuevo;
	}
	
	/*
	 * Metodo saveMoto (FeignClient)
	 * 
	 * asignamos el usuarioId a la motoDTO
	 * guardamos los datos usando la interfaz que creamo con feignclient
	 * retornamos la motoNuevo
	 * 
	 * */
	
	public MotoDTO saveMoto(int usuarioId, MotoDTO motoDTO) {
		motoDTO.setUsuarioId(usuarioId);
		MotoDTO motoNuevo = motoFeignClient.save(motoDTO);
		return motoNuevo;
	}
	
	/*
	 * Metodo getUservehicles (FeignClient)	
	 * 
	 * */
	
	public Map<String, Object> getUservehicles(int usuarioId){
	    Map<String, Object> resultado = new HashMap<>();
	    UsuarioDTO usuario = usuarioRepository.findById(usuarioId)
	        .map(this::convertToUsuarioDTO)
	        .orElse(null);

	    if(usuario == null) {
	        resultado.put("Mensaje", "El usuario no existe");
	        return resultado;
	    }

	    resultado.put("Usuario", usuario);

	    List<CarroDTO> carros = carroFeignClient.getCars(usuarioId);

	    if(carros.isEmpty()) {
	        resultado.put("Carros", "El usuario no tiene carros");
	    }
	    else {
	        resultado.put("Carros", carros);
	    }

	    List<MotoDTO> motos = motoFeignClient.getMotos(usuarioId);

	    if(motos.isEmpty()) {
	        resultado.put("Motos", "El usuario no tiene motos");
	    }
	    else {
	        resultado.put("Motos", motos);
	    }
	    
	    return resultado;
	}

	
}