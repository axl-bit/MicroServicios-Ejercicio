package com.moto.servicio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moto.servicio.dto.MotoDTO;
import com.moto.servicio.entity.Moto;
import com.moto.servicio.repository.MotoRepository;


@Service
public class MotoServicio {
	
	//Intaciamos el repositorio
	@Autowired
	private MotoRepository motoRepository;
	
	/*
	 * Indice de MotoServicio
	 * 
	 * Conversores:
	 * 
	 * convertToMotoDTO => transforma los datos de moto a motoDTO
	 * convertToMoto => transfoma los datos de motoDTO a moto
	 * 
	 * Serivcios:
	 * 
	 * getAllMotos => obtener una lista de todas las motos de la base de datos
	 * getMotoById => obtener una moto en especifico mediante el Id
	 * saveMoto	   => crear una moto
	 * findMotoByUserId => obtiene una la lista de motos pertenecientes a un usuarios mediante el Id del mismo
	 * 
	 * */
	
	
	/*
	 * 
	 * Cremos los conversores de MotoDTO y Moto
	 * 
	 */
	
	private MotoDTO convertToMotoDTO(Moto moto) {
		
		MotoDTO motoDTO = new MotoDTO();
		
		motoDTO.setId(moto.getId());
		motoDTO.setMarca(moto.getMarca());
		motoDTO.setModelo(moto.getModelo());
		motoDTO.setUsuarioId(moto.getUsuarioId());
		
		return motoDTO;
		
	}
	
	private Moto convertToMoto(MotoDTO motoDTO) {
		
		Moto moto = new Moto();
		
		moto.setId(motoDTO.getId());
		moto.setMarca(motoDTO.getMarca());
		moto.setModelo(motoDTO.getModelo());
		moto.setUsuarioId(motoDTO.getUsuarioId());
		
		return moto;
	}
	
	/*
	 * 
	 * Creamos los Servicios
	 * 
	 * */
	
	/*
	 * Metodo getAllMotos
	 * 
	 * primero obtenemos los valores de la base de datos usando el metodo del repositorio JPA
	 * Cremos una lsita vacia de MotoDTO, esta para guardar los datos de retorno
	 * usando un for convertimos los datos obtenidos a motoDTO
	 * luego lo añadimos a la lista de carrosDTO
	 * por ultimo retornamos el lsitado de MotosDTO
	 * 
	 * */
	
	public List<MotoDTO> getAllMotos(){
		
		List<Moto> motos = motoRepository.findAll();
		List<MotoDTO> motosDTO = new ArrayList<>();
		for(Moto moto: motos) { motosDTO.add(convertToMotoDTO(moto));}
		
		return motosDTO;
		
	}
	
	/*
	 * Metodo getMotoById
	 * 
	 * Generamos una moto motoOptional y le asignamos los valores
	 * obtenidos por el metood del repostiorio CrudRepository findById
	 * luego reornamos los datos mapeando un objeto MotoDTO
	 * si el objeto existe se devolvera el objeto y en caso no exista se retornara null
	 * 
	 * */
	
	public MotoDTO getMotoById(int id) {
		Optional<Moto> motoOptional = motoRepository.findById(id);
		return motoOptional.map(this::convertToMotoDTO).orElse(null);
	}
	
	/*
	 * Metodo saveMoto
	 * 
	 * instanciamos Moto y convertimos los datos obtenidos de motoDTO
	 * guardamos los datos usando el repositorio CrudRepository
	 * retornamos los datos convertido a DTO
	 * */

	public MotoDTO saveMoto(MotoDTO motoDTO) {
		
		Moto moto = convertToMoto(motoDTO);
		Moto motoguardado = motoRepository.save(moto);
		return convertToMotoDTO(motoguardado);
		
	}
	
	/*
	 * Metodo findMotoByUserId
	 * 
	 * encontramos las motos que pertencen a un usuario usando su Id
	 * creamos la lista vacia de motos, usando un bucle for convertimos los datos a motoDTO y los guardamos
	 * retornamos la lista de motos que pertenecen al usuario
	 * 
	 * */
	
	public List<MotoDTO> findMotoByUserId(int usuarioId){
		
		List<Moto> motos = motoRepository.findByUsuarioId(usuarioId);
		List<MotoDTO> motosDTO = new ArrayList<>();
		
		for(Moto moto: motos) { motosDTO.add(convertToMotoDTO(moto));}
		
		return motosDTO;
		
	}
}
