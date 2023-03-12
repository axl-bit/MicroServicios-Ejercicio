package com.usuario.servicio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usuario.servicio.entity.Carro;

public interface CarroRepository extends JpaRepository<Carro, Integer> {
	
	List<Carro> findByUsuarioId(int usuarioId);

}
