package com.usuario.servicio.dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;

	private String nombre;

	private String email;

	/*
	 * 
	 * Añadiendo los constructores
	 * 
	 */

	public UsuarioDTO() {
		super();
	}
	
	public UsuarioDTO(String nombre, String email) {
		super();
		this.nombre = nombre;
		this.email = email;
	}



	/*
	 * 
	 * Añadiendo los getters y setters
	 * 
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
