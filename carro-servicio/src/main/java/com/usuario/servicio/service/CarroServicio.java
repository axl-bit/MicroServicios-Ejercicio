package com.usuario.servicio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		 * Creamos lo Servicios
		 * 
		 * */
		
		/*
		 * Metodo getAllCars
		 * 
		 * pirmero obtenemos los valores de la base de datos usando el metodo del repositorio JPA
		 * Creamos una lista vacia de CarroDTO, esta para guardar los datos de retorno
		 * usando un for convertimos los datos obtenidos a carroDTO
		 * luego lo añadimos a la lista de carrosDTO
		 * por ultimo retornamos el listado de carrosDTO
		 * 
		 * */
		
		public List<CarroDTO> getAllCars(){
			
			List<Carro> carros = carroRepository.findAll();
			List<CarroDTO> carrosDTO = new ArrayList<>();
			for(Carro carro: carros) {carrosDTO.add(convertToCarroDTO(carro));}
			
			return carrosDTO;
		}
		
		/*
		 * Metodo getCarById
		 * 
		 * Generamos un carro carroOptional y le asignamos los valores 
		 * obtenidos por el metodo del repositorio CrudRepository findById
		 * luego retornamos los datos mapeando un objeto CarroDTO
		 * si el objeto existe se devolvera el objeto y en caso no exista se retornara null
		 * 
		 * */
		
		public CarroDTO getCarById(int id) {
			Optional<Carro> carroOptional = carroRepository.findById(id);
			return carroOptional.map(this::convertToCarroDTO).orElse(null);
		}
		
		/*
		 * Metodo saveCar
		 * 
		 * instanciamos Carro y convertimos los datos obtenidos de carroDTO
		 * guardamos los datos usando el repositorio CrudRepository
		 * yretornamos los datos convertido a DTO.
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
		 *  encontramos los carros que pertencen a un usuario usando su Id
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
