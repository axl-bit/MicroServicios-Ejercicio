package com.usuario.servicio.dto;

import java.io.Serializable;

public class CarroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String marca;
	private String modelo;
	private int usuarioId;

	/*
	 * 
	 * Añadimos los constructores
	 * 
	 */

	public CarroDTO() {
		super();
	}

	public CarroDTO(String marca, String modelo, int usuarioId) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.usuarioId = usuarioId;
	}

	/*
	 * 
	 * Añadimos los getters y setters
	 * 
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
}
