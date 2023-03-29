package com.moto.servicio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	 * creamos una lista de Moto y le asignamos los datos obtenidos de findAll()
	 * creamos una lista de MotoDTO y transformamos los datos de motos a motosDTO
	 * retornamos la lista motosDTO
	 * 
	 * */
	
	public List<MotoDTO> getAllMotos(){
		
		List<Moto> motos = motoRepository.findAll();
		List<MotoDTO> motosDTO = motos.stream().map(this::convertToMotoDTO).collect(Collectors.toList());
		
		return motosDTO;
		
	}
	
	/*
	 * Metodo getMotoById
	 * 
	 * creamos Moto que sea Optional y le asignamos los valores obtenidos por findById
	 * retornmaos los datos convertidos a MotoDTO o null en caso no se encuentren datos
	 * 
	 * */
	
	public MotoDTO getMotoById(int id) {
		Optional<Moto> motoOptional = motoRepository.findById(id);
		return motoOptional.map(this::convertToMotoDTO).orElse(null);
	}
	
	/*
	 * Metodo saveMoto
	 * 
	 * creamos una Moto y le asignamos los valores obtenidos de motoDTO (convertidos a Moto)
	 * creamos una motoguardado y usando save guardamos la moto
	 * retornamos los valores de motoGuardado conretidos a motoDTO
	 * 
	 * */

	public MotoDTO saveMoto(MotoDTO motoDTO) {
		
		Moto moto = convertToMoto(motoDTO);
		Moto motoGuardado = motoRepository.save(moto);
		return convertToMotoDTO(motoGuardado);
		
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
