package com.usuario.servicio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuario.servicio.dto.CarroDTO;
import com.usuario.servicio.entity.Carro;
import com.usuario.servicio.repository.CarroRepository;

@Service
public class CarroServicio {
	
	//Instanciamos el repositorio
		@Autowired
		private CarroRepository carroRepository;
		
		/*
		 * Indice:
		 * 
		 * Conversores
		 * 
		 * converToCarroDTO => transforma los datos de carro a carroDTO
		 * converToCarro => transforma los datos de carroDTO a carro
		 * 
		 * Servicios
		 * 
		 * getAllCars => obtener una lista de todos los carros de la base de datos
		 * getCarById => obtener un carro en especifico mediante el Id
		 * saveCar 	  => crear un carro nuevo 
		 * findCarByUserId => 0btiene una la lista de carros pertenecientes a un usuarios mediante el Id del mismo
		 * 
		 * */
		
		/*
		 * 
		 *Creamos los Conversores de CarroDTO y Carro
		 * 
		 */
		
		private CarroDTO convertToCarroDTO(Carro carro) {
			
			CarroDTO carroDTO = new CarroDTO();
			
			carroDTO.setId(carro.getId());
			carroDTO.setMarca(carro.getMarca());
			carroDTO.setModelo(carro.getModelo());
			carroDTO.setUsuarioId(carro.getUsuarioId());
			
			return carroDTO;
		}
		
		private Carro convertToCarro(CarroDTO carroDTO) {
			
			Carro carro = new Carro();
			
			carro.setId(carroDTO.getId());
			carro.setMarca(carroDTO.getMarca());
			carro.setModelo(carroDTO.getModelo());
			carro.setUsuarioId(carroDTO.getUsuarioId());
			
			return carro;
		}
		
		/*
		 * 
		 * Creamos los Servicios
		 * 
		 * */
		
		/*
		 * Metodo getAllCars
		 * 
		 * creamos una lista de Carro y le asignamos los datos obtenidos de findAll()
		 * creamos una lista de CarroDTO y transformamos los datos de carros a carrosDTO
		 * retornamos la lista de carrosDTO
		 * 
		 * */
		
		public List<CarroDTO> getAllCars(){
			
			List<Carro> carros = carroRepository.findAll();			
			List<CarroDTO> carrosDTO = carros.stream().map(this::convertToCarroDTO).collect(Collectors.toList()); 
			
			return carrosDTO;
		}
		
		/*
		 * Metodo getCarById
		 * 
		 * creamos Carro que sea Optional y le asignamos los valores de findById
		 * retornamos los datos convertidos a CarroDTO o null en caso no se encuentren datos
		 * 
		 * */
		
		public CarroDTO getCarById(int id) {
			Optional<Carro> carroOptional = carroRepository.findById(id);
			return carroOptional.map(this::convertToCarroDTO).orElse(null);
		}
		
		/*
		 * Metodo saveCar
		 * 
		 * Creamos un Carro y le asignamos los valores obtenidos de carroDTO (convertidos a Carro)
		 * Creamos un carroGuardado y usando save guardamos el carro
		 * retornamos los valores de carroGuardado convertidos a CarroDTO
		 * 
		 * */
		
		public CarroDTO saveCar(CarroDTO carroDTO) {
			
			Carro carro = convertToCarro(carroDTO);
			Carro carroGuardado = carroRepository.save(carro);
			return convertToCarroDTO(carroGuardado);
			
		}
		
		/*
		 * Metodo CarByUserId
		 * 
		 * encontramos los carros que pertencen a un usuario usando su Id
		 * creamos la lista vacia de carros, usando un bucle for convertimos los datos a carroDTO y los guardamos
		 * retornamos la lista de carros que pertenecen al usuario
		 * 
		 * */
		
		public List<CarroDTO> findCarByUserId(int usuarioId){
			
			List<Carro> carros = carroRepository.findByUsuarioId(usuarioId);
			List<CarroDTO> carrosDTO = new ArrayList<>();
			
			for(Carro carro: carros) { carrosDTO.add(convertToCarroDTO(carro));}
			
			return carrosDTO;
		}

}
