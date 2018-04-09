package es.nico.wata.tpv.entities;

import java.io.Serializable;

public class Categoria implements Serializable{
	private static final long serialVersionUID = 2254120630994200045L;

	Long id;
	String nombre;
	public Categoria() {
		
	}
	public Categoria(String nombre) {
		this.nombre = nombre;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
