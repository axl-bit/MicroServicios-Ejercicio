package com.usuario.servicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usuario.servicio.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
