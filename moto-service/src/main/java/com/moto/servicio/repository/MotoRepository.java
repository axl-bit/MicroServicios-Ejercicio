package com.moto.servicio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moto.servicio.entity.Moto;

public interface MotoRepository extends JpaRepository<Moto, Integer> {
	
	List<Moto> findByUsuarioId(int usuarioId);

}
